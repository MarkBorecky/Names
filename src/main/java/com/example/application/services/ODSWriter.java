package com.example.application.services;

import com.example.application.data.entity.DataConverter;
import com.example.application.data.entity.Person;
import com.github.miachm.sods.Sheet;
import com.github.miachm.sods.SpreadSheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class ODSWriter implements ExcelFileWriter {

	@Override
	public InputStream write(List<Person> people, String fileName) throws IOException {

		List<String> header = DataConverter.getHeader();
		int columns = header.size();
		int rows = people.size() + 1;
		Sheet sheet = new Sheet("A", rows, columns);


		header.addAll(DataConverter.transferPeople(people));
		sheet.getDataRange().setValues(header.toArray());

		// Set the underline style in the (3,3) cell
		//sheet.getRange(2, 2).setFontUnderline(true);

		// Set a bold font to the first 2x2 grid
		//sheet.getRange(0, 0, 2, 2).setFontBold(true);

		SpreadSheet spread = new SpreadSheet();
		spread.appendSheet(sheet);
		spread.save(new File(fileName));
		return new FileInputStream(fileName);
	}


}
