package com.itheima.demo;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;

/**
 * @创建人 zyh
 * @创建时间 2019/8/16
 * @描述
 */
public class demo1 {


    @Test
    public void createIndex() throws Exception {
        //创建客户端访问对象
        TransportClient transportClient = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        //构建文档内容 {"id":1,"title":"这是title","content":"这是content"}
        //使用XContentFactory方式准备文档内容
        XContentBuilder xContentBuilder = XContentFactory.jsonBuilder()
                .startObject()
                .field("id", 1)
                .field("title", "这是title")
                .field("content", "这是content")
                .endObject();
        //创建索引、类型、文档
        IndexResponse indexResponse = transportClient.prepareIndex("blog", "article", "1")
                .setSource(xContentBuilder)
                .get();
        System.out.println(indexResponse.status());
        //使用map方式，准备文档内容
//        Map<String,Object> map = new HashMap();
//        map.put("id",2);
//        map.put("title","用map方式生成title");
//        map.put("content","用map方式生成content");
//        IndexResponse indexResponse1 = transportClient.prepareIndex("blog", "article", "2")
//                .setSource(map)
//                .get();
//        System.out.println(indexResponse1.status());
        transportClient.close();
    }


    //如果想要设置Settings，可以通过Settings.builder()创建Settings,目前了解
    Settings settings = Settings.builder()
            .put("cluster.name", "mallsearch") //集群名
            .put("client.transport.sniff", true)// 嗅探机制，找到集群
            .put("index.number_of_shards", 2) // 分片数
            .put("index.number_of_replicas", 1) // 副本数
            .build();



    @Test
    public void testSearchAll() throws Exception {
        //创建客户端访问对象
        TransportClient transportClient = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        //构建搜索内容
        SearchResponse response = transportClient.prepareSearch("blog3").setTypes("article")
                .setQuery(QueryBuilders.matchAllQuery())
                .get();
        //获取搜索结果
        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        //遍历结果
        Iterator<SearchHit> iterator = hits.iterator();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
        transportClient.close();
    }


    @Test
    public void testQueryString() throws Exception {
        TransportClient transportClient = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        SearchResponse response = transportClient.prepareSearch("blog3")
                .setTypes("article")
                .setQuery(QueryBuilders.queryStringQuery("哈哈哈哈哈哈"))
                .get();
        SearchHits searchHits = response.getHits();
        System.out.println(searchHits.getTotalHits());
        Iterator<SearchHit> iterator = searchHits.iterator();
        for (SearchHit searchHit : searchHits) {
            System.out.println(searchHit.getSourceAsString());
        }
        transportClient.close();
    }


    @Test
    public void createIndex1() throws UnknownHostException {
        TransportClient transportClient = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress
                        (InetAddress.getByName("127.0.0.1"),9300)
                );

        CreateIndexResponse response = transportClient.admin().indices().prepareCreate("blog3").get();
        System.out.println(response.isShardsAcked());
        System.out.println(response.isAcknowledged());
        transportClient.close();
    }
}
