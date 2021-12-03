package com.bigdata.demo.domain.repository;

import com.bigdata.demo.form.PlanCostSharesAddForm;
import com.bigdata.demo.model.es.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article,String> {

  //search according to he name of author
  Page<Article> findByAuthorsName(String name, Pageable pageable);

  //search by title term
  Page<Article> findByTitleIsContaining(String word, Pageable pageable);

  Page<Article> findByTitle(String title, Pageable pageable);
}
