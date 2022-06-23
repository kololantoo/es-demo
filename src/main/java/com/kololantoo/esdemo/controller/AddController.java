package com.kololantoo.esdemo.controller;

import com.kololantoo.esdemo.model.MyEsDemo;
import com.kololantoo.esdemo.service.AddService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhengyang
 * @date 2022/6/22
 * @description
 */
@RestController
@RequestMapping("add")
@Api(tags = "新增")
public class AddController {

    @Autowired
    private AddService addService;

    @PostMapping("addByTemplate")
    @ApiOperation(value = "Template新增")
    public void addByTemplate(@RequestBody MyEsDemo demo) {
        addService.addByTemplate(demo);
    }

    @PostMapping("addBatchByTemplate")
    @ApiOperation(value = "Template批量新增")
    public void addBatchByTemplate(@RequestBody List<MyEsDemo> list) {
        addService.addBatchByTemplate(list);
    }

    @PostMapping("addByRepository")
    @ApiOperation(value = "Repository新增")
    public void addByRepository(@RequestBody MyEsDemo demo) {
        addService.addByRepository(demo);
    }

    @PostMapping("addBatchByRepository")
    @ApiOperation(value = "Repository批量新增")
    public void addBatchByRepository(@RequestBody List<MyEsDemo> list) {
        addService.addBatchByRepository(list);
    }

    @PostMapping("addByRestClient")
    @ApiOperation(value = "RestClient新增")
    public void addByRestClient(@RequestBody MyEsDemo demo) {
        addService.addByRestClient(demo);
    }

    @PostMapping("addBatchByRestClient")
    @ApiOperation(value = "RestClient批量新增")
    public void addBatchByRestClient(@RequestBody List<MyEsDemo> list) {
        addService.addBatchByRestClient(list);
    }
}
