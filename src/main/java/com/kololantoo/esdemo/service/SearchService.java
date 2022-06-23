package com.kololantoo.esdemo.service;

import com.kololantoo.esdemo.repository.MyEsDemoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zhengyang
 * @date 2022/6/21
 * @description
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class SearchService {

    @Autowired
    private final ElasticsearchRestTemplate template;
    @Autowired
    private final MyEsDemoRepository repository;
    @Autowired
    private final RestHighLevelClient client;
}
