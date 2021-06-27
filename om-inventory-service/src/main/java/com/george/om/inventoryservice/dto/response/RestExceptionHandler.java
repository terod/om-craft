package com.george.om.inventoryservice.dto.response;

import com.george.om.inventoryservice.exception.InventoryServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InventoryServiceException.EntityNotFoundException.class)
    public final ResponseEntity handleNotFoundExceptions(Exception ex, WebRequest request) {
        ResponseError error = ResponseError.newInstance(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }


}