package com.example.pet1.service;

import java.util.List;

public interface BaseService<T> {
    T findById(Long id);
    List<T> findAll();

    T saveOrUpdate(T entity);
    List<T> saveAll(List<T> entities);

    void deleteById(Long id);

}
