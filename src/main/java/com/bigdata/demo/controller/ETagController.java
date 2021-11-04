package com.bigdata.demo.controller;

import com.sun.deploy.net.HttpResponse;
import com.sun.deploy.net.MessageHeader;
import java.io.BufferedInputStream;
import java.net.URL;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class ETagController {

  @Autowired
  private StringRedisTemplate redisTemplate;

  @RequestMapping("/hello/{oid}")
  public ResponseEntity<String> hello(@RequestHeader HttpHeaders requestHeaders, @PathVariable Integer oid){

    HttpHeaders responseHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    Object rstObject = redisTemplate.opsForHash().get("hash1","oid");
    if (rstObject != null){
      String currentETag = "111";
      responseHeaders.setETag(currentETag);
      String ETag = requestHeaders.getFirst("If-None-Match");
      if (ETag == null || !ETag.equals(currentETag)){
        return ResponseEntity.ok().headers(requestHeaders).body("rstObject");
      }
      else return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @RequestMapping("/hello22")
  public String hello(@RequestHeader HttpHeaders requestHeaders){
    System.out.println(requestHeaders.getFirst("If-None-Match"));
    HttpHeaders responseHeaders = new HttpHeaders();
    requestHeaders.setETag(("dsfdsfdsfd").toString());
    System.out.println(responseHeaders.getETag());
    return "HH";
  }

  @GetMapping("/testETag/{id}")
  public ResponseEntity<String> test(@PathVariable Integer id, @RequestHeader HttpHeaders requestHeaders){
    System.out.println(requestHeaders.getFirst("If-None-Match"));
    return ResponseEntity.ok().eTag("dsfdf").body("asdasd");
  }

}
