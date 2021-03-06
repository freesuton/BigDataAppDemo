package com.bigdata.demo.model.es;

import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "blog")
@Data
public class Article {
  @Id
  private String id;
  private String title;
  @Field(type = FieldType.Nested, includeInParent = true)
  private List<Author> authors;

  public Article(String title){
    this.title =title;
  }
}
