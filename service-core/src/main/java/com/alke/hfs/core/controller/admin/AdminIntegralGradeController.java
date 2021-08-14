package com.alke.hfs.core.controller.admin;


import com.alke.common.result.R;
import com.alke.hfs.core.pojo.entity.IntegralGrade;
import com.alke.hfs.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author Alke
 * @since 2021-08-13
 */
@Api(tags = "积分等级管理")
@CrossOrigin
@RestController
@RequestMapping("/admin/core/integralGrade")
public class AdminIntegralGradeController {

    @Resource
    private IntegralGradeService integralGradeService;

    @ApiOperation("积分等级列表")
    @GetMapping("/list")
    public R listAll() {
        List<IntegralGrade> list = integralGradeService.list();
        return R.ok().data("list", list).message("获取列表成功");
    }

    @ApiOperation(value = "根据id删除积分等级", notes = "逻辑删除")
    @DeleteMapping("/remove/{id}")
    public R removeById(@PathVariable Long id) {
        boolean result = integralGradeService.removeById(id);
        if (result){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation("新增积分等级")
    @PostMapping("/save")
    public R save(@RequestBody IntegralGrade integralGrade){
        boolean result = integralGradeService.save(integralGrade);
        if (result){
            return R.ok().message("保存成功");
        }else {
            return R.error().message("保存失败");
        }
    }

    @ApiOperation("根据id获取积分等级列表")
    @GetMapping("/get/{id}")
    public R getById(@PathVariable Long id){
        IntegralGrade result = integralGradeService.getById(id);
        if (result!=null){
            return R.ok().data("record", result).message("获取成功");
        }else {
            return R.error().message("获取失败");
        }
    }

    @ApiOperation("根据id更新积分等级")
    @PutMapping("/update")
    public R updateById(@RequestBody IntegralGrade integralGrade){
        boolean result = integralGradeService.updateById(integralGrade);
        if (result){
            return R.ok().message("修改成功");
        }else {
            return R.error().message("修改失败");
        }
    }

}

