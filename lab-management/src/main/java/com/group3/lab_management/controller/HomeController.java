package com.group3.lab_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.group3.lab_management.service.LoanService;

@Controller
public class HomeController {

    private final LoanService loanService;

    public HomeController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/home")
    public String home() {
        return "dashboard";
    }

    @GetMapping("/loanhistory")
    public String loanHistory(Model model) {
        model.addAttribute("loans", loanService.getAllLoan());
        model.addAttribute("activeLoan", loanService.getActiveLoan());
        model.addAttribute("completedLoan", loanService.getCompletedLoan());
        return "loanhistory";
    }
}