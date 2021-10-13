package com.example.application.services;

import com.example.application.data.entity.Person;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ExcelFileWriter {
	InputStream write(List<Person> data, String fileName) throws IOException;
}
