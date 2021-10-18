package com.example.application.data.service;

import com.example.application.data.entity.Person;
import com.example.application.data.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.List;

@Service
public class PersonService extends CrudService<Person, Integer> {

    private PersonRepository repository;

    public PersonService(@Autowired PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    protected PersonRepository getRepository() {
        return repository;
    }

    public List<Person> getAll() {
        return repository.findAll();
    }

	public List<Person> getAll(
	        String mainName,
	        String originalName,
            String surname,
            String patronus,
            String goverment,
            String uyezd,
            String selo,
            String fatherOccupation,
            String number,
            String school,
            String year) {
        return repository.findAll(
                mainName, originalName, surname, patronus, goverment, uyezd, selo, fatherOccupation, number, school, year);
	}

	public void saveAll(List<Person> data) {
		repository.saveAll(data);
	}

	public void deleteAll() {
		repository.deleteAll();
	}
}
