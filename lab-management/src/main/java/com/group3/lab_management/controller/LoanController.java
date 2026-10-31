package com.group3.lab_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.group3.lab_management.model.Loan;
import com.group3.lab_management.model.Pc;
import com.group3.lab_management.model.Borrower;
import com.group3.lab_management.repository.PcRepository;
import com.group3.lab_management.repository.BorrowerRepository;
import com.group3.lab_management.service.LoanService;
import com.group3.lab_management.exception.BusinessException;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/newloan")
public class LoanController {
    
    private final PcRepository pcRepository;
    private final BorrowerRepository borrowerRepository;
    private final LoanService loanService;
    
    public LoanController(PcRepository pcRepository,
                         BorrowerRepository borrowerRepository,
                         LoanService loanService) {
        this.pcRepository = pcRepository;
        this.borrowerRepository = borrowerRepository;
        this.loanService = loanService;
    }
    
    @GetMapping
    public String listLoan(Model model) {
        model.addAttribute("activeLoan", loanService.getActiveLoan());
        model.addAttribute("loans", loanService.getAllLoan());
        model.addAttribute("pcs", pcRepository.findAll());
        model.addAttribute("borrowers", borrowerRepository.findAll());
        return "loan-list";
    }

    @PostMapping
    public String createLoan(
            @RequestParam @NonNull Long pcId,
            @RequestParam @NonNull Long borrowerId,
            @RequestParam Integer durasiJam,
            @RequestParam String keperluan,
            Model model) {
        try {
            Pc pc = pcRepository.findById(pcId)
                    .orElseThrow(() -> new BusinessException("PC tidak ditemukan"));
            Borrower borrower = borrowerRepository.findById(borrowerId)
                    .orElseThrow(() -> new BusinessException("Peminjam tidak ditemukan"));

            Loan loan = new Loan();
            loan.setPc(pc);
            loan.setBorrower(borrower);
            loan.setWaktuMulai(LocalDateTime.now());
            loan.setDurasiJam(durasiJam);
            loan.setKeperluan(keperluan);
            
            loanService.createLoan(loan);
            return "redirect:/loanhistory";
        } catch (BusinessException e) {
            System.err.println("[LoanController] BusinessException: " + e.getMessage());
            model.addAttribute("error", e.getMessage());
            model.addAttribute("pcs", pcRepository.findAll());
            model.addAttribute("borrowers", borrowerRepository.findAll());
            return "loan";
        } catch (Exception e) {
            System.err.println("[LoanController] Exception: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Terjadi kesalahan: " + e.getMessage());
            model.addAttribute("pcs", pcRepository.findAll());
            model.addAttribute("borrowers", borrowerRepository.findAll());
            return "loan";
        }

    }

    @PostMapping("/return")
    public String returnLoan(
            @RequestParam @NonNull Long loanId,
            Model model) {
        try {
            loanService.returnLoan(loanId);
            return "redirect:/loan";
        } catch (BusinessException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("activeLoan", loanService.getActiveLoan());
            model.addAttribute("completedLoan", loanService.getCompletedLoan());
            model.addAttribute("pcs", pcRepository.findAll());
            model.addAttribute("borrowers", borrowerRepository.findAll());
            return "loan-list";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            model.addAttribute("activeLoan", loanService.getActiveLoan());
            model.addAttribute("completedLoan", loanService.getCompletedLoan());
            model.addAttribute("pcs", pcRepository.findAll());
            model.addAttribute("borrowers", borrowerRepository.findAll());
            return "loan-list";
        }
    }
}
