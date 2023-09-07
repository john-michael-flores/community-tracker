package com.membermanagement.Member.Management.common.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.membermanagement.Member.Management.common.dto.ApiResponse;
import com.membermanagement.Member.Management.common.util.StringUtil;
import com.membermanagement.Member.Management.exception.ComponentAlreadyPresentException;
import com.membermanagement.Member.Management.exception.PeopleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.*;

@Slf4j
@ControllerAdvice
@RestController
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> errors.add(error.getDefaultMessage()));

        ApiResponse<List<String>> apiError = new ApiResponse<>(false,
                "validation.issue", null, errors);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle missing header.
     */
    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.handleOverrideRequiredException("missing.header.issue", HttpStatus.BAD_REQUEST, ex);
    }

    /**
     * Handle missing required parameters on endpoint.
     *
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.handleOverrideRequiredException("missing.require.parameter", HttpStatus.BAD_REQUEST, ex);
    }

    /**
     * Handle NotWritable usually happens on conflicting type during copyProperties
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.handleOverrideRequiredException("conflict.dto.type", HttpStatus.BAD_REQUEST, ex);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
                                                                      HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.handleOverrideRequiredException("media.type.not.acceptable", HttpStatus.BAD_REQUEST, ex);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.handleOverrideRequiredException("media.type.not.supported", HttpStatus.BAD_REQUEST, ex);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.handleOverrideRequiredException("request.method.not.supported", HttpStatus.BAD_REQUEST, ex);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
                                                                        HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.handleOverrideRequiredException("async.request.timeout", HttpStatus.BAD_REQUEST, ex);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        return this.handleOverrideRequiredException("no.handler.found", HttpStatus.BAD_REQUEST, ex);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.handleOverrideRequiredException("missing.servlet.request.part", HttpStatus.BAD_REQUEST, ex);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.handleOverrideRequiredException("http.message.not.readable", HttpStatus.BAD_REQUEST, ex);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.handleOverrideRequiredException("conversion.not.supported", HttpStatus.BAD_REQUEST, ex);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        return this.handleOverrideRequiredException("missing.path.variable", HttpStatus.BAD_REQUEST, ex);
    }

    private ResponseEntity<Object> handleOverrideRequiredException(String errorCode, HttpStatus status, Exception ex) {

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        ApiResponse<List<String>> apiError = new ApiResponse<>(false, errorCode, null,
                errors);

        return new ResponseEntity<Object>(apiError, status);
    }

    private ResponseEntity<ApiResponse<String>> handleException(Exception ex, HttpStatus status,
                                                                   String optionalErrorCode, boolean showRawException) {

        List<String> errors = new ArrayList<>();

        String error = ex.getMessage();
        if (!StringUtil.isBlank(optionalErrorCode)) {
            error = optionalErrorCode;
        }

        errors.add(error);

        if (showRawException) {
            errors.add(ex.getMessage());
        }

        ApiResponse<String> apiError = new ApiResponse<>(false, error, null, errors);
        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(value = { InvalidFormatException.class })
    public ResponseEntity<ApiResponse<String>> handleInvalidFormat(InvalidFormatException ex) {
        return this.handleException(ex, HttpStatus.BAD_REQUEST, "invalid.format", true);
    }

    @ExceptionHandler(value = { MismatchedInputException.class })
    public ResponseEntity<ApiResponse<String>> handleInputMismatch(MismatchedInputException ex) {
        return this.handleException(ex, HttpStatus.BAD_REQUEST, "mismatch.input", true);
    }


    /**
     * Explicitly declared for Apache Commons Validation
     *
     * validate.isTrue() will throw IllegalArgumentException
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = { IllegalArgumentException.class })
    protected ResponseEntity<ApiResponse<String>> interceptIllegalArgException(IllegalArgumentException ex,
                                                                                  WebRequest request) {
        return this.handleException(ex, HttpStatus.BAD_REQUEST, "", false);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ApiResponse<Exception>> handleGeneralExceptionError(Exception ex) {

        log.error("Unhandled Server Exception", ex);

        String error = "something.went.wrong";

        ApiResponse<Exception> apiError = new ApiResponse<>(false, error, null, Collections.emptyList());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(value = { PeopleNotFoundException.class })
    public ResponseEntity<ApiResponse<String>> interceptCustomException(PeopleNotFoundException ex) {
        return this.handleException(ex, HttpStatus.NOT_FOUND, "", false);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<String>> exception3(Exception ex) {
        return this.handleException(ex, HttpStatus.BAD_REQUEST, "Value is invalid.", false);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<String>> constraintViolationException(ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();

        ex.getConstraintViolations().forEach(cv -> errors.add(cv.getMessage()));

        Map<String, List<String>> result = new HashMap<>();

        result.put("errors", errors);
        return this.handleException(ex, HttpStatus.BAD_REQUEST, result.get("errors").toString(), false);



    }
}