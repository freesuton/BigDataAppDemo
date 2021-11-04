package com.bigdata.demo.vo;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class UseCaseVo {

  public PlanCostSharesVo planCostSharesVo;

  public List<PlanCostSharesVo> planCostSharesVoList;

  public String _org;

  public String objectId;

  public String planType;

  public Date creationDate;

}
