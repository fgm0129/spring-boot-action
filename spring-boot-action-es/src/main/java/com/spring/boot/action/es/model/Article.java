package com.spring.boot.action.es.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fgm on 2017/9/27.
 */
@Data
@Document(indexName="article_index",type="article",shards=5,replicas=1,indexStoreType="fs",refreshInterval="-1")
public class Article implements Serializable {

        private static final long serialVersionUID = 551589397625941750L;
        @Id
        private Long id;
        /**标题*/
        private String title;
        /**摘要*/
        private String abstracts;
        /**内容*/
        private String content;
        /**发表时间*/
        private String sunnyDate;
        /**点击率*/
        private Long clickCount;

        private String hello;

        private String date;

        private String publishAuthor;


        //        @Field(format= DateFormat.date_time,index= FieldIndex.no,store=true,type= FieldType.Date)


}
