package io.github.aselkin.elar.service.impl;

/**
 * An exception which occurs if there is a problem in business service.
 *
 * @author Andrei Selkin
 */
public class BusinessException extends Exception {
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
