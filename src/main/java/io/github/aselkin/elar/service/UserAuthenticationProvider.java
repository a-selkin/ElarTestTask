package io.github.aselkin.elar.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import io.github.aselkin.elar.model.User;
import io.github.aselkin.elar.service.impl.BusinessException;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOG = Logger.getLogger(UserAuthenticationProvider.class);

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {

        final String login = authentication.getName();
        final String password = String.valueOf(authentication.getCredentials());

        final User user;
        try {
            user = userService.getUserByLogin(login);
        }
        catch (BusinessException ex) {
            LOG.error(ex.getMessage(), ex);
            throw new AuthenticationServiceException(ex.getMessage(), ex);
        }

        if (user == null || !user.getLogin().equals(login)) {
            throw new UsernameNotFoundException("Нет такого пользователя");
        }

        final String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!md5Password.equals(user.getPassword())) {
            throw new BadCredentialsException("Неверный пароль");
        }

        final List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(user, password, grantedAuths);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
