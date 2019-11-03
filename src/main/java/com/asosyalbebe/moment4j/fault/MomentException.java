package com.asosyalbebe.moment4j.fault;

/**
 * @author Serdar Kuzucu
 */
public class MomentException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MomentException(String message, Throwable t) {
        super(message, t);
    }

    public MomentException(String message) {
        super(message);
    }
}
