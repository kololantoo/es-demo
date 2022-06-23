package com.kololantoo.esdemo.controller;

import com.kololantoo.esdemo.model.MyEsDemo;
import com.kololantoo.esdemo.service.DeleteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author zhengyang
 * @date 2022/6/22
 * @description
 */
@RestController
@RequestMapping("delete")
@Api(tags = "删除")
public class DeleteController {

    @Autowired
    private DeleteService deleteService;

    @PostMapping("deleteByTemplate")
    @ApiOperation(value = "Template删除")
    public void deleteByTemplate(MyEsDemo demo) {
        deleteService.deleteByTemplate(demo);
    }

    @PostMapping("deleteBatchByTemplate")
    @ApiOperation(value = "Template批量删除")
    public void deleteBatchByTemplate(List<MyEsDemo> list) {
        deleteService.deleteBatchByTemplate(list);
    }

    @PostMapping("queryDeleteByTemplate")
    @ApiOperation(value = "Template条件删除")
    public void queryDeleteByTemplate() {
        deleteService.queryDeleteByTemplate();
    }

    @PostMapping("deleteByRepository")
    @ApiOperation(value = "Repository删除")
    public void deleteByRepository(MyEsDemo demo) {
        deleteService.deleteByRepository(demo);
    }

    @PostMapping("deleteBatchByRepository")
    @ApiOperation(value = "Repository批量删除")
    public void deleteBatchByRepository(List<MyEsDemo> list) {
        deleteService.deleteBatchByRepository(list);
    }

    @PostMapping("deleteByRestClient")
    @ApiOperation(value = "RestClient删除")
    public void deleteByRestClient(MyEsDemo demo) throws IOException {
        deleteService.deleteByRestClient(demo);
    }

    @PostMapping("deleteBatchByRestClient")
    @ApiOperation(value = "RestClient批量删除")
    public void deleteBatchByRestClient(List<MyEsDemo> list) throws IOException {
        deleteService.deleteBatchByRestClient(list);
    }

    @PostMapping("queryDeleteByRestClient")
    @ApiOperation(value = "RestClient条件删除")
    public void queryDeleteByRestClient() throws IOException {
        deleteService.queryDeleteByRestClient();
    }


}
