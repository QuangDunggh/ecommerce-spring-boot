package com.ecomerce.admin.controller;

import com.ecomerce.library.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping("/categories")
    public String category(Model model) {
        model.addAttribute("title", "Category");
        return "categories";
    }

    @PostMapping("/save-category")
    public String saveCategory() {
        return null;
    }

}
