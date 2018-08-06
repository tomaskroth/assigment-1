package com.waes.differ.api.resource;

import com.waes.differ.api.util.diff.resource.DifferencesResource;

public class DiffOutcomeResource {

    private String message;
    private DifferencesResource differences;
    private DiffableResource diffable;

    public DiffOutcomeResource(DiffableResource diffable, String message) {
        this.diffable = diffable;
        this.message = message;
    }

    public DiffOutcomeResource(DiffableResource diffable, DifferencesResource differences) {
        this.diffable = diffable;
        this.differences = differences;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DifferencesResource getDifferences() {
        return differences;
    }

    public void setDifferences(DifferencesResource differences) {
        this.differences = differences;
    }

    public DiffableResource getDiffable() {
        return diffable;
    }

    public void setDiffable(DiffableResource diffable) {
        this.diffable = diffable;
    }

}
