package io.github.aselkin.elar.repository.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import io.github.aselkin.elar.repository.UserPasswordRepository;

/**
 * Represents user password repository stored in a file with the following format:
 * ID[Tab]PASS_MD5,
 * where
 *   ID - user id;
 *   [Tab] - tab character;
 *   PASS_MD5 - MD5 password cache.
 *
 * @author Andrei Selkin
 */
@Repository
public class UserPasswordFileRepository  implements UserPasswordRepository {

    private static final Logger LOG = Logger.getLogger(UserPasswordFileRepository.class);
    private static final String VALID_LINE_FORMAT = "^[0-9]+\t\\b([a-f\\d]{32}|[A-F\\d]{32})\\b$";

    @Value("classpath:${password.storage.path}")
    private Resource storage;

    @Override
    public String getPasswordById(long id) throws RepositoryException {
        try {
            return searchPasswordByUserId(id, storage.getFile().toPath());
        }
        catch (IOException ex) {
            final String exMessage = "Exception was happened while accessing passwords storage.";
            LOG.error(exMessage, ex);
            throw new RepositoryException(exMessage, ex);
        }
        catch (LineInvalidFormatException ex) {
            final String exMessage = "User passwords storage has invalid content.";
            LOG.error(exMessage, ex);
            throw new RepositoryException(exMessage, ex);
        }
    }

    private static String searchPasswordByUserId(long id, Path path)
            throws IOException, LineInvalidFormatException {
        String password = null;
        final List<String> lines = Files.readAllLines(path);
        for (int i = 0; i < lines.size(); i++) {
            final String line = lines.get(i);
            if (hasValidFormat(line)) {
                final String[] lineInfo = line.split("\\t");
                final long userId = Long.valueOf(lineInfo[0]);
                if (Long.compare(userId, id) == 0) {
                    password = lineInfo[1];
                    break;
                }
            }
            else {
                final int lineNo = i + 1;
                throw new LineInvalidFormatException(lineNo, line, VALID_LINE_FORMAT);
            }
        }
        return password;
    }

    private static boolean hasValidFormat(String line) {
        return line.matches(VALID_LINE_FORMAT);
    }
}
