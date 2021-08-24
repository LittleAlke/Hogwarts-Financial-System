package com.alke.hfs.core.mapper;

import com.alke.hfs.core.pojo.dto.ExcelDictDTO;
import com.alke.hfs.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author Alke
 * @since 2021-08-13
 */
public interface DictMapper extends BaseMapper<Dict> {

    void insertBatch(List<ExcelDictDTO> list);
}
