package com.project.app.io.impl;

import com.project.app.exception.ArrayDataException;
import com.project.app.io.ArrayFileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ArrayFileReaderImpl implements ArrayFileReader {
    @Override
    public List<String> readLines(Path path) throws ArrayDataException {
        try {
            return Files.readAllLines(path);
        } catch (IOException exception) {
            throw new ArrayDataException("Cannot read input file: " + path, exception);
        }
    }
}
