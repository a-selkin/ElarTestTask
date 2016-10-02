package io.github.aselkin.elar.service;

import io.github.aselkin.elar.model.User;
import io.github.aselkin.elar.service.impl.BusinessException;

/**
 * Represents the service to perform operations with users.
 *
 * @author Andrei Selkin
 */
public interface UserService {

    /**
     * Returns {@link User} by login.
     * @param login user login.
     * @return {@link User}.
     */
    User getUserByLogin(String login) throws BusinessException, BusinessException;
}
