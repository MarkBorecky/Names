package com.example.application.services;

import com.example.application.data.entity.DataConverter;
import com.example.application.data.entity.DataDao;
import com.github.miachm.sods.SpreadSheet;

import java.io.IOException;
import java.util.List;

public class ODSReader implements FileReader {
    @Override
    public List<DataDao> read(String fileName) throws IOException {
        var spread = new SpreadSheet(readFile(fileName));
        var sheet = spread.getSheets().get(0);
        var range = sheet.getDataRange();
        var values = range.getValues();
        return DataConverter.getDataDaoList(values);
    }
}
