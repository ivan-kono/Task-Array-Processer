package com.project.app.validation.impl;

import com.project.app.validation.impl.NumericArrayLineValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayLineValidatorTest {

    private final NumericArrayLineValidator validator =
            new NumericArrayLineValidator();

    @Test
    void acceptsValidInput() {
        assertTrue(validator.isValid("1; 2; 3"));
        assertTrue(validator.isValid("1, 2, 3"));
        assertTrue(validator.isValid("3 4 7"));
        assertTrue(validator.isValid("-1.5, 2.25, 3e2"));
    }

    @Test
    void acceptsBlankLines() {
        assertTrue(validator.isValid(""));
        assertTrue(validator.isValid("   "));
    }

    @Test
    void rejectsInvalidCharactersAndNumbers() {
        assertFalse(validator.isValid("1y1 21 32"));
        assertFalse(validator.isValid("1, 2, x3, 6..5, 77"));
        assertFalse(validator.isValid("1, , 3"));
        assertFalse(validator.isValid("1, NaN, 3"));
        assertFalse(validator.isValid("1, Infinity, 3"));
    }
}
