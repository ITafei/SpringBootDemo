package com.itheima;

import com.itheima.demo.pojo.Article;
import com.itheima.demo.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @创建人 zyh
 * @创建时间 2019/10/19
 * @描述
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ESTest {

    @Autowired
    private ArticleService as;

    @Test
    public void testAdd(){
        Article a = new Article();
        a.setId(1L);
        a.setTitle("elasticSearch 3.0版本发布...更新");
        a.setContent("ElasticSearch是一个基于Lucene的搜索服务器。它提供了一个分布式多用户能力的全文搜 索引擎，基于RESTful web接口");
        as.save(a);
    }
}
