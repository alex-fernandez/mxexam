package com.alexfrndz.orderexam.controller;

import com.alexfrndz.orderexam.pojo.exception.ApiException;
import com.alexfrndz.orderexam.pojo.exception.DuplicateEntryException;
import com.alexfrndz.orderexam.pojo.exception.ErrorJson;
import com.alexfrndz.orderexam.pojo.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
public class BaseController {

    @Autowired
    private ErrorAttributes errorAttributes;


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
    public ErrorJson handleNotFoundError(HttpServletRequest req) {
        return emitErrorJson(HttpStatus.NOT_FOUND, req, false);
    }


    @ExceptionHandler(DuplicateEntryException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorJson handleDuplicate(HttpServletRequest req) {
        return emitErrorJson(HttpStatus.CONFLICT, req, false);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorJson handleValidationFromAnnotation(HttpServletRequest req) {
        // BindingResult result = exception.getBindingResult();
        // FieldError error = result.getFieldError();
        return emitErrorJson(HttpStatus.BAD_REQUEST, req, false);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorJson handleIllegalArgumentException(HttpServletRequest req) {
        return emitErrorJson(HttpStatus.BAD_REQUEST, req, false);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorJson handleIllegalArgumentExceptionForNotReadable(HttpServletRequest req) {
        return emitErrorJson(HttpStatus.BAD_REQUEST, req, false);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorJson handleIllegalArgumentExceptionTypeMismatch(HttpServletRequest req) {
        //  return new ErrorResponse("INVALID_VALUE", exception.getMostSpecificCause().getLocalizedMessage());
        return emitErrorJson(HttpStatus.BAD_REQUEST, req, false);
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorJson handleAPIException(HttpServletRequest req) {

        return emitErrorJson(HttpStatus.BAD_REQUEST, req, false);
    }


    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }

    private ErrorJson emitErrorJson(HttpStatus status, HttpServletRequest request, boolean includeStackTrace) {
        return new ErrorJson(status.value(), getErrorAttributes(request, includeStackTrace));
    }
}
