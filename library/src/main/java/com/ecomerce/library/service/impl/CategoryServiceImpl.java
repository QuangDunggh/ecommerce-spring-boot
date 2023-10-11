package com.ecomerce.library.service.impl;

import com.ecomerce.library.model.Category;
import com.ecomerce.library.repository.CategoryRepository;
import com.ecomerce.library.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        try {
            Category categorySave = new Category(category.getName());
            return categoryRepository.save(categorySave);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(Category.builder()
                .name(category.getName())
                .is_activated(category.is_activated())
                .is_deleted(category.is_deleted())
                .build());
    }

    @Override
    public void deleteById(Long id) {
        Category category = getById(id);
        category.set_deleted(true);
        category.set_activated(false);
        categoryRepository.save(category);
    }

    @Override
    public void enableById(Long id) {
        Category category = getById(id);
        category.set_deleted(false);
        category.set_activated(true);
        categoryRepository.save(category);
    }
}
