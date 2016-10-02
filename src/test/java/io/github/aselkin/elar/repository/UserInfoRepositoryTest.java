package io.github.aselkin.elar.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.github.aselkin.elar.App;
import io.github.aselkin.elar.model.entities.UserInfo;
import io.github.aselkin.elar.repository.impl.RepositoryException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {"classpath:application-user-info-repo-test.properties"})
public class UserInfoRepositoryTest {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    public void testGetUserInfoById() throws RepositoryException {
        final long expectedId = 0L;
        final String expectedLogin = "andrew";
        final String expectedName = "Андрей";
        final UserInfo actualUserInfo = userInfoRepository.getUserInfoByLogin(expectedLogin);
        assertEquals(expectedId, actualUserInfo.getId());
        assertEquals(expectedLogin, actualUserInfo.getLogin());
        assertEquals(expectedName, actualUserInfo.getName());
    }

    @Test
    public void testGetUserInfoByIdNotFound() throws RepositoryException {
        final String expectedLogin = "notfounduserlogin";
        final UserInfo expectedInfo = null;
        final UserInfo actualUserInfo = userInfoRepository.getUserInfoByLogin(expectedLogin);
        assertEquals(expectedInfo, actualUserInfo);
    }
}