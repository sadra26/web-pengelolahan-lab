package com.group3.lab_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group3.lab_management.model.Borrower;

public interface BorrowerRepository
    extends JpaRepository<Borrower, Long> {
}
