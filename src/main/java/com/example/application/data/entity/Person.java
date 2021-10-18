package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Person extends AbstractEntity {

    private int _id;
    @Embedded
    private Name name;
    private String surname;
    private String patronus;
    private String goverment;
    private String uyezd;
    private String selo;
    private String fatherOccupation;
    private int number;
    private String school;
    private int year;

    public Person() {}

    public static class Builder {

        private int _id;
        private Name name;
        private String surname;
        private String patronus;
        private String goverment;
        private String uyezd;
        private String selo;
        private String fatherOccupation;
        private int number;
        private String school;
        private int year;

        public Builder() {
        }

        Builder(int id, Name name, String surname, String patronus, String goverment, String uyezd, String selo, String fatherOccupation, int number, String school, int year) {
            this._id = id;
            this.name = name;
            this.surname = surname;
            this.patronus = patronus;
            this.goverment = goverment;
            this.uyezd = uyezd;
            this.selo = selo;
            this.fatherOccupation = fatherOccupation;
            this.number = number;
            this.school = school;
            this.year = year;
        }

        public Builder _id(int id){
            this._id = id;
            return Builder.this;
        }

        public Builder name(String name){
            this.name = new Name(name.trim());
            return Builder.this;
        }

        public Builder surname(String surname){
            this.surname = surname.trim();
            return Builder.this;
        }

        public Builder patronus(String patronus){
            this.patronus = patronus.trim();
            return Builder.this;
        }

        public Builder goverment(String goverment){
            this.goverment = goverment.trim();
            return Builder.this;
        }

        public Builder uyezd(String uyezd){
            this.uyezd = uyezd.trim();
            return Builder.this;
        }

        public Builder selo(String selo){
            this.selo = selo.trim();
            return Builder.this;
        }

        public Builder fatherOccupation(String fatherOccupation){
            this.fatherOccupation = fatherOccupation.trim();
            return Builder.this;
        }

        public Builder number(int number){
            this.number = number;
            return Builder.this;
        }

        public Builder school(String school){
            this.school = school.trim();
            return Builder.this;
        }

        public Builder year(int year){
            this.year = year;
            return Builder.this;
        }

        public Person build() {
            if(this.name == null){
                throw new NullPointerException("The property \"name\" is null. "
                        + "Please set the value by \"name()\". "
                        + "The properties \"name\", \"surname\", \"patronus\", \"goverment\", \"uyezd\", \"selo\", \"fatherOccupation\" and \"school\" are required.");
            }
            if(this.surname == null){
                throw new NullPointerException("The property \"surname\" is null. "
                        + "Please set the value by \"surname()\". "
                        + "The properties \"name\", \"surname\", \"patronus\", \"goverment\", \"uyezd\", \"selo\", \"fatherOccupation\" and \"school\" are required.");
            }
            if(this.patronus == null){
                throw new NullPointerException("The property \"patronus\" is null. "
                        + "Please set the value by \"patronus()\". "
                        + "The properties \"name\", \"surname\", \"patronus\", \"goverment\", \"uyezd\", \"selo\", \"fatherOccupation\" and \"school\" are required.");
            }
            if(this.goverment == null){
                throw new NullPointerException("The property \"goverment\" is null. "
                        + "Please set the value by \"goverment()\". "
                        + "The properties \"name\", \"surname\", \"patronus\", \"goverment\", \"uyezd\", \"selo\", \"fatherOccupation\" and \"school\" are required.");
            }
            if(this.uyezd == null){
                throw new NullPointerException("The property \"uyezd\" is null. "
                        + "Please set the value by \"uyezd()\". "
                        + "The properties \"name\", \"surname\", \"patronus\", \"goverment\", \"uyezd\", \"selo\", \"fatherOccupation\" and \"school\" are required.");
            }
            if(this.selo == null){
                throw new NullPointerException("The property \"selo\" is null. "
                        + "Please set the value by \"selo()\". "
                        + "The properties \"name\", \"surname\", \"patronus\", \"goverment\", \"uyezd\", \"selo\", \"fatherOccupation\" and \"school\" are required.");
            }
            if(this.fatherOccupation == null){
                throw new NullPointerException("The property \"fatherOccupation\" is null. "
                        + "Please set the value by \"fatherOccupation()\". "
                        + "The properties \"name\", \"surname\", \"patronus\", \"goverment\", \"uyezd\", \"selo\", \"fatherOccupation\" and \"school\" are required.");
            }
            if(this.school == null){
                throw new NullPointerException("The property \"school\" is null. "
                        + "Please set the value by \"school()\". "
                        + "The properties \"name\", \"surname\", \"patronus\", \"goverment\", \"uyezd\", \"selo\", \"fatherOccupation\" and \"school\" are required.");
            }

            return new Person(this);
        }
    }

    private Person(Builder builder) {
        this._id = builder._id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.patronus = builder.patronus;
        this.goverment = builder.goverment;
        this.uyezd = builder.uyezd;
        this.selo = builder.selo;
        this.fatherOccupation = builder.fatherOccupation;
        this.number = builder.number;
        this.school = builder.school;
        this.year = builder.year;
    }

    @Override
    public String toString() {
        return "DataDao{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronus='" + patronus + '\'' +
                ", goverment='" + goverment + '\'' +
                ", uyezd='" + uyezd + '\'' +
                ", selo='" + selo + '\'' +
                ", fatherOccupation='" + fatherOccupation + '\'' +
                ", number=" + number +
                ", school='" + school + '\'' +
                ", year=" + year +
                '}';
    }

    public int get_id() {
        return _id;
    }

    public Name getName() {
        return name;
    }

    public String getMainName() {
        return name.getMainName();
    }

    public String getOriginalName() {
        return name.getOriginalName();
    }

    public String getNameToString() {
        return name.toString();
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronus() {
        return patronus;
    }

    public String getGoverment() {
        return goverment;
    }

    public String getUyezd() {
        return uyezd;
    }

    public String getSelo() {
        return selo;
    }

    public String getFatherOccupation() {
        return fatherOccupation;
    }

    public int getNumber() {
        return number;
    }

    public String getSchool() {
        return school;
    }

    public int getYear() {
        return year;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    public void setGoverment(String goverment) {
        this.goverment = goverment;
    }

    public void setUyezd(String uyezd) {
        this.uyezd = uyezd;
    }

    public void setSelo(String selo) {
        this.selo = selo;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
