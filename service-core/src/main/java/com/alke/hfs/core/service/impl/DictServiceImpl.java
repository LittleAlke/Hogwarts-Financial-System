package com.alke.hfs.core.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alke.hfs.core.listener.ExcelDictDTOListener;
import com.alke.hfs.core.pojo.dto.ExcelDictDTO;
import com.alke.hfs.core.pojo.entity.Dict;
import com.alke.hfs.core.mapper.DictMapper;
import com.alke.hfs.core.service.DictService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.beans.Transient;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author Alke
 * @since 2021-08-13
 */
@Slf4j
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 数据导入
     *
     * @param inputStream
     */
//    设置事务回滚
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void importData(InputStream inputStream) {
        EasyExcel.read(inputStream, ExcelDictDTO.class, new ExcelDictDTOListener(baseMapper)).sheet().doRead();
        log.info("Excel导入成功");
    }

    /**
     * 数据导出
     *
     * @return
     */
    @Override
    public List<ExcelDictDTO> listDictData() {
        List<Dict> dictList = baseMapper.selectList(null);
        ArrayList<ExcelDictDTO> excelDictDTOList = new ArrayList<>(dictList.size());
        dictList.forEach(dict -> {
            ExcelDictDTO excelDictDTO = new ExcelDictDTO();
            BeanUtils.copyProperties(dict, excelDictDTO);
            excelDictDTOList.add(excelDictDTO);
        });
        return excelDictDTOList;
    }

    /**
     * 展示页面数据
     *
     * @param parentId
     * @return
     */
    @Override
    public List<Dict> listByParentId(Long parentId) {

//        先在redis中查询数据，若查到了 则返回，否则去数据库中查找并写入redis中
        try {
            List<Dict> dictList = (List<Dict>) redisTemplate.opsForValue().get("hfs:core:dictList:" + parentId);
            if (dictList!=null){
                log.info("从Redis中获取数据列表");
                return dictList;
            }
        } catch (Exception e) {
            log.error("Redis服务器异常:"+ ExceptionUtils.getStackTrace(e));
        }
//        在数据库中查找
        log.info("从数据库中获取数据列表");
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("parent_id", parentId);
        List<Dict> dictList = baseMapper.selectList(dictQueryWrapper);
//        填充hasChildren字段
        dictList.forEach(dict -> {
//            判断当前节点是否有子节点
            boolean hasChildren = this.hasChildren(dict.getId());
            dict.setHasChildren(hasChildren);
        });
        try {
            log.info("将数据存入Redis");
            redisTemplate.opsForValue().set("hfs:core:dictList:" + parentId,dictList,5, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.error("Redis服务器异常:"+ ExceptionUtils.getStackTrace(e));
        }
        return dictList;
    }

    /**
     * 判断当前id节点下是否有子节点
     *
     * @param id
     * @return
     */
    public boolean hasChildren(Long id) {
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(dictQueryWrapper);
        if (count > 0) {
            return true;
        }
        return false;
    }
}
