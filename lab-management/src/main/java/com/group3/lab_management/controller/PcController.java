package com.group3.lab_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group3.lab_management.exception.BusinessException;
import com.group3.lab_management.model.Pc;
import com.group3.lab_management.model.PcStatus;
import com.group3.lab_management.repository.PcRepository;
import com.group3.lab_management.service.PcService;

@Controller
public class PcController {

    private final PcRepository pcRepository;
    private final PcService pcService;

    public PcController(PcRepository pcRepository, PcService pcService) {
        this.pcRepository = pcRepository;
        this.pcService = pcService;
    }

    @GetMapping("/pc")
    public String listPc(Model model) {
        model.addAttribute("pcs", pcRepository.findAll());
        model.addAttribute("statuses", PcStatus.values());
        return "pc-list";
    }

    @GetMapping("/computers")
    public String computers(Model model) {

        model.addAttribute("pcs", pcRepository.findAll());

        return "computers";
    }

    @GetMapping("/newcomputer")
    public String newComputer(Model model) {

        model.addAttribute("statuses", PcStatus.values());

        return "pc-form";
    }

    @PostMapping("/computers/delete")
    public String deletePc(@RequestParam Long id) {
        pcRepository.deleteById(id);
        return "redirect:/computers";
    }

    @PostMapping("/computers")
    public String createPcFromModal(
            @RequestParam String kodePc,
            @RequestParam(required = false) String status,
            Model model) {
        try {
            Pc pc = new Pc();
            pc.setKodePc(kodePc);
            if (status != null && !status.isEmpty()) {
                pc.setStatus(PcStatus.valueOf(status));
            }
            pcService.save(pc);
            return "redirect:/computers";
        } catch (BusinessException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("pcs", pcRepository.findAll());
            return "computers";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            model.addAttribute("pcs", pcRepository.findAll());
            return "computers";
        }
    }

    @PostMapping("/newcomputer")
    public String createPc(
            @RequestParam String kodePc,
            @RequestParam(required = false) String status,
            Model model) {
        try {
            Pc pc = new Pc();
            pc.setKodePc(kodePc);
            if (status != null && !status.isEmpty()) {
                pc.setStatus(PcStatus.valueOf(status));
            }
            pcService.save(pc);
            return "redirect:/computers";
        } catch (BusinessException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("pcs", pcRepository.findAll());
            model.addAttribute("statuses", PcStatus.values());
            return "pc-list";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            model.addAttribute("pcs", pcRepository.findAll());
            model.addAttribute("statuses", PcStatus.values());
            return "pc-list";
        }
    }
}
