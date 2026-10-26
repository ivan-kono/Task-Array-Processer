package com.project.app.validation.impl;

import java.util.regex.Pattern;
import com.project.app.validation.ArrayLineValidator;

public class NumericArrayLineValidator implements ArrayLineValidator {
    private static final Pattern NUMBER = Pattern.compile("[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:\\d+)?");
    @Override
    public boolean isValid(String line) {
        if (line == null || line.isBlank()) return false;
        String normalized = line.trim().replaceAll("(?<=\\d)\\s*(?=[+-]?\\d)", ",");
        normalized = normalized.replaceAll("(?<=\\d)\\s*[–—-]\\s*(?=\\d)", "");
        String[] tokens = normalized.split("[,;\\s]+", -1);
        if (tokens.length == 0) return false;
        for (String token : tokens) {
            if (token.isBlank() || !NUMBER.matcher(token).matches()) return false;
            try {
                if (!Double.isFinite(Double.parseDouble(token))) return false;
            } catch (NumberFormatException exception) {
                return false;
            }
        }
        return true;
    }
}
