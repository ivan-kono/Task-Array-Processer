package com.project.app.service;

import com.project.app.exception.ArrayDataException;
import java.nio.file.Path;

public interface ArrayLoadingService {
    int load(Path path) throws ArrayDataException;
}
