package com.example.pet1.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Vacancy  extends BaseEntity {

    private String description;

    private Integer salary;

    public Vacancy() {
    }

    public Vacancy(String description, Integer salary) {
        this.description = description;
        this.salary = salary;
    }
}
