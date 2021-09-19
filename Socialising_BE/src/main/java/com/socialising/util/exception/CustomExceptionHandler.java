package com.socialising.util.exception;

import com.socialising.constant.SystemConstant;
import com.socialising.dto.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleAllExceptions(Exception ex, WebRequest request) {
        ResponseDTO result = new ResponseDTO();
        result.setMessage(SystemConstant.FAIL_MESSAGE);
        result.setData(null);
        result.setDetailMessage(ex.getMessage());
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
