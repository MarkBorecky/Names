package com.example.application.utils;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    public void shouldReturnFalse1() {
        String text = null;
        boolean result = StringUtils.isNotEmpty(text);
        assertEquals(false, result);
    }

    @Test
    public void shouldReturnFalse2() {
        String text = "";
        boolean result = StringUtils.isNotEmpty(text);
        assertEquals(false, result);
    }

    @Test
    public void shouldReturnFalse3() {
        String text = "  ";
        boolean result = StringUtils.isNotEmpty(text);
        assertEquals(false, result);
    }

    @Test
    public void shouldReturnFalse4() {
        String text = "-";
        boolean result = StringUtils.isNotEmpty(text);
        assertEquals(false, result);
    }

    @Test
    public void shouldReturnFalse5() {
        String text = "- ";
        boolean result = StringUtils.isNotEmpty(text);
        assertEquals(false, result);
    }

}