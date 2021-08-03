package com.example.android_project_8_sqlite;

public class Person {
    private String first_name;
    private String last_name;
    private String mobile;

    public Person() {
    }

    public Person(String first_name, String last_name, String mobile) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.mobile = mobile;
    }

    public String getFirstname() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLastname() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
