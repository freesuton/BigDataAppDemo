package com.bigdata.demo.service.impl;

import com.bigdata.demo.enums.ResponseEnum;
import com.bigdata.demo.form.PlanCostSharesAddForm;
import com.bigdata.demo.service.IPlanCostSharesService;
import com.bigdata.demo.vo.PlanCostSharesVo;
import com.bigdata.demo.vo.ResponseVo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PlanCostSharesServiceImpl implements IPlanCostSharesService {

  private final static String OBJECT_ID ="object_%d";

  @Autowired
  private StringRedisTemplate redisTemplate;

  private Gson gson = new Gson();

  @Override
  public ResponseVo<PlanCostSharesVo> addUseCase(String oid,PlanCostSharesAddForm form){

    HashOperations<String,String,String> opsForHash = redisTemplate.opsForHash();
    opsForHash.put("useCase",oid,gson.toJson(form));

    return ResponseVo.success("SUCCESS");
  }

  @Override
  public ResponseVo<PlanCostSharesVo> add2list(String oid,PlanCostSharesAddForm form){

    //write into redis
    HashOperations<String,String,String> opsForHash = redisTemplate.opsForHash();
    opsForHash.put("linkedPlanService",oid,gson.toJson(form));

    return ResponseVo.success("SUCCESS");
  }

  @Override
  public void delete(String oid){
    redisTemplate.opsForHash().delete("linkedPlanService",oid);
  }

  @Override
  public PlanCostSharesVo query(Integer oid){
    PlanCostSharesVo planCostSharesVo = new PlanCostSharesVo();


    return planCostSharesVo;
  }

  @Override
  public  ResponseVo<PlanCostSharesVo> update(String oid,PlanCostSharesVo planCostSharesVoForm){
    HashOperations<String,String,String> opsForHash = redisTemplate.opsForHash();
    String redisKey = "linkedPlanService";

    PlanCostSharesVo planCostSharesVo;
    String value = opsForHash.get(redisKey, oid);
    if (StringUtils.isEmpty(value)){
      //if object doesn't exist
      return ResponseVo.error(ResponseEnum.NO_OBJECT_ID,"Object doesn't exist");
    }
    //exist
    planCostSharesVo = gson.fromJson(value, PlanCostSharesVo.class);
    opsForHash.put(redisKey, oid,gson.toJson(planCostSharesVoForm));

    return ResponseVo.success(planCostSharesVoForm);
  }

  @Override
  public void testRedis(){
    System.out.println(redisTemplate.opsForHash().get("hash1","oid") + "-------------");
  }
}
