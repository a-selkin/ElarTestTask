package io.github.aselkin.elar.repository;

import io.github.aselkin.elar.model.entities.UserInfo;
import io.github.aselkin.elar.repository.impl.RepositoryException;

/**
 * Represents an interface to access user info repository.
 *
 * @author Andrei Selkin
 */
public interface UserInfoRepository {

    /**
     * Returns {@link UserInfo} instance.
     * @param login user login.
     * @return {@link UserInfo} instance.
     * @throws RepositoryException if an exception occurs while accessing the repository.
     */
    UserInfo getUserInfoByLogin(String login) throws RepositoryException;
}
