package com.waes.differ.api.util.diff;

import com.github.difflib.algorithm.DiffException;
import com.waes.differ.api.util.diff.resource.DifferenceResource;
import com.waes.differ.api.util.diff.resource.DifferencesResource;
import org.junit.Assert;
import org.junit.Test;

public class DiffCalculatorTest {

    @Test
    public void shouldNotHaveDifferences() throws DiffException {
        String left = "abcabc";
        String right = "abcabc";

        DiffCalculator diffCalculator = new DiffCalculator(left, right);
        DifferencesResource differences = diffCalculator.calculate();
        Assert.assertTrue(differences.getDifferences().isEmpty());
    }

    @Test
    public void shouldHaveOneDeleteDifference() throws DiffException {
        String left = "abcabc";
        String right = "abcab";

        DiffCalculator diffCalculator = new DiffCalculator(left, right);
        DifferencesResource differences = diffCalculator.calculate();
        Assert.assertEquals(differences.getDifferences().size(), 1);
        Assert.assertEquals(differences.getDifferences().get(0).getDifferenceType(), DifferenceResource.DifferenceType.DELETE);
    }

    @Test
    public void shouldHaveOneInsertDifference() throws DiffException {
        String left = "abcabc";
        String right = "abcabcdef";

        DiffCalculator diffCalculator = new DiffCalculator(left, right);
        DifferencesResource differences = diffCalculator.calculate();
        Assert.assertEquals(differences.getDifferences().size(), 1);
        Assert.assertEquals(differences.getDifferences().get(0).getDifferenceType(), DifferenceResource.DifferenceType.INSERT);
    }

    @Test
    public void shouldHaveTwoInsertDifference() throws DiffException {
        String left = "abcabc";
        String right = "xyzabcabcdef";

        DiffCalculator diffCalculator = new DiffCalculator(left, right);
        DifferencesResource differences = diffCalculator.calculate();
        Assert.assertEquals(differences.getDifferences().size(), 2);
        Assert.assertEquals(differences.getDifferences().get(0).getDifferenceType(), DifferenceResource.DifferenceType.INSERT);
    }

}
