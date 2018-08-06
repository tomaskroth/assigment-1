package com.waes.differ.api.controller;


import com.waes.differ.api.exception.DiffCalculationException;
import com.waes.differ.api.exception.MissingComparisonSideException;
import com.waes.differ.api.exception.DiffableNotFoundException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class DifferControllerAdvice {

    @ExceptionHandler(MissingComparisonSideException.class)
    public ResponseEntity<VndErrors> missingSideException(MissingComparisonSideException e) {
        return error(e, HttpStatus.UNPROCESSABLE_ENTITY, e.getId());
    }

    @ExceptionHandler(DiffableNotFoundException.class)
    public ResponseEntity<VndErrors> notFoundException(final DiffableNotFoundException e) {
        return error(e, HttpStatus.NOT_FOUND, e.getId());
    }

    @ExceptionHandler(DiffCalculationException.class)
    public ResponseEntity<VndErrors> diffCalculationException(final DiffCalculationException e) {
        return error(e, HttpStatus.BAD_REQUEST, e.getId());
    }

    private ResponseEntity<VndErrors> error(Exception exception, HttpStatus httpStatus, String logRef) {
        String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
    }

}
