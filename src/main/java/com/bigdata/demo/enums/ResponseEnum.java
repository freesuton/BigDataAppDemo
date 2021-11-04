package com.bigdata.demo.enums;

import lombok.Getter;

@Getter
public enum ResponseEnum {

  ERROR(-1, "Server Error"),

  SUCCESS(0, "Success"),

  PARAM_ERROR(3, "Param Error"),

  NO_OBJECT_ID(4, "No Object ID found"),
  ;

  Integer code;

  String desc;

  ResponseEnum(Integer code, String desc){
    this.code = code;
    this.desc = desc;
  }
}
