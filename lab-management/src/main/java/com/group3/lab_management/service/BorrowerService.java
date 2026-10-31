package com.group3.lab_management.service;

import org.springframework.stereotype.Service;
import com.group3.lab_management.model.Borrower;
import com.group3.lab_management.repository.BorrowerRepository;
import com.group3.lab_management.exception.BusinessException;
import java.util.List;


@Service
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;

    public BorrowerService(
            BorrowerRepository borrowerRepository) {

        this.borrowerRepository = borrowerRepository;
    }

    public Borrower save(Borrower borrower) {

        if (borrower.getNim() == null ||
            borrower.getNim().trim().isEmpty()) {

            throw new BusinessException(
                    "NIM tidak boleh kosong");
        }

        if (borrower.getNama() == null ||
            borrower.getNama().trim().isEmpty()) {

            throw new BusinessException(
                    "Nama tidak boleh kosong");
        }

        return borrowerRepository.save(borrower);
    }

    public List<Borrower> getAllBorrower() {
        return borrowerRepository.findAll();
    }
}