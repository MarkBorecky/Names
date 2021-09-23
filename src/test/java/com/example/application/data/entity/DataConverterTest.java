package com.example.application.data.entity;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class DataConverterTest {

    @Test
    public void shouldReturn0() {
        var in = StringUtils.EMPTY;
        var result = DataConverter.intValue(in);
        assertEquals(0, result);
    }

    @Test
    public void shouldReturn7() {
        var in = "7.0";
        var result = DataConverter.intValue(in);
        assertEquals(7, result);
    }

    @Test
    public void shouldBeNullRow() {
        Object[][] tab = new Object[10][10];
        long count = Arrays.stream(tab)
                .filter(DataConverter.isNullRow())
                .count();
        assertEquals(0, count);
    }

    @Test
    public void shouldBeNullRow2() {
        Object[][] tab = {{"first"},{},{"Non empty"},{}};
        long count = Arrays.stream(tab)
                .filter(DataConverter.isNullRow())
                .count();
        assertEquals(2, count);
    }
}