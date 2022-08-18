package com.juggernauts.todoapp.configurations.interceptors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

class RequestLoggingInterceptorTest {
    /**
     * Method under test: {@link RequestLoggingInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)}
     */
    @Test
    void testPreHandle() throws Exception {
        RequestLoggingInterceptor requestLoggingInterceptor = new RequestLoggingInterceptor();
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertTrue(requestLoggingInterceptor.preHandle(request, new Response(), "Handler"));
    }

    /**
     * Method under test: {@link RequestLoggingInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPreHandle2() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.juggernauts.todoapp.configurations.interceptors.RequestLoggingInterceptor.preHandle(RequestLoggingInterceptor.java:19)
        //   In order to prevent preHandle(HttpServletRequest, HttpServletResponse, Object)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   preHandle(HttpServletRequest, HttpServletResponse, Object).
        //   See https://diff.blue/R013 to resolve this issue.

        RequestLoggingInterceptor requestLoggingInterceptor = new RequestLoggingInterceptor();
        requestLoggingInterceptor.preHandle(null, new Response(), "Handler");
    }

    /**
     * Method under test: {@link RequestLoggingInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPreHandle3() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalStateException: Multipart request not initialized
        //       at org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest.initializeMultipart(AbstractMultipartHttpServletRequest.java:151)
        //       at org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest.getMultipartParameters(DefaultMultipartHttpServletRequest.java:174)
        //       at org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest.getParameterNames(DefaultMultipartHttpServletRequest.java:112)
        //       at org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest.getParameterMap(DefaultMultipartHttpServletRequest.java:126)
        //       at com.juggernauts.todoapp.configurations.interceptors.RequestLoggingInterceptor.preHandle(RequestLoggingInterceptor.java:19)
        //   In order to prevent preHandle(HttpServletRequest, HttpServletResponse, Object)
        //   from throwing IllegalStateException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   preHandle(HttpServletRequest, HttpServletResponse, Object).
        //   See https://diff.blue/R013 to resolve this issue.

        RequestLoggingInterceptor requestLoggingInterceptor = new RequestLoggingInterceptor();
        DefaultMultipartHttpServletRequest request = new DefaultMultipartHttpServletRequest(new MockHttpServletRequest());
        requestLoggingInterceptor.preHandle(request, new Response(), "Handler");
    }

    /**
     * Method under test: {@link RequestLoggingInterceptor#afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)}
     */
    @Test
    void testAfterCompletion() throws Exception {
        RequestLoggingInterceptor requestLoggingInterceptor = new RequestLoggingInterceptor();
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        Response response = new Response();
        requestLoggingInterceptor.afterCompletion(mockHttpServletRequest, response, "Handler", new Exception("foo"));
        assertFalse(mockHttpServletRequest.isRequestedSessionIdFromURL());
        assertTrue(mockHttpServletRequest.isRequestedSessionIdFromCookie());
        assertFalse(mockHttpServletRequest.isAsyncSupported());
        assertFalse(mockHttpServletRequest.isAsyncStarted());
        assertTrue(mockHttpServletRequest.isActive());
        assertTrue(mockHttpServletRequest.getSession() instanceof MockHttpSession);
        assertEquals("", mockHttpServletRequest.getServletPath());
        assertEquals(80, mockHttpServletRequest.getServerPort());
        assertEquals("localhost", mockHttpServletRequest.getServerName());
        assertEquals("http", mockHttpServletRequest.getScheme());
        assertEquals("", mockHttpServletRequest.getRequestURI());
        assertEquals(80, mockHttpServletRequest.getRemotePort());
        assertEquals("localhost", mockHttpServletRequest.getRemoteHost());
        assertEquals("HTTP/1.1", mockHttpServletRequest.getProtocol());
        assertEquals("", mockHttpServletRequest.getMethod());
        assertEquals(80, mockHttpServletRequest.getLocalPort());
        assertEquals("localhost", mockHttpServletRequest.getLocalName());
        assertTrue(mockHttpServletRequest.getInputStream() instanceof DelegatingServletInputStream);
        assertEquals(DispatcherType.REQUEST, mockHttpServletRequest.getDispatcherType());
        assertEquals("", mockHttpServletRequest.getContextPath());
        assertEquals(-1L, mockHttpServletRequest.getContentLengthLong());
    }

    /**
     * Method under test: {@link RequestLoggingInterceptor#afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)}
     */
    @Test
    void testAfterCompletion2() throws Exception {
        RequestLoggingInterceptor requestLoggingInterceptor = new RequestLoggingInterceptor();
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        requestLoggingInterceptor.afterCompletion(mockHttpServletRequest, new Response(), "42", null);
        assertFalse(mockHttpServletRequest.isRequestedSessionIdFromURL());
        assertTrue(mockHttpServletRequest.isRequestedSessionIdFromCookie());
        assertFalse(mockHttpServletRequest.isAsyncSupported());
        assertFalse(mockHttpServletRequest.isAsyncStarted());
        assertTrue(mockHttpServletRequest.isActive());
        assertTrue(mockHttpServletRequest.getSession() instanceof MockHttpSession);
        assertEquals("", mockHttpServletRequest.getServletPath());
        assertEquals(80, mockHttpServletRequest.getServerPort());
        assertEquals("localhost", mockHttpServletRequest.getServerName());
        assertEquals("http", mockHttpServletRequest.getScheme());
        assertEquals("", mockHttpServletRequest.getRequestURI());
        assertEquals(80, mockHttpServletRequest.getRemotePort());
        assertEquals("localhost", mockHttpServletRequest.getRemoteHost());
        assertEquals("HTTP/1.1", mockHttpServletRequest.getProtocol());
        assertEquals("", mockHttpServletRequest.getMethod());
        assertEquals(80, mockHttpServletRequest.getLocalPort());
        assertEquals("localhost", mockHttpServletRequest.getLocalName());
        assertTrue(mockHttpServletRequest.getInputStream() instanceof DelegatingServletInputStream);
        assertEquals(DispatcherType.REQUEST, mockHttpServletRequest.getDispatcherType());
        assertEquals("", mockHttpServletRequest.getContextPath());
        assertEquals(-1L, mockHttpServletRequest.getContentLengthLong());
    }

    /**
     * Method under test: {@link RequestLoggingInterceptor#afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAfterCompletion3() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.juggernauts.todoapp.configurations.interceptors.RequestLoggingInterceptor.afterCompletion(RequestLoggingInterceptor.java:26)
        //   In order to prevent afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception).
        //   See https://diff.blue/R013 to resolve this issue.

        RequestLoggingInterceptor requestLoggingInterceptor = new RequestLoggingInterceptor();
        Response response = new Response();
        requestLoggingInterceptor.afterCompletion(null, response, "Handler", new Exception("foo"));
    }

    /**
     * Method under test: {@link RequestLoggingInterceptor#afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAfterCompletion4() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.juggernauts.todoapp.configurations.interceptors.RequestLoggingInterceptor.afterCompletion(RequestLoggingInterceptor.java:28)
        //   In order to prevent afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception).
        //   See https://diff.blue/R013 to resolve this issue.

        RequestLoggingInterceptor requestLoggingInterceptor = new RequestLoggingInterceptor();
        requestLoggingInterceptor.afterCompletion(null, new Response(), "Handler", null);
    }
}

