package com.bigdata.demo.exception;

import static com.bigdata.demo.enums.ResponseEnum.ERROR;

import com.bigdata.demo.enums.ResponseEnum;
import com.bigdata.demo.vo.ResponseVo;
import java.util.Objects;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RuntimeExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  @ResponseBody
//	@ResponseStatus(HttpStatus.FORBIDDEN)
  public ResponseVo handle(RuntimeException e) {
    return ResponseVo.error(ERROR, e.getMessage());
  }


  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public ResponseVo notValidExceptionHandle(MethodArgumentNotValidException e){

    BindingResult bindingResult = e.getBindingResult();
    Objects.requireNonNull(bindingResult.getFieldError());
    return ResponseVo.error(ResponseEnum.NO_OBJECT_ID,
        bindingResult.getFieldError().getField() + " " +
        bindingResult.getFieldError().getDefaultMessage());
  }
}
