package com.waes.differ.api.exception;

public class DiffableNotFoundException extends RuntimeException {

    private String id;

    public DiffableNotFoundException(String id) {
        super(String.format("Diffable with id %s not found", id));
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
