package io.github.aselkin.elar.repository;

import io.github.aselkin.elar.repository.impl.RepositoryException;

/**
 * Represents an interface to access user password repository.
 *
 * @author Andrei Selkin
 */
public interface UserPasswordRepository {

    /**
     * Returns user password by user's id in {@link String} form.
     * @param id user's id.
     * @return user password.
     * @throws RepositoryException if an exception occurs while accessing the repository.
     */
    String getPasswordById(long id) throws RepositoryException;
}
