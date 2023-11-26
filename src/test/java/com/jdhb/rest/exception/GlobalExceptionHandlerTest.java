package com.jdhb.rest.exception;

import com.jdhb.rest.model.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void handleValidationExceptions_ValidException_ReturnsBadRequest() {
        MethodArgumentNotValidException ex = createMethodArgumentNotValidException();

        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleValidationExceptions(ex, createWebRequest());

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCode().value());
        assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), responseEntity.getBody().getError());
        assertEquals("uri=/path", responseEntity.getBody().getPath());
    }

    @Test
    void handleEmployeeClientExceptions_ValidException_ReturnsInternalServerError() {
        EmployeeClientException ex = new EmployeeClientException("Error message");

        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleEmployeeClientExceptions(ex, createWebRequest());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getBody().getError());
        assertEquals("uri=/path", responseEntity.getBody().getPath());
        assertEquals(List.of("Error message"), responseEntity.getBody().getMessage());
    }

    private MethodArgumentNotValidException createMethodArgumentNotValidException() {
        return new MethodArgumentNotValidException((MethodParameter) null, new BeanPropertyBindingResult(null, "errors"));
    }

    private ServletWebRequest createWebRequest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/path");
        return new ServletWebRequest(request);
    }
}
