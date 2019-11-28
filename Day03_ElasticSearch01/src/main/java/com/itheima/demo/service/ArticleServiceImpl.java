package com.itheima.demo.service;

import com.itheima.demo.dao.ArticleDao;
import com.itheima.demo.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @创建人 zyh
 * @创建时间 2019/10/19
 * @描述
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public void save(Article article) {
        articleDao.save(article);
    }
}