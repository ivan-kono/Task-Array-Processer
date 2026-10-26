package com.project.app.io;

import com.project.app.exception.ArrayDataException;

import java.nio.file.Path;
import java.util.List;

public interface ArrayFileReader {
    public List<String> readLines(Path path) throws ArrayDataException;
}
