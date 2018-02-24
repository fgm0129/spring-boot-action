package com.spring.boot.action.es.respository;

import com.spring.boot.action.es.model.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by fgm on 2017/9/28.
 */
public interface ArticleSearchRepository extends ElasticsearchRepository<Article,Long> {



}
