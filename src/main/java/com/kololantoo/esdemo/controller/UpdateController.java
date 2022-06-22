package com.kololantoo.esdemo.controller;

import com.kololantoo.esdemo.service.UpdateService;
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
@RequestMapping("update")
@Api(tags = "更新")
public class UpdateController {

    @Autowired
    private UpdateService updateService;

    @PostMapping("")
    @ApiOperation(value = "使用null覆盖更新")
    public void updateWithNull() {
        updateService.updateWithNull();
    }
}
