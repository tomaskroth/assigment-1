package com.waes.differ.api.util.diff;

import com.github.difflib.patch.Delta;
import com.github.difflib.patch.Patch;
import com.waes.differ.api.util.diff.resource.BlockResource;
import com.waes.differ.api.util.diff.resource.DifferenceResource;
import com.waes.differ.api.util.diff.resource.DifferencesResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PatchToDifferenceAdapter {

    public DifferencesResource adapt(Patch<String> patch) {
        if (patch == null || patch.getDeltas() == null || patch.getDeltas().isEmpty()) {
            return new DifferencesResource(Collections.emptyList());
        }
        DifferencesResource differences = new DifferencesResource(new ArrayList<>());
        List<Delta<String>> deltas = patch.getDeltas();
        deltas.forEach(delta -> differences.getDifferences().add(this.from(delta)));
        return differences;
    }

    private DifferenceResource from(Delta<String> delta) {
        BlockResource original = null;
        if (delta.getOriginal() != null) {
            original = new BlockResource(
                    delta.getOriginal().getPosition(),
                    delta.getOriginal().getLines() != null && !delta.getOriginal().getLines().isEmpty() ?
                            delta.getOriginal().getLines().get(0) : null);
        }
        BlockResource revised = null;
        if (delta.getRevised() != null) {
            revised = new BlockResource(
                    delta.getRevised().getPosition(),
                    delta.getRevised().getLines() != null && !delta.getRevised().getLines().isEmpty() ?
                            delta.getRevised().getLines().get(0) : null);
        }
        return new DifferenceResource(
                DifferenceResource.DifferenceType.valueOf(delta.getType().name()),
                original,
                revised);
    }

}
