package com.kololantoo.esdemo.repository;

import com.kololantoo.esdemo.model.MyEsDemo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author zhengyang
 * @date 2022/6/22
 * @description
 */
public interface MyEsDemoRepository  extends ElasticsearchRepository<MyEsDemo,Long> {

    List<MyEsDemo> findAllByIdEquals(Long id);
}
