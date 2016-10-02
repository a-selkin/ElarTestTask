package io.github.aselkin.elar.repository.impl;

/**
 * An exception which occurs if there is a problem accessing the repository.
 *
 * @author Andrei Selkin
 */
public class RepositoryException extends Exception {
    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
