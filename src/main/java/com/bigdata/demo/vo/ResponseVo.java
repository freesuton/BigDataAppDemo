package com.bigdata.demo.vo;

import com.bigdata.demo.enums.ResponseEnum;
import lombok.Data;

@Data
public class ResponseVo<T> {

  private Integer status;

  private String msg;

  private T data;

  public ResponseVo(Integer status, String msg){
    this.status = status;
    this.msg = msg;
  }

  private ResponseVo(Integer status, T data) {
    this.status = status;
    this.data = data;
  }

  public static <T> ResponseVo<T> success(String msg){
    return new ResponseVo<>(0,msg);
  }
  public static <T> ResponseVo<T> success(T data) {
    return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), data);
  }
  public static <T> ResponseVo<T> error(ResponseEnum responseEnum,String msg) {
    return new ResponseVo<>(responseEnum.getCode(),responseEnum.getDesc());
  }
}
