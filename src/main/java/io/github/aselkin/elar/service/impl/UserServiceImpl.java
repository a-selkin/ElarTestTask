package io.github.aselkin.elar.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.aselkin.elar.model.User;
import io.github.aselkin.elar.model.UserWithEncryptedPassword;
import io.github.aselkin.elar.model.entities.UserInfo;
import io.github.aselkin.elar.repository.UserInfoRepository;
import io.github.aselkin.elar.repository.UserPasswordRepository;
import io.github.aselkin.elar.repository.impl.RepositoryException;
import io.github.aselkin.elar.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserPasswordRepository userPasswordRepository;

    @Override
    public User getUserByLogin(String login) throws BusinessException {
        User user = null;
        try {
            final UserInfo userInfo = userInfoRepository.getUserInfoByLogin(login);
            if (userInfo != null) {
                final long userId = userInfo.getId();
                final String password = userPasswordRepository.getPasswordById(userId);
                user = new UserWithEncryptedPassword(userInfo.getName(),
                    userInfo.getLogin(), password);
            }
            return user;
        }
        catch (RepositoryException ex) {
            LOG.error(ex.getMessage(), ex);
            throw new BusinessException(ex.getMessage(), ex);
        }
    }
}
