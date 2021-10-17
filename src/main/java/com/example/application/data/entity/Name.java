package com.example.application.data.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Name {

    private String mainName;
    private String originalName;

    public Name() {
    }

    public Name(String originalName) {
        this.mainName = NamesConverter.getByAlternative(originalName).getMainName();
        this.originalName = originalName;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    @Override
    public String toString() {
        return mainName.equals(originalName) ? mainName : String.format("%s [%s]",mainName, originalName);
    }
}
