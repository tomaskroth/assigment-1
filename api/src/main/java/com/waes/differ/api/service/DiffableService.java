package com.waes.differ.api.service;

import com.github.difflib.algorithm.DiffException;
import com.waes.differ.api.adapter.DiffableToResourceAdapter;
import com.waes.differ.api.exception.DiffCalculationException;
import com.waes.differ.api.model.Diffable;
import com.waes.differ.api.repository.DiffableRepository;
import com.waes.differ.api.resource.DiffOutcomeResource;
import com.waes.differ.api.resource.DiffSide;
import com.waes.differ.api.exception.DiffableNotFoundException;
import com.waes.differ.api.exception.MissingComparisonSideException;
import com.waes.differ.api.resource.DiffableResource;
import com.waes.differ.api.util.diff.DiffCalculator;
import com.waes.differ.api.util.diff.resource.DifferencesResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiffableService {

    private DiffableRepository diffableRepository;
    private DiffableToResourceAdapter diffableToResourceAdapter;

    @Autowired
    public DiffableService(DiffableRepository diffableRepository, DiffableToResourceAdapter diffableToResourceAdapter) {
        this.diffableRepository = diffableRepository;
        this.diffableToResourceAdapter = diffableToResourceAdapter;
    }

    @Transactional(readOnly = false)
    public void save(String description, DiffSide side, String content) {
        Diffable diffable = this.diffableRepository.findByDescription(description);
        if (diffable == null) {
            diffable = new Diffable();
            diffable.setDescription(description);
        }
        if (side == DiffSide.left) {
            diffable.setContentLeft(content);
        } else {
            diffable.setContentRight(content);
        }
        this.diffableRepository.save(diffable);
    }

    @Transactional(readOnly = true)
    public DiffOutcomeResource diff(String description) {
        Diffable diffable = this.diffableRepository.findByDescription(description);
        if (diffable == null) {
            throw new DiffableNotFoundException(description);
        }
        String missingSideMessage = "Missing the %s side of comparison!";
        if (diffable.getContentLeft() == null) {
            throw new MissingComparisonSideException(String.format(missingSideMessage, DiffSide.left), description);
        }
        if (diffable.getContentRight() == null) {
            throw new MissingComparisonSideException(String.format(missingSideMessage, DiffSide.right), description);
        }

        DiffableResource diffableResource = this.diffableToResourceAdapter.adapt(diffable);

        if (diffable.getContentRight().length() != diffable.getContentLeft().length()) {
            return new DiffOutcomeResource(diffableResource, "Comparison sides have different sizes!");
        }
        if (diffable.getContentRight().equals(diffable.getContentLeft())) {
            return new DiffOutcomeResource(diffableResource, "Comparison sides are equal to each other!");
        }

        DiffCalculator diffCalculator = new DiffCalculator(diffable.getContentLeft(), diffable.getContentRight());

        DifferencesResource differences = null;
        try {
            differences = diffCalculator.calculate();
        } catch (DiffException e) {
            throw new DiffCalculationException(String.format("Error calculating diff of diffable %s", description), description);
        }

        return new DiffOutcomeResource(diffableResource, differences);
    }

}
