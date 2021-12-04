package com.bigdata.demo.domain.repository;

import com.bigdata.demo.form.PlanCostSharesAddForm;
import com.bigdata.demo.model.es.Article;
import com.bigdata.demo.vo.PlanCostSharesVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanCostShareRepository extends ElasticsearchRepository<PlanCostSharesAddForm,String> {

  //search according to he name of author
  Page<PlanCostSharesAddForm> findByObjectId(String objectId, Pageable pageable);

  //search by title term
//  Page<PlanCostSharesAddForm> findByTitleIsContaining(String word, Pageable pageable);

  Page<PlanCostSharesAddForm> findBy_org(String _org, Pageable pageable);
}
