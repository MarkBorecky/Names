package com.example.application.services;

import com.example.application.data.entity.Person;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class XLSXReader implements FileReader {
	@Override
	public List<Person> read(InputStream inputStream) throws IOException, InvalidFormatException {
		throw new NotImplementedException();
	}
}
