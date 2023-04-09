package hu.deik.online_chess.service.impl;

import hu.deik.online_chess.populator.DBPopulator;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DBPopulatorService {

    private final List<DBPopulator> dbPopulators;

    public DBPopulatorService(final List<DBPopulator> dbPopulators) {
        this.dbPopulators = dbPopulators;
    }

    @PostConstruct
    public void populateDatabase() {
        log.info("Populates data base...");
        dbPopulators.forEach(DBPopulator::populateDatabase);
        log.info("Database populate process is finished.");
    }
}
