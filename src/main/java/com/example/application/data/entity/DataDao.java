package com.example.application.data.entity;

public class DataDao {
    // id	imię	nazwisko	patronimikum	gubernia	ujezd	sioło	zawód ojca	NUMER	Szkoła	Rok

    private int id;
    private String name;
    private String surname;
    private String patronus;
    private String goverment;
    private String uyezd;
    private String selo;
    private String fatherOccupation;
    private int number;
    private String school;
    private int year;

    public static class Builder {

        private int id;
        private String name;
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

        Builder(int id, String name, String surname, String patronus, String goverment, String uyezd, String selo, String fatherOccupation, int number, String school, int year) {
            this.id = id;
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

        public Builder id(int id){
            this.id = id;
            return Builder.this;
        }

        public Builder name(String name){
            this.name = name;
            return Builder.this;
        }

        public Builder surname(String surname){
            this.surname = surname;
            return Builder.this;
        }

        public Builder patronus(String patronus){
            this.patronus = patronus;
            return Builder.this;
        }

        public Builder goverment(String goverment){
            this.goverment = goverment;
            return Builder.this;
        }

        public Builder uyezd(String uyezd){
            this.uyezd = uyezd;
            return Builder.this;
        }

        public Builder selo(String selo){
            this.selo = selo;
            return Builder.this;
        }

        public Builder fatherOccupation(String fatherOccupation){
            this.fatherOccupation = fatherOccupation;
            return Builder.this;
        }

        public Builder number(int number){
            this.number = number;
            return Builder.this;
        }

        public Builder school(String school){
            this.school = school;
            return Builder.this;
        }

        public Builder year(int year){
            this.year = year;
            return Builder.this;
        }

        public DataDao build() {
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

            return new DataDao(this);
        }
    }

    private DataDao(Builder builder) {
        this.id = builder.id;
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
                "id=" + id +
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
}
