package com.alke.hfs.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alke.hfs.core.mapper.DictMapper;
import com.alke.hfs.core.pojo.dto.ExcelDictDTO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * :
 * Alke
 * 2021-08-23 16:23
 */
@Slf4j
@NoArgsConstructor
public class ExcelDictDTOListener extends AnalysisEventListener<ExcelDictDTO> {

    private DictMapper dictMapper;

//    数据列表
    private List<ExcelDictDTO> list = new ArrayList<>();

//    每5条记录批量存储一次
    private static final int BATCH_COUNT = 5;

    public ExcelDictDTOListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(ExcelDictDTO data, AnalysisContext analysisContext) {
        list.add(data);
        if (list.size()>=BATCH_COUNT){
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//        最后不足BATCH_COUNT的数据导入
        saveData();
        log.info("数据解析完毕");
    }

    private void saveData() {
        log.info("{} 条数据被导入到数据库中",list.size());
        //调用mapper层的save方法： save list
        dictMapper.insertBatch(list);
        log.info("{} 条数据导入数据库成功",list.size());
    }
}
