package com.bigdata.demo.form;

import com.bigdata.demo.vo.PlanCostSharesVo;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Add Information
 */
@Document(indexName = "plan")
@Data
public class PlanCostSharesAddForm {


  private PlanCostSharesVo planCostSharesVo;

  private Integer deductible;

  private String _org;

  private Integer copay;

  @NotEmpty
  @Id
  private String objectId;

  private String objectType;

  private String name;

  private String planType;

  private String planCostShares;

  private Date creationDate;


}
