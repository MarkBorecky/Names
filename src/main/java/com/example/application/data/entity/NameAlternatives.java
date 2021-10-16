package com.example.application.data.entity;

import java.util.List;

public enum NameAlternatives implements NameAbstraction{
    ALFRED(List.of("АДАЛЬБЕРТ", "АЛЬБЕРТ", "АДАЛЬБЕРТЪ", "АЛЬБЕРТЪ")),
    ALEKSEJ(List.of("АЛЕКСЕЙ", "АЛЕКСЕЙ", "АЛЕКСІЙ", "АЛЕКСѢЙ"));

    public final List<String> alternatives;

    NameAlternatives(List<String> alternatives) {
        this.alternatives = alternatives;
    }

    public List<String> getAlternatives() {
        return alternatives;
    }

    public String getMainName() {
        return this.alternatives.get(0);
    }
}
