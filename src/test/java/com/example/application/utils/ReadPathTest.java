package com.example.application.utils;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReadPathTest {

    @Test
    public void shouldReturnTxt() {
        String fileExtension = ReadPath.getFileExtension("file.txt");
        assertEquals("txt", fileExtension);
    }

    @Test
    public void shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> ReadPath.getFileExtension("filetxt"));
    }

}