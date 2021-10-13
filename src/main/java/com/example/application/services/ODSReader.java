package com.example.application.services;

import com.example.application.data.entity.DataConverter;
import com.example.application.data.entity.Person;
import com.github.miachm.sods.SpreadSheet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ODSReader implements FileReader {
    @Override
    public List<Person> read(InputStream is) throws IOException {
        var spread = new SpreadSheet(is);
        var sheet = spread.getSheets().get(0);
        var range = sheet.getDataRange();
        var values = range.getValues();
        return DataConverter.getDataDaoList(values);
    }
}
