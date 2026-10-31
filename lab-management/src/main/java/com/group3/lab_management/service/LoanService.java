package com.group3.lab_management.service;

import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull;
import com.group3.lab_management.model.Loan;
import com.group3.lab_management.model.Pc;
import com.group3.lab_management.model.PcStatus;
import com.group3.lab_management.repository.LoanRepository;
import com.group3.lab_management.repository.PcRepository;
import com.group3.lab_management.exception.BusinessException;
import java.util.List;
import java.util.Objects;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final PcRepository pcRepository;

    public LoanService(LoanRepository loanRepository, PcRepository pcRepository) {
        this.loanRepository = loanRepository;
        this.pcRepository = pcRepository;
    }

    public Loan createLoan(Loan loan) {
        // Validasi 1: Durasi jam harus > 0
        if (loan.getDurasiJam() == null || loan.getDurasiJam() <= 0) {
            throw new BusinessException(
                    "Durasi harus lebih dari 0");
        }

        // Validasi 2: PC tidak boleh null
        if (loan.getPc() == null) {
            throw new BusinessException(
                    "PC harus dipilih");
        }

        // Validasi 3 & 4: Cek apakah PC punya transaksi aktif
        Long pcId = Objects.requireNonNull(loan.getPc().getId());
        Pc pc = pcRepository.findById(pcId)
                .orElseThrow(() -> new BusinessException("PC tidak ditemukan"));

        List<Loan> activeLoanForPc = loanRepository.findByPcAndSelesaiFalse(pc);
        if (!activeLoanForPc.isEmpty()) {
            throw new BusinessException(
                    "PC sudah dipinjam, tidak boleh double booking");
        }

        // Auto-fix: jika tidak ada transaksi aktif tapi status masih DIPINJAM, reset ke TERSEDIA
        if (pc.getStatus() != PcStatus.TERSEDIA) {
            pc.setStatus(PcStatus.TERSEDIA);
            pcRepository.save(pc);
        }


        // Validasi 5: Borrower harus ada dan valid
        if (loan.getBorrower() == null) {
            throw new BusinessException(
                    "Peminjam harus dipilih");
        }

        // Validasi 6: Keperluan tidak boleh kosong
        if (loan.getKeperluan() == null || loan.getKeperluan().trim().isEmpty()) {
            throw new BusinessException(
                    "Keperluan tidak boleh kosong");
        }

        // Set status PC ke DIPINJAM
        pc.setStatus(PcStatus.DIPINJAM);
        pcRepository.save(pc);
        
        loan.setPc(pc);
        loan.setSelesai(false);

        return loanRepository.save(loan);
    }

    public Loan returnLoan(@NonNull Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new BusinessException("Peminjaman tidak ditemukan"));

        // Validasi: Pengembalian hanya untuk transaksi aktif
        if (loan.isSelesai()) {
            throw new BusinessException(
                    "Peminjaman sudah selesai, tidak bisa mengembalikan lagi");
        }

        // Tandai transaksi sebagai selesai
        loan.setSelesai(true);

        // Kembalikan status PC ke TERSEDIA
        Pc pc = loan.getPc();
        pc.setStatus(PcStatus.TERSEDIA);
        pcRepository.save(pc);

        return loanRepository.save(loan);
    }

    public List<Loan> getActiveLoan() {
        return loanRepository.findBySelesaiFalse();
    }

    public List<Loan> getCompletedLoan() {
        return loanRepository.findBySelesaiTrue();
    }

    public List<Loan> getAllLoan() {
        return loanRepository.findAll();
    }
}
