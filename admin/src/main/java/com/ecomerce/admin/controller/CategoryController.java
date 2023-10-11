package com.ecomerce.admin.controller;

import com.ecomerce.library.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping("/categories")
    public String category(Model model) {
        model.addAttribute("title", "Category");
        return "categories";
    }

}
