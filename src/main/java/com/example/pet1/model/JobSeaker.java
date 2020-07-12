package com.example.pet1.model;

import com.example.pet1.model.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class JobSeaker extends BaseEntity {

    @Getter@Setter
    @Column(length = 20, nullable = false)
    @Pattern(regexp = Const.Regex.name)
    private String lastname;

    @Getter@Setter
    @Column(length = 20, nullable = false)
    @Pattern(regexp = Const.Regex.name)
    private String firstname;

    @Getter@Setter
    private LocalDate birthday;

    @Column(unique = true)
    @Pattern(regexp = Const.Regex.email)
    @Getter@Setter
    private String email;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "job_seaker_vacancy",
            joinColumns = @JoinColumn(name = "job_seaker_id"),
            inverseJoinColumns = @JoinColumn(name = "vacacny_id"))
    @Getter@Setter
    private Set<Vacancy> appliedVacancies = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jobSeaker")
    @Getter@Setter
    private List<VacancyView> vacancyViews = new ArrayList<>();

    public JobSeaker() {
    }

    public JobSeaker(@Pattern(regexp = Const.Regex.name) String lastname, @Pattern(regexp = Const.Regex.name) String firstname, LocalDate birthday, @Pattern(regexp = Const.Regex.email) String email) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthday = birthday;
        this.email = email;
    }

    public void addVacancy(Vacancy vacancy) {
        appliedVacancies.add(vacancy);
        vacancy.getJobSeakers().add(this);
    }

    public void removeVacancy(Vacancy vacancy) {
        appliedVacancies.remove(vacancy);
        vacancy.getJobSeakers().remove(this);
    }

}
