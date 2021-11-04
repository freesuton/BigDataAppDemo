package com.bigdata.demo.controller;

import com.bigdata.demo.form.PlanCostSharesAddForm;
import com.bigdata.demo.form.SimpleForm;
import com.bigdata.demo.service.IPlanCostSharesService;
import com.bigdata.demo.vo.LinkedPlanCostSharesListVo;
import com.bigdata.demo.vo.PlanCostSharesVo;
import com.bigdata.demo.vo.ResponseVo;
import com.bigdata.demo.vo.UseCaseVo;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PlanCostSharesController {

  private final static String plan_REDIS_KEY_TEMPLATE = "planCost_%d";

  @Autowired
  private StringRedisTemplate redisTemplate;

  @Autowired
  private IPlanCostSharesService planCostSharesService;

  private Gson gson = new Gson();

  @PostMapping("/addUseCase")
  public ResponseVo<PlanCostSharesVo> addUsecase(@Valid @RequestBody PlanCostSharesAddForm planCostSharesAddForm){

    return planCostSharesService.addUseCase(planCostSharesAddForm.getObjectId(),planCostSharesAddForm);
  }

  @PostMapping("/planCostShares")
  public ResponseVo<PlanCostSharesVo> add(@Valid @RequestBody PlanCostSharesAddForm planCostSharesAddForm){

    return planCostSharesService.add2list(planCostSharesAddForm.getObjectId(),planCostSharesAddForm);
  }

  @DeleteMapping("/planCostSharesDelete/{oid}")
  public ResponseVo<PlanCostSharesVo> delete(@PathVariable String oid){
     planCostSharesService.delete(oid);
     return ResponseVo.success("SUCCESS");
  }


  @GetMapping("/allPlanCostShares")
  public ResponseEntity<Map> queryAll(){


    Map map1 = redisTemplate.opsForHash().entries("");
    Map map2 = redisTemplate.opsForHash().entries("");
    Map map3 = redisTemplate.opsForHash().entries("");
    Map<String, String> combineResultMap = new HashMap<String, String>();
    combineResultMap.putAll(map1);
    combineResultMap.putAll(map2);
    combineResultMap.putAll(map3);
//    return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    return ResponseEntity.ok().eTag("028d7d664f6cb75399622ead2005").body(combineResultMap);
  }

  //list all plan
  @GetMapping("/plan")
  public ResponseEntity<UseCaseVo> queryAll2(){
    HashOperations<String,String,String> opsForHash = redisTemplate.opsForHash();


    Map<String, String> entries = opsForHash.entries("linkedPlanService");

//
    UseCaseVo useCaseVo = new UseCaseVo();
    List<PlanCostSharesVo> planCostSharesVoList = new ArrayList<>();
    //test1

//    planCostSharesVoList.add(new PlanCostSharesVo("123"));
//    planCostSharesVoList.add(new PlanCostSharesVo("cc"));
    //test1

    //pass through and transfer data to PlanCostSharesVo
    for (Map.Entry<String, String> entry : entries.entrySet()){

      PlanCostSharesVo planCostSharesVo = gson.fromJson(entry.getValue(),PlanCostSharesVo.class);
      System.out.println("-------------");
      System.out.println(planCostSharesVo.getObjectId());
      System.out.println(planCostSharesVo.get_org());
      planCostSharesVoList.add(planCostSharesVo);
    }

    useCaseVo.setPlanCostSharesVo(gson.fromJson(opsForHash.get("useCase","1234vxc2324sdf-500"),PlanCostSharesVo.class));
    useCaseVo.setPlanCostSharesVoList(planCostSharesVoList);
    return ResponseEntity.ok().eTag("028d7d664f6cb75399622ead2005").body(useCaseVo);
  }


  @GetMapping("/planCostShares/{oid}")
  public ResponseEntity<Map> query( @PathVariable String oid,@RequestHeader HttpHeaders requestHeaders){
    PlanCostSharesVo planCostSharesVo = new PlanCostSharesVo();


    Map map1 = redisTemplate.opsForHash().entries(oid);

    String currentETag = "ETAG" + oid;
    String reqETag = requestHeaders.getFirst("If-None-Match");
    System.out.println(reqETag);
    if (reqETag == null || !reqETag.equals(currentETag)){
      return ResponseEntity.ok().eTag(currentETag).body(map1);
    }else {
      return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

   

//    return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//    return ResponseEntity.ok().eTag("ETAG"+"028d7d664f6cb75399622ead2005").body(map1);
  }

  @PutMapping("/updatePlan/{oid}")
  public ResponseVo<PlanCostSharesVo> update(@PathVariable String oid, @Valid @RequestBody PlanCostSharesVo planCostSharesVoForm ){
    System.out.println(planCostSharesVoForm.get_org());
    System.out.println("planCostSharesVoForm.get_org()");
      return planCostSharesService.update(oid,planCostSharesVoForm);
  }

  @PostMapping("/update3")
  public Integer update2(){
    return 8;
  }


}
