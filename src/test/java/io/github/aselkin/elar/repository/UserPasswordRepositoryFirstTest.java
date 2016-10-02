package io.github.aselkin.elar.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.github.aselkin.elar.App;
import io.github.aselkin.elar.repository.impl.LineInvalidFormatException;
import io.github.aselkin.elar.repository.impl.RepositoryException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {"classpath:application-password-repo-test-v1.properties"})
public class UserPasswordRepositoryFirstTest {

    @Autowired
    private UserPasswordRepository userPasswordRepository;

    @Test
    public void testGetPasswordById() throws RepositoryException {
        // Password is 123456
        final String expectedPassword = "e10adc3949ba59abbe56e057f20f883e";
        final long userId = 0L;
        final String actualPassword = this.userPasswordRepository.getPasswordById(userId);
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    public void testGetPasswordByIdThrowsLineInvalidFormatException() {
        try {
            final long userId = 5L;
            this.userPasswordRepository.getPasswordById(userId);
        } catch (RepositoryException ex) {
            final Throwable cause = ex.getCause();
            assertTrue(cause instanceof LineInvalidFormatException);

            final String expectedExceptionMessage =
                "User passwords storage has invalid content.";
            assertEquals(expectedExceptionMessage, ex.getMessage());

            final String expectedCauseMessage = "String \'3-e10adc3949ba59abbe56e057f20f883e\' "
                + "on line \'4\' has invalid format. Valid format is "
                + "\'^[0-9]+\t\\b([a-f\\d]{32}|[A-F\\d]{32})\\b$\'";
            assertEquals(expectedCauseMessage, cause.getMessage());
        }
    }
}