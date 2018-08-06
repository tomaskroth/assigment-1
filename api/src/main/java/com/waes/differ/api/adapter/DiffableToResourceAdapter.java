package com.waes.differ.api.adapter;

import com.waes.differ.api.model.Diffable;
import com.waes.differ.api.resource.DiffableResource;
import org.springframework.stereotype.Component;

@Component
public class DiffableToResourceAdapter {

    public DiffableResource adapt(Diffable diffable) {
        return new DiffableResource(
                diffable.getDescription(),
                diffable.getContentLeft(),
                diffable.getContentRight());
    }
}
