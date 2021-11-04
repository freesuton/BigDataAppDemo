package com.bigdata.demo.service;

import com.bigdata.demo.form.PlanCostSharesAddForm;
import com.bigdata.demo.vo.PlanCostSharesVo;
import com.bigdata.demo.vo.ResponseVo;

public interface IPlanCostSharesService {

  public ResponseVo<PlanCostSharesVo> addUseCase(String oid,PlanCostSharesAddForm form);

  ResponseVo<PlanCostSharesVo> add2list(String oid,PlanCostSharesAddForm form);

  void delete(String oid);

//  ResponseVo<PlanCostSharesVo> query(Integer oid);

  PlanCostSharesVo query(Integer oid);

  ResponseVo<PlanCostSharesVo> update(String oid,PlanCostSharesVo planCostSharesVo);

  void testRedis();
 }
