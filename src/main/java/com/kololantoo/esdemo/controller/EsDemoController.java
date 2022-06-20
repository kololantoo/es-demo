package com.kololantoo.esdemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("es操作")
public class EsDemoController {

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
