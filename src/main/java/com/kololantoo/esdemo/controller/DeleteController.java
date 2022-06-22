package com.kololantoo.esdemo.controller;

import com.kololantoo.esdemo.service.DeleteService;
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
@RequestMapping("delete")
@Api(tags = "删除")
public class DeleteController {

    @Autowired
    private DeleteService deleteService;
}
