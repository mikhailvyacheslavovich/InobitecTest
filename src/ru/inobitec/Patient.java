package ru.inobitec;

import java.util.Date;

public class Patient {
    private String first_name;
    private String middle_name;
    private String last_name;
    private Date birthdate;
    private Integer age;
    private String gender;
    private String phone;

    public Patient() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        final String delimiter = " ";
        final String FIO = "1. \"ФИО\" - ";
        final String AGE = "2. \"Возраст\" - ";
        final String GENDER = "3. \"Пол\" - ";
        final String PHONE = "4. \"Телефон\" - ";

        sb.append(String.join(delimiter, FIO, getLast_name(),
                getFirst_name(), getMiddle_name(), "\n"));
        sb.append(String.join(delimiter, AGE, getAge().toString(), "\n"));
        sb.append(String.join(delimiter, GENDER, getGender(), "\n"));
        sb.append(String.join(delimiter, PHONE, getPhone(), "\n"));
        sb.append("-------------------------------------------\n");

        return sb.toString();
    }
}
