package com.group3.lab_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group3.lab_management.model.User;
import com.group3.lab_management.repository.UserRepository;
import com.group3.lab_management.repository.PcRepository;
import com.group3.lab_management.repository.BorrowerRepository;
@Controller
public class LoginController {

    private final UserRepository userRepository;
    private final PcRepository pcRepository;
    private final BorrowerRepository borrowerRepository;

    public LoginController(UserRepository userRepository, PcRepository pcRepository, BorrowerRepository borrowerRepository) {
        this.userRepository = userRepository;
        this.pcRepository = pcRepository;
        this.borrowerRepository = borrowerRepository;
    }

    // ================= LOGIN =================

    @GetMapping("/")
    public String home() {
        return "login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(
            @RequestParam String username,
            @RequestParam String password) {
        System.out.println("Username : " + username);
        System.out.println("Password : " + password);
        User user = userRepository.findByUsername(username);
        System.out.println("Data user : " + user);
        if (user == null) {
            System.out.println("USER TIDAK DITEMUKAN");
            return "redirect:/login";
        }
        System.out.println("Password DB : " + user.getPassword());
        if (!user.getPassword().equals(password)) {
            System.out.println("PASSWORD SALAH");
            return "redirect:/login";
        }
        System.out.println("LOGIN BERHASIL");
        return "redirect:/dashboard";
    }

    // ================= REGISTER =================

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(
            @RequestParam String name,
            @RequestParam String username,
            @RequestParam String password) {

        if (userRepository.findByUsername(username) != null) {
            return "redirect:/register";
        }

        User user = new User();

        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("USER");

        userRepository.save(user);

        return "redirect:/login";
    }

   @PostMapping("/settings")
    public String updateSettings(
            @RequestParam String name,
            @RequestParam String email) {
        User user = userRepository.findByUsername("admin");
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            userRepository.save(user);
        }
        return "redirect:/settings";
    }
    // ================= DASHBOARD =================

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    // ================= FORGOT PASSWORD =================

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }
    // =========================
    // HALAMAN NEW LOAN
    // =========================
    @GetMapping("/loan")
    public String newLoan(Model model) {
        model.addAttribute("borrowers", borrowerRepository.findAll());
        model.addAttribute("pcs", pcRepository.findAll());
        return "loan";
    }

    // =========================
    // HALAMAN BORROWERS
    // =========================
    

    // (HALAMAN COMPUTERS - Dihandle di PcController)


    // =========================
    // HALAMAN SETTINGS
    // =========================
    @GetMapping("/settings")
    public String settings(Model model) {
        User user = userRepository.findByUsername("admin");
        model.addAttribute("user", user);
        return "settings";
    }


}
