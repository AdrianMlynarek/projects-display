package com.memsource.projectdisplay.memsource.integration.exception;

public class MemsourceLoginFailedException extends RuntimeException {
    public MemsourceLoginFailedException(String errorMessage) {
        super(errorMessage);
    }
}
