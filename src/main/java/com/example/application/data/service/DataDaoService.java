package com.example.application.data.service;

import com.example.application.data.entity.DataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.artur.helpers.CrudService;

public class DataDaoService extends CrudService<DataDao, Integer> {

    private DataDaoRepository repository;

    public DataDaoService(@Autowired DataDaoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected DataDaoRepository getRepository() {
        return repository;
    }
}
