package com.kololantoo.esdemo.controller;

import com.kololantoo.esdemo.service.SearchService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengyang
 * @date 2022/6/22
 * @description
 */
@RestController
@RequestMapping("search")
@Api(tags = "查询")
public class SearchController {

    @Autowired
    private SearchService service;
}
