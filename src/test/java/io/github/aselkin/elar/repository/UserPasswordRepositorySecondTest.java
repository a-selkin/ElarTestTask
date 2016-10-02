package io.github.aselkin.elar.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.github.aselkin.elar.App;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {"classpath:application-password-repo-test-v2.properties"})
public class UserPasswordRepositorySecondTest {

    @Autowired
    private UserPasswordRepository userPasswordRepository;

    @Test
    public void testGetPasswordByIdNotFound() throws Exception {
        final String expectedPassword = null;
        final long userId = 10L;
        final String actualPassword = this.userPasswordRepository.getPasswordById(userId);
        assertEquals(expectedPassword, actualPassword);
    }
}