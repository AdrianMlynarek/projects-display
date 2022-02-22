package com.memsource.projectdisplay.controller;

import com.memsource.projectdisplay.memsource.integration.exception.MemsourceLoginFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(value = {MemsourceLoginFailedException.class})
    protected ResponseEntity<String> handleFailedMemsourceLogin() {
        return new ResponseEntity<>("Could not login to Memsource account", HttpStatus.UNAUTHORIZED);
    }
}
