package com.alexfrndz.orderexam.controller;

import com.alexfrndz.orderexam.pojo.ErrorResponse;
import com.alexfrndz.orderexam.pojo.exception.ApiException;
import com.alexfrndz.orderexam.pojo.exception.DuplicateEntryException;
import com.alexfrndz.orderexam.pojo.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
public class BaseController {

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Exception> handleNoResultException(
            NoResultException nre) {

        return new ResponseEntity<Exception>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Exception> handleException(Exception e) {
        return new ResponseEntity<Exception>(e,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNotFoundError(HttpServletRequest req, NotFoundException exception) {
        return new ErrorResponse(exception.getErrorCode(), exception.getMessage());
    }


    @ExceptionHandler(DuplicateEntryException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse handleDuplicate(HttpServletRequest req, DuplicateEntryException exception) {
        return new ErrorResponse(exception.getErrorCode(), exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationFromAnnotation(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        FieldError error = result.getFieldError();
        return new ErrorResponse("INVALID_VALUE", error.getDefaultMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException exception) {
        return new ErrorResponse("INVALID_VALUE", exception.getMessage());
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(HttpMessageNotReadableException exception) {
        return new ErrorResponse("INVALID_VALUE", exception.getMostSpecificCause().getLocalizedMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(MethodArgumentTypeMismatchException exception) {
        return new ErrorResponse("INVALID_VALUE", exception.getMostSpecificCause().getLocalizedMessage());
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleAPIException(ApiException exception) {
        return new ErrorResponse(exception.getErrorCode(), exception.getLocalizedMessage());
    }


}

