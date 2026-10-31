package com.group3.lab_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group3.lab_management.exception.BusinessException;
import com.group3.lab_management.model.Borrower;
import com.group3.lab_management.repository.BorrowerRepository;
import com.group3.lab_management.service.BorrowerService;


@Controller
@RequestMapping({"/borrowers", "/borrower"})
public class BorrowerController {
    
    private final BorrowerRepository borrowerRepository;
    private final BorrowerService borrowerService;
    
    public BorrowerController(BorrowerRepository borrowerRepository, BorrowerService borrowerService) {
        this.borrowerRepository = borrowerRepository;
        this.borrowerService = borrowerService;
    }
    
    @GetMapping
    public String listBorrower(Model model) {
        model.addAttribute("borrowers", borrowerRepository.findAll());
        return "borrowers";
    }

    @PostMapping
    public String createBorrower(
            @RequestParam String nim,
            @RequestParam String nama,
            Model model) {
        try {
            Borrower borrower = new Borrower();
            borrower.setNim(nim);
            borrower.setNama(nama);
            borrowerService.save(borrower);
            return "redirect:/borrowers";
        } catch (BusinessException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("borrowers", borrowerRepository.findAll());
            return "borrowers";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            model.addAttribute("borrowers", borrowerRepository.findAll());
            return "borrowers";
        }
    }

    @PostMapping("/edit")
    public String editBorrower(
            @RequestParam Long id,
            @RequestParam String nim,
            @RequestParam String nama,
            Model model) {
        try {
            Borrower borrower = borrowerRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Peminjam tidak ditemukan"));
            borrower.setNim(nim);
            borrower.setNama(nama);
            borrowerService.save(borrower);
            return "redirect:/borrowers";
        } catch (BusinessException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("borrowers", borrowerRepository.findAll());
            return "borrowers";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            model.addAttribute("borrowers", borrowerRepository.findAll());
            return "borrowers";
        }
    }

    @PostMapping("/delete")
    public String deleteBorrower(@RequestParam Long id, Model model) {
        try {
            borrowerRepository.deleteById(id);
            return "redirect:/borrowers";
        } catch (Exception e) {
            model.addAttribute("error", "Gagal menghapus data: " + e.getMessage());
            model.addAttribute("borrowers", borrowerRepository.findAll());
            return "borrowers";
        }
    }
}
