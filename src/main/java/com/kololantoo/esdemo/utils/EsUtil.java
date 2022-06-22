package com.kololantoo.esdemo.utils;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.RefreshPolicy;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhengyang
 * @date 2022/6/20
 * @description
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EsUtil {

    @Autowired
    private final RestHighLevelClient restHighLevelClient;
    @Autowired
    private final ElasticsearchRestTemplate template;

    // 基于ElasticsearchRestTemplate的增删改查
    public<T> void add(T t) {
        String indexName = t.getClass().getAnnotation(Document.class).indexName();
        template.setRefreshPolicy(RefreshPolicy.IMMEDIATE);
        template.save(t, IndexCoordinates.of(indexName));
    }

    public<T> void delete(T t) {
        String indexName = t.getClass().getAnnotation(Document.class).indexName();
        template.setRefreshPolicy(RefreshPolicy.IMMEDIATE);
        template.delete(t,IndexCoordinates.of(indexName));
    }

    public<T> void update(T t) {
        String indexName = t.getClass().getAnnotation(Document.class).indexName();
    }

    public<T> void search(T t) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 使用QueryBuilders创建查询条件
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("name", "张三");
        queryBuilder.withQuery(matchQuery);
    }

    /**
     * 构建es BulkProcessor
     * @return
     */
    public BulkProcessor createBulkProcessor() {
        BulkProcessor.Listener listener = new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long executionId, BulkRequest request) {
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request,
                                  BulkResponse response) {
                if (!response.hasFailures()) {
//                    LogUtil.info("2. 【afterBulk-成功】批量 ["+executionId+"] 完成时间：" + response.getTook().getMillis()+"ms");
                } else {
                    BulkItemResponse[] items = response.getItems();
                    for (BulkItemResponse item : items) {
                        if (item.isFailed()) {
                            break;
                        }
                    }
                }
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request,
                                  Throwable failure) {

                List<DocWriteRequest<?>> requests = request.requests();
                List<String> esIds = requests.stream().map(DocWriteRequest::id).collect(Collectors.toList());
            }
        };

        BulkProcessor.Builder builder = BulkProcessor.builder(((bulkRequest, bulkResponseActionListener) -> {
            restHighLevelClient.bulkAsync(bulkRequest, RequestOptions.DEFAULT, bulkResponseActionListener);
        }), listener);
        //到达1000条时刷新
        builder.setBulkActions(1000);
        //内存到达8M时刷新
        builder.setBulkSize(new ByteSizeValue(8L, ByteSizeUnit.MB));
        //设置的刷新间隔200ms
        builder.setFlushInterval(TimeValue.timeValueMillis(200));
        //设置允许执行的并发请求数。
        builder.setConcurrentRequests(8);
        //设置重试策略
        builder.setBackoffPolicy(BackoffPolicy.constantBackoff(TimeValue.timeValueSeconds(1), 3));
        return builder.build();
    }
}
