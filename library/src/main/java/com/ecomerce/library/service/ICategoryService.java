package com.ecomerce.library.service;

import com.ecomerce.library.model.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> findAll();

    Category save(Category category);

    Category getById(Long id);

    Category update(Category category);

    void deleteById(Long id);

    void enableById(Long id);
}
