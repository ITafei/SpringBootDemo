package com.itheima.demo.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @创建人 zyh
 * @创建时间 2019/10/19
 * @描述
 */
/**
 *  @Document(indexName="blob3",type="article")： indexName：索引的名称（必填项） type：索引的类型
 *  @Id：主键的唯一标识
 *  @Field(index=true,analyzer="ik_smart",store=true,searchAnalyzer="ik_smart",type = FieldType.text)
 *  index：是否设置索引 analyzer：存储时使用的分词器 searchAnalyze：搜索时使用的分词器 store：是否存储 type: 数据类型
 */
@Document(indexName = "blog3",type = "article")
public class Article implements Serializable {

    @Id
    private Long id;

    //field可以不用写，如果已经创建了mappings
    @Field(index = true,analyzer = "ik_smart",store = true,type = FieldType.Text)
    private String title;

    @Field(index = true,analyzer = "ik_smart",store = true,type = FieldType.Text)
    private String content;

    //getter/setter方法，toString方法


    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}