package com.example.pet1.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Data
public class Vacancy  extends BaseEntity {

    private String description;

    private Integer salary;

    @ManyToMany(mappedBy = "appliedVacancies")
    private Set<JobSeaker> jobSeakers;

    public Vacancy() {
    }

    public Vacancy(String description, Integer salary) {
        this.description = description;
        this.salary = salary;
    }
}
