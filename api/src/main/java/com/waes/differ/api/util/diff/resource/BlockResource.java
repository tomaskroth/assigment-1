package com.waes.differ.api.util.diff.resource;

public class BlockResource {

    private int position;
    private String value;

    public BlockResource() {
    }

    public BlockResource(int position, String value) {
        this.position = position;
        this.value = value;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
