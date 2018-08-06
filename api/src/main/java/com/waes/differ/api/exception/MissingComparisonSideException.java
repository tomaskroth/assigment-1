package com.waes.differ.api.exception;

public class MissingComparisonSideException extends RuntimeException {

    private String id;

    public MissingComparisonSideException(String message, String id) {
        super(message);
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
