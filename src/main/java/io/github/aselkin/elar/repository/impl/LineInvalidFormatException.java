package io.github.aselkin.elar.repository.impl;

/**
 * An exception which occurs if the target line has invalid format.
 *
 * @author Andrei Selkin
 */
public class LineInvalidFormatException extends Exception {

    /**
     * Creates an instance.
     * @param lineNo line number on which the exception occurred.
     * @param line target line.
     * @param validFormat valid format of the line.
     */
    public LineInvalidFormatException(int lineNo, String line, String validFormat) {
        super(
            String.format("String '%s' on line '%d' has invalid format."
            + " Valid format is '%s'", line, lineNo, validFormat)
        );
    }
}
