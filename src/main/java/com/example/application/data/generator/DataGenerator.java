package com.example.application.data.generator;

import com.example.application.data.entity.DataDao;
import com.example.application.data.service.DataDaoRepository;
import com.example.application.services.FileReader;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@SpringComponent
public class DataGenerator {

    static final String ODS_FILE = "./src/main/resources/Baza_dr.ods";

    @Bean
    public CommandLineRunner loadData(DataDaoRepository dataDaoRepository) {
        return args -> {
            var reader = FileReader.getReader(ODS_FILE);
            List<DataDao> read = reader.read(ODS_FILE);
            dataDaoRepository.saveAll(read);
        };
    }
}