/*
 * Author: George Ebbinason
 */

package com.george.om.orderservice.dto.response;


import com.george.om.orderservice.exception.OrderServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OrderServiceException.EntityNotFoundException.class)
    public final ResponseEntity handleNotFoundExceptions(Exception ex, WebRequest request) {
        ResponseError error = ResponseError.newInstance(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }


}