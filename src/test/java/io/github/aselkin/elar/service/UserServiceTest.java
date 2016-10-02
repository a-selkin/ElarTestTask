package io.github.aselkin.elar.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.github.aselkin.elar.App;
import io.github.aselkin.elar.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {"classpath:application-user-service-test.properties"})
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testGetUserById() throws Exception {
        final String expectedUserName = "Андрей";
        final String expectedLogin = "andrew";
        final String md5expectedPassword = "e10adc3949ba59abbe56e057f20f883e";
        final User actualUser = this.userService.getUserByLogin("andrew");
        Assert.assertEquals(expectedUserName, actualUser.getName());
        Assert.assertEquals(expectedLogin, actualUser.getLogin());
        Assert.assertEquals(md5expectedPassword, actualUser.getPassword());
    }
}