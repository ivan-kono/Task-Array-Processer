package com.project.app.repository.impl;

import com.project.app.factory.impl.ArrayFactoryImpl;
import com.project.app.repository.impl.ArrayRepositoryImpl;
import com.project.app.warehouse.Warehouse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayRepositoryObserverTest {

    private final ArrayRepositoryImpl repository =
            ArrayRepositoryImpl.getInstance();

    private final Warehouse warehouse = Warehouse.getInstance();

    private final ArrayFactoryImpl factory =
            new ArrayFactoryImpl();

    @AfterEach
    void cleanUp() {
        repository.clear();
    }

    @Test
    void recalculatesMetricsAfterElementChange() {
        var observableArray = factory.create(1, new double[]{2, 4});
        repository.add(observableArray);

        assertEquals(
                6.0,
                warehouse.get(1).sum().orElseThrow()
        );

        observableArray.setValue(0, 10);

        assertEquals(
                14.0,
                warehouse.get(1).sum().orElseThrow()
        );
        assertEquals(
                7.0,
                warehouse.get(1).average().orElseThrow()
        );
        assertEquals(
                4.0,
                warehouse.get(1).min().orElseThrow()
        );
        assertEquals(
                10.0,
                warehouse.get(1).max().orElseThrow()
        );
    }

    @Test
    void removesMetricsWhenArrayIsRemoved() {
        var removableArray = factory.create(2, new double[]{1, 2, 3});
        repository.add(removableArray);

        assertNotNull(warehouse.get(2));

        assertTrue(repository.remove(2));

        assertNull(warehouse.get(2));
        assertFalse(repository.findById(2).isPresent());
    }

    @Test
    void rejectsDuplicateIds() {
        repository.add(factory.create(3, new double[]{1}));

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.add(
                        factory.create(3, new double[]{2})
                )
        );
    }
}

