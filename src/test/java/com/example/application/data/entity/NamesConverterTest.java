package com.example.application.data.entity;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NamesConverterTest {

    @Test
    public void shouldBeAleks() {
        var aleks = "АЛЕКСІЙ";
        var result = NamesConverter.getByAlternative(aleks);
        assertEquals(NameAlternatives.АЛЕКСЕЙ, result);
    }

    @Test
    public void shouldBeAleks2() {
        var aleks = "АЛЕКСІЙ";
        var aleks2 = "АЛЕКСѢЙ";
        var result = NamesConverter.getByAlternative(aleks);
        var result2 = NamesConverter.getByAlternative(aleks2);
        assertEquals(result, result2);
    }

    @Test
    public void shouldBeEquals() {
        var name = "Dummy Name";
        var result = NamesConverter.getByAlternative(name).getMainName();
        assertEquals(name, result);
    }
}