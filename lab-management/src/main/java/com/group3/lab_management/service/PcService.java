package com.group3.lab_management.service;

import org.springframework.stereotype.Service;
import com.group3.lab_management.model.Pc;
import com.group3.lab_management.model.PcStatus;
import com.group3.lab_management.repository.PcRepository;
import com.group3.lab_management.exception.BusinessException;
import java.util.List;


@Service
public class PcService {

    private final PcRepository pcRepository;

    public PcService(PcRepository pcRepository) {
        this.pcRepository = pcRepository;
    }

    public Pc save(Pc pc) {

        // Validasi 1
        if (pc.getKodePc() == null ||
            pc.getKodePc().trim().isEmpty()) {

            throw new BusinessException(
                    "Kode PC tidak boleh kosong");
        }

        // Validasi 2
        if (pcRepository.existsByKodePc(
                pc.getKodePc())) {

            throw new BusinessException(
                    "Kode PC sudah digunakan");
        }

        // Set default status to TERSEDIA
        if (pc.getStatus() == null) {
            pc.setStatus(PcStatus.TERSEDIA);
        }

        return pcRepository.save(pc);
    }

    public List<Pc> getAllPc() {
        return pcRepository.findAll();
    }
}