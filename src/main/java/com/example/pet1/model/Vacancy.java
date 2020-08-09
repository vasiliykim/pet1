package com.example.pet1.model;

import com.example.pet1.model.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Vacancy  extends BaseEntity {

    private String description;

    private Integer salary;

    @ManyToMany(mappedBy = "appliedVacancies")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "vacancy", fetch = FetchType.LAZY)
    private List<VacancyView> vacancyView = new ArrayList<>();

    public Vacancy() {
    }

    public Vacancy(String description, Integer salary) {
        this.description = description;
        this.salary = salary;
    }
}
