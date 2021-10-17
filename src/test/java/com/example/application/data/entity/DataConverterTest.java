package com.example.application.data.entity;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

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

    @Test
    public void shouldBeEquals() {
        var flor = "Флоръ";
        var result = new Name(flor);
        assertEquals("ФЛОР [Флоръ]", result.toString());
    }

    @Test
    public void shouldBeDiff() {
        var flor = "Jan";
        var result = new Name(flor);
        assertEquals("Jan", result.toString());
    }

    @Test
    public void shouldBeDiff2() {
        var expected = "Jan";
        var key = "Jan";
        var result = NamesConverter.getByAlternative(key).getMainName();
        assertEquals(expected, result);
    }

    @Test
    public void mapShouldBeFulfilled() {
        Map<String, NameAbstraction> names = NamesConverter.names;
        assertNotEquals(null, names);
        assertNotEquals(0, names.size());
    }

    @Test
    public void keysShouldBeEqual() {
        var key1 = "ФЛОРЪ";
        var key2 = "Флоръ";
        assertEquals(key1, key2.toUpperCase());
    }

    @Test
    public void keysShouldBeEqual2() {
        var expected = "ФЛОР";
        var key = "Флоръ";
        var result = NamesConverter.names.get(key.toUpperCase()).getMainName();
        assertEquals(expected, result);
    }
}