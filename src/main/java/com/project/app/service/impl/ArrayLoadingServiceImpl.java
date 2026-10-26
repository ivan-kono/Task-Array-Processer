package com.project.app.service.impl;

import com.project.app.exception.ArrayDataException;
import com.project.app.factory.ArrayFactory;
import com.project.app.io.ArrayFileReader;
import com.project.app.io.ArrayLineParser;
import com.project.app.repository.ArrayRepository;
import com.project.app.validation.ArrayLineValidator;
import com.project.app.entity.ArrayEntity;
import com.project.app.service.ArrayLoadingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ArrayLoadingServiceImpl implements ArrayLoadingService {
    private static final Logger LOGGER = LogManager.getLogger(ArrayLoadingServiceImpl.class);
    private final ArrayFileReader reader;
    private final ArrayLineParser parser;
    private final ArrayLineValidator validator;
    private final ArrayFactory factory;
    private final ArrayRepository repository;

    public ArrayLoadingServiceImpl(ArrayFileReader reader, ArrayLineParser parser,
                                      ArrayLineValidator validator, ArrayFactory factory, ArrayRepository repository) {
        this.reader = reader; this.parser = parser; this.validator = validator;
        this.factory = factory; this.repository = repository;
    }
    @Override public int load(Path path) throws ArrayDataException {
        List<String> lines = reader.readLines(path);
        AtomicInteger accepted = new AtomicInteger();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line == null || line.isBlank()) continue;
            if (!validator.isValid(line)) {
                LOGGER.warn("Skipping invalid data at line {}: {}", i + 1, line);
                continue;
            }
            try {
                double[] values = parser.parse(line);
                if (values.length == 0) continue;
                ArrayEntity entity = factory.create((i + 1), values);
                repository.add(entity);
                accepted.incrementAndGet();
                LOGGER.info("Loaded {}", entity.getId());
            } catch (RuntimeException exception) {
                LOGGER.warn("Skipping malformed data at line {}: {}", i + 1, line, exception);
            }
        }
        return accepted.get();
    }
}

