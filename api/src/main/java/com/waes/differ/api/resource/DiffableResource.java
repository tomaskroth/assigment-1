package com.waes.differ.api.resource;

public class DiffableResource {

    private String id;
    private String left;
    private String right;

    public DiffableResource() {
    }

    public DiffableResource(String id, String left, String right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

}
