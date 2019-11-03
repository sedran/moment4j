package com.asosyalbebe.moment4j.fault;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * @author Serdar Kuzucu
 */
public class MomentExceptionTest {

    @Test
    public void shouldContainMessageGivenInConstructor() {
        final String message = "Test exception message";
        final MomentException exception = new MomentException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void shouldContainGivenThrowableAndMessage() {
        final String message = "Test exception message";
        final RuntimeException runtimeException = new RuntimeException("Error!");
        final MomentException momentException = new MomentException(message, runtimeException);
        assertEquals(message, momentException.getMessage());
        assertSame(runtimeException, momentException.getCause());
    }
}
