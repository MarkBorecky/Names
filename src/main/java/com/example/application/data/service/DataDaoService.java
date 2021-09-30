package com.example.application.data.service;

import com.example.application.data.entity.DataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.List;

@Service
public class DataDaoService extends CrudService<DataDao, Integer> {

    private DataDaoRepository repository;

    public DataDaoService(@Autowired DataDaoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected DataDaoRepository getRepository() {
        return repository;
    }

    public List<DataDao> getAll() {
        return repository.findAll();
    }
}
