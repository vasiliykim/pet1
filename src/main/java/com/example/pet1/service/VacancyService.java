package com.example.pet1.service;

import com.example.pet1.model.Vacancy;
import com.example.pet1.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VacancyService implements BaseService<Vacancy> {
    @Autowired
    private VacancyRepository vacancyRepository;

    @Override
    @Transactional(readOnly = true)
    public Vacancy findById(Long id) {
        return vacancyRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vacancy> findAll() {
        return vacancyRepository.findAll();
    }

    @Override
    @Transactional
    public Vacancy saveOrUpdate(Vacancy entity) {
        return vacancyRepository.save(entity);
    }

    @Override
    @Transactional
    public List<Vacancy> saveAll(List<Vacancy> entities) {
        return vacancyRepository.saveAll(entities);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        vacancyRepository.deleteById(id);
    }
}
