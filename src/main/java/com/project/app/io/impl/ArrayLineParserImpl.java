package com.project.app.io.impl;

import com.project.app.io.ArrayLineParser;
import java.util.ArrayList;
import java.util.List;

public class ArrayLineParserImpl implements ArrayLineParser {
    @Override
    public double[] parse(String line) {
        if (line == null || line.isBlank()) return new double[0];
        String normalized = line.trim().replaceAll("(?<=\\d)\\s*[–—-]\\s*(?=[+-]?\\d)", ",");
        normalized = normalized.replaceAll("(?<=\\d)\\s*[–—-]\\s*$", "");
        String[] tokens = normalized.split("[,;\\s]+");
        List<Double> values = new ArrayList<>();
        for (String token : tokens) values.add(Double.parseDouble(token));
        double[] result = new double[values.size()];
        for (int i = 0; i < values.size(); i++) result[i] = values.get(i);
        return result;
    }
}
