package com.group3.lab_management.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.group3.lab_management.model.Pc;
import com.group3.lab_management.model.PcStatus;

public interface PcRepository extends JpaRepository<Pc, Long> {

    Optional<Pc> findByKodePc(String kodePc);

    boolean existsByKodePc(String kodePc);

    long countByStatus(PcStatus status);
}
