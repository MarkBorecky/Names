package com.example.application.data.entity;

public record Name(String mainName, String originalName) {
    @Override
    public String toString() {
        return mainName.equals(originalName) ? mainName : String.format("%s (%s)",mainName, originalName);
    }
}
