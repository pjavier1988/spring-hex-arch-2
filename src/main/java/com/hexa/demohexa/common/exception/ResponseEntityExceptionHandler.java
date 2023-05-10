package com.hexa.demohexa.common.exception;

import com.hexa.demohexa.common.payloads.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResponseEntityExceptionHandler extends org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleItemNotFoundException(Exception ex, WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setCode(1);
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setData(null);
        errorDetails.setTimeStamp(LocalDateTime.now());
        errorDetails.setDetails(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

    }
}
