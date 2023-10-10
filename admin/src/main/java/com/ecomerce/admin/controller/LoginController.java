package com.ecomerce.admin.controller;

import com.ecomerce.library.dto.AdminDto;
import com.ecomerce.library.model.Admin;
import com.ecomerce.library.repository.AdminRepository;
import com.ecomerce.library.service.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final IAdminService adminService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("adminDto", new AdminDto());
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model) {
        return "forgot-password";
    }

    @PostMapping("/register-new")
    public String addNewAdmin(@Valid @ModelAttribute("adminDto") AdminDto adminDto,
                              BindingResult bindingResult,
                              Model model
    ) {

        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("adminDto", adminDto);
                return "register";
            }
            String username = adminDto.getUsername();
            Admin admin = adminService.findByUsername(username);
            if (admin != null) {
                model.addAttribute("errorEmail", "Your email has been register!!!");
                return "register";
            }
            if (adminDto.getPassword().equals(adminDto.getRepeatPassword())) {
                adminService.save(adminDto);
                model.addAttribute("adminDto", adminDto);
                model.addAttribute("messageSuccess", "Register successfully");
            } else {
                model.addAttribute("adminDto", adminDto);
                model.addAttribute("errorRepeatPassword", "Repeat password not same");
                return "register";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorServer", "Server is error, please try again later!!!");
        }
        return "register";
    }
}
