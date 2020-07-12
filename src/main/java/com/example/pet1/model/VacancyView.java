package com.example.pet1.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class VacancyView extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacancy_id")
    @Getter@Setter
    private Vacancy vacancy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_seaker_id")
    @Getter@Setter
    private JobSeaker jobSeaker;

    public VacancyView() {
    }

    public VacancyView(Vacancy vacancy, JobSeaker jobSeaker) {
        this.vacancy = vacancy;
        this.jobSeaker = jobSeaker;
    }
}
