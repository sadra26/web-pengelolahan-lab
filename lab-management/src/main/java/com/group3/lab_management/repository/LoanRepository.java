package com.group3.lab_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.group3.lab_management.model.Loan;
import com.group3.lab_management.model.Pc;
import java.util.List;

public interface LoanRepository
        extends JpaRepository<Loan, Long> {

    List<Loan> findBySelesaiFalse();
    
    List<Loan> findBySelesaiTrue();
    
    List<Loan> findByPcAndSelesaiFalse(Pc pc);
}
