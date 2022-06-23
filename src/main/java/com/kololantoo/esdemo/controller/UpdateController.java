package com.kololantoo.esdemo.controller;

import com.kololantoo.esdemo.model.MyEsDemo;
import com.kololantoo.esdemo.service.UpdateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
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
@RequestMapping("update")
@Api(tags = "更新")
public class UpdateController {

    @Autowired
    private UpdateService updateService;

    @Autowired
    private ElasticsearchRestTemplate template;
    @Autowired
    private RestHighLevelClient client;

    @PostMapping("updateByTemplateSave")
    @ApiOperation(value = "TemplateSave批量更新")
    public void updateByTemplateSave(MyEsDemo demo) {
        updateService.updateByTemplateSave(demo);
    }

    @PostMapping("updateByTemplate")
    @ApiOperation(value = "Template更新")
    public void updateByTemplate(MyEsDemo demo) {
        updateService.updateByTemplate(demo);
    }

    @PostMapping("updateBatchByTemplate")
    @ApiOperation(value = "Template批量更新")
    public void updateBatchByTemplate(List<MyEsDemo> list) {
        updateService.updateBatchByTemplate(list);
    }

    @PostMapping("queryUpdateByTemplate")
    @ApiOperation(value = "Template查询更新")
    public void queryUpdateByTemplate() {
        updateService.queryUpdateByTemplate();
    }

    @PostMapping("updateByRestClient")
    @ApiOperation(value = "RestClient更新")
    public void updateByRestClient(MyEsDemo demo) throws IOException {
        updateService.updateByRestClient(demo);
    }

    @PostMapping("batchUpdateByRestClient")
    @ApiOperation(value = "RestClient批量更新")
    public void batchUpdateByRestClient(List<MyEsDemo> list) throws IOException {
        updateService.batchUpdateByRestClient(list);
    }

    @PostMapping("queryUpdateByRestClient")
    @ApiOperation(value = "RestClient查询更新")
    public void queryUpdateByRestClient() throws IOException {
        updateService.queryUpdateByRestClient();
    }
}
