package com.kololantoo.esdemo.controller;

import com.kololantoo.esdemo.service.InitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengyang
 * @date 2022/6/20
 * @description
 */
@RestController
@RequestMapping("es")
@Api(tags = "es操作")
public class EsDemoController {

    @Autowired
    private InitService initService;

    @PostMapping("init")
    @ApiOperation("初始化数据")
    public void init() {
        initService.init();
    }

    @PostMapping("add")
    @ApiOperation("新增数据")
    public void add() {

    }

    @PostMapping("delete")
    @ApiOperation("删除数据")
    public void delete() {

    }

    @PostMapping("update")
    @ApiOperation("修改数据")
    public void update() {

    }

    @PostMapping("search")
    @ApiOperation("查询数据")
    public void search() {

    }
}
