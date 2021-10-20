package com.example.application.services;

import com.example.application.data.entity.DataConverter;
import com.example.application.data.entity.Person;
import com.example.application.utils.Headers;
import com.example.application.utils.PersonUtils;
import com.github.miachm.sods.Sheet;
import com.github.miachm.sods.SpreadSheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ODSWriter implements ExcelFileWriter {

    @Override
    public InputStream write(List<Person> people, String fileName) throws IOException {

        SpreadSheet spread = new SpreadSheet();
        spread.appendSheet(getMainSheet(people));
        spread.appendSheet(getNamesSheet(people));
        spread.appendSheet(getMainNamesSheet(people.stream().map(p->p.getMainName()).distinct().collect(Collectors.toList())));

        spread.save(new File(fileName));
        return new FileInputStream(fileName);
    }

    private Sheet getMainNamesSheet(List<String> names) {
        var columns = 1;
        var rows = names.size();
        var sheet = new Sheet("Główne imiona", rows, columns);
        sheet.getDataRange().setValues(names.toArray());
        return sheet;
    }

    private Sheet getNamesSheet(List<Person> people) {
        var names = PersonUtils.getDistinctOriginalNames(people);
        var columns = 2;
        var rows = names.size() + 1;
        var sheet = new Sheet("Imiona", rows, columns);
        var header = new ArrayList<String>();
        header.add(Headers.MAIN_NAME);
        header.add(Headers.ORIGINAL_NAME);
        header.addAll(DataConverter.transferNames(names));
        sheet.getDataRange().setValues(header.toArray());
        return sheet;
    }

    private Sheet getMainSheet(List<Person> people) {
        var header = DataConverter.getHeader();
        int columns = header.size();
        int rows = people.size() + 1;
        var sheet = new Sheet("Cała tabela", rows, columns);
        header.addAll(DataConverter.transferPeople(people));
        sheet.getDataRange().setValues(header.toArray());
        return sheet;
    }


}
