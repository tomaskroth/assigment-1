package com.waes.differ.api.util.diff.resource;

import java.util.List;

public class DifferencesResource {

    private List<DifferenceResource> differences;

    public DifferencesResource() {
    }

    public DifferencesResource(List<DifferenceResource> differences) {
        this.differences = differences;
    }

    public List<DifferenceResource> getDifferences() {
        return differences;
    }

    public void setDifferences(List<DifferenceResource> differences) {
        this.differences = differences;
    }

}
