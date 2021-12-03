package com.bigdata.demo.service;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.bigdata.demo.DemoApplicationTests;
import com.bigdata.demo.form.PlanCostSharesAddForm;
import com.bigdata.demo.vo.PlanCostSharesVo;
import com.bigdata.demo.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class IPlanCostSharesServiceTest extends DemoApplicationTests {

  @Autowired
  private IPlanCostSharesService planCostSharesService;

  private Gson gson = new GsonBuilder().setPrettyPrinting().create();

  @Test
  public void add() {
    PlanCostSharesAddForm form = new PlanCostSharesAddForm();

  }

  @Test
  public void delete() {

//    planCostSharesService.delete(13);
  }

//  @Test
//  public ResponseVo<PlanCostSharesVo> query() {
//
//    return planCostSharesService.query(13);
//  }

  @Test
  public void testRedis() {
    planCostSharesService.testRedis();
  }

  @Test
  public void update(){
    PlanCostSharesVo planCostSharesVo = new PlanCostSharesVo();
    planCostSharesVo.set_org("utyutut");
    planCostSharesVo.setName("zz");
    ResponseVo<PlanCostSharesVo> responseVo = planCostSharesService.update("1234vxc2324sdf-502",planCostSharesVo);

    log.info("list={}", gson.toJson(responseVo));
  }
}