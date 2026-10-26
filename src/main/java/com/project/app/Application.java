package com.project.app;

import com.project.app.factory.impl.ArrayFactoryImpl;
import com.project.app.io.impl.ArrayFileReaderImpl;
import com.project.app.io.impl.ArrayLineParserImpl;
import com.project.app.repository.impl.ArrayRepositoryImpl;
import com.project.app.service.impl.ArrayLoadingServiceImpl;
import com.project.app.service.impl.ArrayStatisticsServiceImpl;
import com.project.app.service.impl.ArraySortServiceImpl;
import com.project.app.validation.impl.NumericArrayLineValidator;
import com.project.app.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.nio.file.Path;
import java.util.Arrays;

public class Application {
    private static final String FILE_PATH = ("data/arrays.txt");
    private static final Logger LOGGER = LogManager.getLogger(Application.class);
    public void run() throws Exception {
        var repository = ArrayRepositoryImpl.getInstance();
        repository.clear();
        var loader = new ArrayLoadingServiceImpl(new ArrayFileReaderImpl(), new ArrayLineParserImpl(),
                new NumericArrayLineValidator(), new ArrayFactoryImpl(), repository);
        int count = loader.load(Path.of(FILE_PATH));
        var statistics = new ArrayStatisticsServiceImpl();
        var sorter = new ArraySortServiceImpl();
        LOGGER.info("Loaded {} arrays", count);
        for (var entity : repository.findAll()) {
            LOGGER.info("{} min={} max={} sum={} avg={} bubble={} merge={}",
                    entity.getId(), statistics.min(entity), statistics.max(entity),
                    statistics.sum(entity), statistics.average(entity),
                    Arrays.toString(sorter.bubbleSort(entity)), Arrays.toString(sorter.mergeSort(entity)));
        }
        LOGGER.info("Warehouse entries: {}", Warehouse.getInstance().getAll().size());
    }
    public static void main(String[] args) throws Exception { new Application().run(); }
}