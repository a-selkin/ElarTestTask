package io.github.aselkin.elar.model;

/**
 * Represents the user with encrypted password.
 * User's password is encrypted with MD5 algorithm.
 * This class is immutable.
 *
 * @author Andrei Selkin
 */
public class UserWithEncryptedPassword implements User {

    private final String name;
    private final String login;
    private final String password;

    /**
     * Creates {@link UserWithEncryptedPassword} instance.
     * @param name user name.
     * @param login user login.
     * @param password encrypted user password.
     */
    public UserWithEncryptedPassword(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
