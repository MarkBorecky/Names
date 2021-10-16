package com.example.application.data.entity;

public record NameWithoutAlternative(String name) implements NameAbstraction {

    public String getMainName() {
        return name;
    }
}
