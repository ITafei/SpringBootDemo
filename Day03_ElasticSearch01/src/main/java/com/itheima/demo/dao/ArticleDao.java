package com.itheima.demo.dao;

import com.itheima.demo.pojo.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @创建人 zyh
 * @创建时间 2019/10/19
 * @描述
 */
public interface ArticleDao extends ElasticsearchRepository<Article,Long> {
}
