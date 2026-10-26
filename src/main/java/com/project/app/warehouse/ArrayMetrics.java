package com.project.app.warehouse;

import java.util.OptionalDouble;

public record ArrayMetrics(OptionalDouble min, OptionalDouble max, OptionalDouble sum, OptionalDouble average, int size) { }

