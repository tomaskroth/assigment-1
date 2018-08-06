package com.waes.differ.api.util.diff;

import com.github.difflib.DiffUtils;
import com.github.difflib.algorithm.DiffException;
import com.github.difflib.patch.Patch;
import com.waes.differ.api.util.diff.resource.DifferencesResource;

public class DiffCalculator {

    private static final PatchToDifferenceAdapter PATCH_TO_DIFFERENCE_ADAPTER = new PatchToDifferenceAdapter();

    private String left;
    private String right;

    public DiffCalculator(String left, String right) {
        this.left = left;
        this.right = right;
    }

    public DifferencesResource calculate() throws DiffException {
        Patch<String> patch = DiffUtils.diffInline(this.left, this.right);
        return PATCH_TO_DIFFERENCE_ADAPTER.adapt(patch);
    }

}
