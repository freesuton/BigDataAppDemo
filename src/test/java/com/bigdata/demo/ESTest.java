package com.bigdata.demo;


import com.bigdata.demo.domain.repository.ArticleRepository;
import com.bigdata.demo.model.es.Article;
import com.bigdata.demo.model.es.Author;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Arrays.asList;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ESTest {
  @Autowired
  private ArticleRepository articleRepository;

  @Test
  public void testSave(){

      Article article = new Article("Spring Data Elasticsearch");
      article.setAuthors(asList(new Author("god"),new Author("John")));
      articleRepository.save(article);

      article = new Article("Spring Data Elasticsearch2");
      article.setAuthors(asList(new Author("god"),new Author("King")));
      articleRepository.save(article);

      article = new Article("Spring Data Elasticsearch3");
      article.setAuthors(asList(new Author("god"),new Author("Bill")));
      articleRepository.save(article);
  }

  @Test
  public void queryAuthorName()  {
    Page<Article> articles = articleRepository.findByAuthorsName("god", PageRequest.of(0,10));
    for (Article article : articles.getContent()) {
      System.out.println(article);
      for (Author author : article.getAuthors()) {
        System.out.println(author);
      }
    }
  }

  @Test
  public void update() {
    Page<Article> articles = articleRepository.findByTitle("Spring Data Elasticsearch",PageRequest.of(0,10));

    Article article = articles.getContent().get(0);
    System.out.println(article);
    System.out.println(article.getAuthors().get(0));
    Author author = new Author("chali");
    article.setAuthors(Arrays.asList(author));
    articleRepository.save(article);
  }

  @Test
  public void delete(){
    Page<Article> articles = articleRepository.findByTitle("Spring Data Elasticsearch3",PageRequest.of(0,10));
    Article article = articles.getContent().get(0);
    articleRepository.delete(article);
  }

}
