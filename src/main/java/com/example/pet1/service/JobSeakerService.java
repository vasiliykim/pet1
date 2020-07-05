package com.example.pet1.service;

import com.example.pet1.model.JobSeaker;
import com.example.pet1.repository.JobSeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeakerService implements BaseService<JobSeaker>{

    @Autowired
    private JobSeakerRepository jobSeakerRepository;

    @Override
    public JobSeaker findById(Long id) {
        return jobSeakerRepository.findById(id).orElse(null);
    }

    @Override
    public List<JobSeaker> findAll() {
        return jobSeakerRepository.findAll();
    }

    @Override
    public JobSeaker saveOrUpdate(JobSeaker entity) {
        return jobSeakerRepository.save(entity);
    }

    @Override
    public List<JobSeaker> saveAll(List<JobSeaker> entities) {
        return jobSeakerRepository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        jobSeakerRepository.deleteById(id);
    }
}
