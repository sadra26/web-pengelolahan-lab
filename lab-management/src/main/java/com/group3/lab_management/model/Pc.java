package com.group3.lab_management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pcs")
public class Pc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String kodePc;

    @Enumerated(EnumType.STRING)
    private PcStatus status;

    public Pc() {}

    public Pc(String kodePc, PcStatus status) {
        this.kodePc = kodePc;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getKodePc() {
        return kodePc;
    }

    public void setKodePc(String kodePc) {
        this.kodePc = kodePc;
    }

    public PcStatus getStatus() {
        return status;
    }

    public void setStatus(PcStatus status) {
        this.status = status;
    }
}