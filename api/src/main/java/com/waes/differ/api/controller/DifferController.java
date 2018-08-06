package com.waes.differ.api.controller;

import com.waes.differ.api.resource.DiffOutcomeResource;
import com.waes.differ.api.resource.DiffSide;
import com.waes.differ.api.service.DiffableService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1")
public class DifferController {

    private DiffableService diffableService;

    @Autowired
    public DifferController(DiffableService diffableService) {
        this.diffableService = diffableService;
    }

    @PostMapping(value = "/diff/{id}/{side}")
    @ApiOperation(value = "")
    public ResponseEntity save(
            @PathVariable(value = "id") String id,
            @PathVariable(value = "side") DiffSide side,
            @RequestBody String base64Body) {
        this.diffableService.save(id, side, base64Body);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/diff/{id}")
    @ApiOperation(value = "")
    public ResponseEntity<DiffOutcomeResource> diff(@PathVariable(value = "id") String id) {
        DiffOutcomeResource outcomeResource = this.diffableService.diff(id);
        return new ResponseEntity<>(outcomeResource, HttpStatus.OK);
    }

}
