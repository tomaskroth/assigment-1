package com.waes.differ.api.repository;

import com.waes.differ.api.model.Diffable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiffableRepository extends CrudRepository<Diffable, Integer> {

    Diffable findByDescription(String description);
}
