package com.example.application.data.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataConverter {

    enum DataField {
        ID("id", "id"),
        NAME("name","imię"),
        SURNAME("surname","nazwisko"),
        PATRONUS("patronus","patronimikum"),
        GOVERMENT("goverment","gubernia"),
        UYEZD("uyezd","ujezd"),
        SELO("selo","sioło"),
        FATHER_OCCUPATION("fatherOccupation","zawód ojca"),
        NUMBER("number","NUMER"),
        SCHOOL("school","Szkoła"),
        YEAR("year","Rok");

        String objectField;
        String columnName;

        DataField(String objectField, String columnName) {
            this.objectField = objectField;
            this.columnName = columnName;
        }
    }

    private static String getFieldName(String name) {
        for (DataField df : DataField.values())
            if (name.equals(df.columnName))
                return df.objectField;
        throw new IllegalArgumentException(name);
    }

    public static DataDao someMethod(Object[] header, Object[] row) {
        var map = createMap(header, row);
        return new DataDao.Builder()
                .id(Double.valueOf(map.get(DataField.ID.objectField)).intValue())
                .name(map.get(DataField.NAME.objectField))
                .surname(map.get(DataField.SURNAME.objectField))
                .patronus(map.get(DataField.PATRONUS.objectField))
                .goverment(map.get(DataField.GOVERMENT.objectField))
                .uyezd(map.get(DataField.UYEZD.objectField))
                .selo(map.get(DataField.SELO.objectField))
                .fatherOccupation(map.get(DataField.FATHER_OCCUPATION.objectField))
                .number(Double.valueOf(map.get(DataField.NUMBER.objectField)).intValue())
                .school(map.get(DataField.SCHOOL.objectField))
                .year(Double.valueOf(map.get(DataField.YEAR.objectField)).intValue())
                .build();
    }

    public static List<DataDao> getDataDaoList(Object[][] values) {
        Object[] header = values[0];
        return Arrays.stream(values)
                .skip(1)
                .map(row -> DataConverter.someMethod(header, row))
                .collect(Collectors.toList());
    }

    private static Map<String, String> createMap(Object[] header, Object[] row) {
        var map = new HashMap<String, String>();
        for (int i=0; i<header.length; i++) {
            var key = getFieldName(header[i].toString());
            map.put(key, row[i].toString());
        }
        return map;
    }
}
