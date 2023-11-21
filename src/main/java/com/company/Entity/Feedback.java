package com.company.Entity;

import java.io.Serializable;
import java.util.Calendar;

public class Feedback implements Serializable {
    private String name;
    private String car;
    private Calendar date;
    private String description;
    private String transmission;

    public Feedback(String name, String car, Calendar date, String description, String transmission) {
        this.name = name;
        this.car = car;
        this.date = date;
        this.description = description;
        this.transmission = transmission;
    }

    public Feedback() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    @Override
    public String toString() {
        return name + ", " + car + ", " + date.get(Calendar.DAY_OF_MONTH) + "-"
                + date.get(Calendar.MONTH) + "-" + date.get(Calendar.YEAR) + ", " + description + ", " + transmission;
    }
}
