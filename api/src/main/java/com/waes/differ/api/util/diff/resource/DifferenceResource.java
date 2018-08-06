package com.waes.differ.api.util.diff.resource;

public class DifferenceResource {

    public enum DifferenceType {
        CHANGE, DELETE, INSERT, EQUAL
    }

    private DifferenceType differenceType;
    private BlockResource original;
    private BlockResource revised;

    public DifferenceResource() {
    }

    public DifferenceResource(DifferenceType differenceType, BlockResource original, BlockResource revised) {
        this.differenceType = differenceType;
        this.original = original;
        this.revised = revised;
    }

    public DifferenceType getDifferenceType() {
        return differenceType;
    }

    public void setDifferenceType(DifferenceType differenceType) {
        this.differenceType = differenceType;
    }

    public BlockResource getOriginal() {
        return original;
    }

    public void setOriginal(BlockResource original) {
        this.original = original;
    }

    public BlockResource getRevised() {
        return revised;
    }

    public void setRevised(BlockResource revised) {
        this.revised = revised;
    }

}
