package io.github.aselkin.elar.model;

/**
 * Interface to access information about user.
 *
 * @author Andrei Selkin
 */
public interface User {

    /**
     * Returns user name.
     * @return user name.
     */
    String getName();

    /**
     * Returns user login.
     * @return user login.
     */
    String getLogin();

    /**
     * Returns user password.
     * @return user password.
     */
    String getPassword();
}