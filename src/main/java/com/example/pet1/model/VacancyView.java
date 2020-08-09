package com.example.pet1.model;

import com.example.pet1.model.base.BaseEntity;
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
    @JoinColumn(name = "user_id")
    @Getter@Setter
    private User user;

    public VacancyView() {
    }

    public VacancyView(Vacancy vacancy, User user) {
        this.vacancy = vacancy;
        this.user = user;
    }
}
