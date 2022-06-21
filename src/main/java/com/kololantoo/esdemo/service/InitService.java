package com.kololantoo.esdemo.service;
import com.google.common.collect.Lists;
import java.util.Date;

import com.kololantoo.esdemo.model.MyEsDemo;
import com.kololantoo.esdemo.utils.EsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

/**
 * @author zhengyang
 * @date 2022/6/21
 * @description 用于初始化示例索引在es中的数据
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InitService {

    @Autowired
    private ElasticsearchRestTemplate template;
    @Autowired
    EsUtil esUtil;

    /**
     * 初始化基础数据
     */
    public void init() {
        MyEsDemo demo = new MyEsDemo();
        demo.setId(0L);
        demo.setNormalText1("");
        demo.setNormalText2("");
        demo.setCombineText("");
        demo.setNormalKeyword("");
        demo.setAnalyzeText("");
        demo.setDate(new Date());
        demo.setBoolVal(false);
        demo.setDoubleVal(0.0D);
        demo.setList(Lists.newArrayList());
        esUtil.add(demo);
    }

    /**
     * 清除所有数据
     */
    public void truncate() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
    }
}
