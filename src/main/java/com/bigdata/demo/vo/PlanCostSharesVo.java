package com.bigdata.demo.vo;

import java.util.Date;
import lombok.Data;

@Data
public class PlanCostSharesVo {

  private Integer deductible;

  private String _org;

  private Integer copay;

  private String objectId;

  private String objectType;

  private String name;

  private String planType;

  private String planCostShares;

  private Date creationDate;

  public PlanCostSharesVo(String oid){
    this.objectId = oid;
  }

  public PlanCostSharesVo( ){
  }
}
