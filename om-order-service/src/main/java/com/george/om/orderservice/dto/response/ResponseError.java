/*
 * Author: George Ebbinason
 */

package com.george.om.orderservice.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseError implements Serializable {

    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus;

    private String message;

    public static ResponseError newInstance(HttpStatus httpStatus, String message) {
        ResponseError apiError = new ResponseError();
        apiError.setHttpStatus(httpStatus);
        apiError.setMessage(message);
        return apiError;
    }
}