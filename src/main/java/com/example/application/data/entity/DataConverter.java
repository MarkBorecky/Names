package com.example.application.data.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class DataConverter {

    enum DataField {
        ID("_id", "id"),
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
        return StringUtils.EMPTY;
    }

    public static DataDao readDataDao(Object[] header, Object[] row) {
        var map = createMap(header, row);
        return new DataDao.Builder()
                ._id(intValue(map.get(DataField.ID.objectField)))
                .name(map.get(DataField.NAME.objectField))
                .surname(map.get(DataField.SURNAME.objectField))
                .patronus(map.get(DataField.PATRONUS.objectField))
                .goverment(map.get(DataField.GOVERMENT.objectField))
                .uyezd(map.get(DataField.UYEZD.objectField))
                .selo(map.get(DataField.SELO.objectField))
                .fatherOccupation(map.get(DataField.FATHER_OCCUPATION.objectField))
                .number(intValue(map.get(DataField.NUMBER.objectField)))
                .school(map.get(DataField.SCHOOL.objectField))
                .year(intValue(map.get(DataField.YEAR.objectField)))
                .build();
    }

    public static int intValue(String s) {
        return s.equals(StringUtils.EMPTY) ? 0 : Double.valueOf(s).intValue();
    }

    public static List<DataDao> getDataDaoList(Object[][] values) {
        Object[] header = values[0];
        return Arrays.stream(values)
                .skip(1)
                .filter(isNullRow())
                .map(row -> DataConverter.readDataDao(header, row))
                .collect(Collectors.toList());
    }

    public static Predicate<Object[]> isNullRow() {
        return row -> Arrays.stream(row)
                .map(cell -> cell != null)
                .reduce(false, (a,b) -> a || b );
    }

    private static Map<String, String> createMap(Object[] header, Object[] row) {
        var map = new HashMap<String, String>();
        for (int i=0; i<header.length; i++) {
            var key = getFieldName(getString(header[i]));
            if (key.equals(StringUtils.EMPTY))
                break;
            map.put(key, getString(row[i]));
        }
        return map;
    }

    private static String getString(Object o) {
        if (isNull(o))
            return StringUtils.EMPTY;
        else
            return o.toString();
    }
}
