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
 * @date 2022/6/22
 * @description
 */
@RestController
@RequestMapping("data")
@Api(tags = "数据初始化操作")
public class InitController {

    @Autowired
    private InitService initService;

    @PostMapping("init")
    @ApiOperation("初始化数据")
    public void init() {
        initService.init();
    }

    @PostMapping("truncate")
    @ApiOperation("清空数据")
    public void truncate() {
        initService.truncate();
    }
}
