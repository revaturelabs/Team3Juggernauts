package com.juggernauts.todoapp.advice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

class ExceptionHandlerAdviceTest {

    /**
     * Method under test: {@link ExceptionHandlerAdvice#handle(Exception, HttpServletRequest, HttpServletResponse)}
     */

    @Test
    void testHandle() {
        ExceptionHandlerAdvice exceptionHandlerAdvice = new ExceptionHandlerAdvice();
        Exception ex = new Exception("An error occurred");
        MockHttpServletRequest request = new MockHttpServletRequest();
        ResponseEntity<Object> actualHandleResult = exceptionHandlerAdvice.handle(ex, request, new Response());
        assertNull(actualHandleResult.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualHandleResult.getStatusCode());
        assertTrue(actualHandleResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ExceptionHandlerAdvice#handle(Exception, HttpServletRequest, HttpServletResponse)}
     */

    @Test
    void testHandle2() {
        ExceptionHandlerAdvice exceptionHandlerAdvice = new ExceptionHandlerAdvice();
        NullPointerException ex = new NullPointerException("foo");
        MockHttpServletRequest request = new MockHttpServletRequest();
        ResponseEntity<Object> actualHandleResult = exceptionHandlerAdvice.handle(ex, request, new Response());
        assertNull(actualHandleResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleResult.getStatusCode());
        assertTrue(actualHandleResult.getHeaders().isEmpty());
    }
}

