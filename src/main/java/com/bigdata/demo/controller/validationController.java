package com.bigdata.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class validationController {

  @GetMapping("/")
  public String helleWorld(){
    return "Hello World";
  }

  @GetMapping("/restricted")
  public String restricted(){
    return "to see this text you need to be logged in!";
  }
}
