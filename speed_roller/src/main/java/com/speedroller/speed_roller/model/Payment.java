package com.speedroller.speed_roller.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_pagos")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Student estudiante;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false, length = 50)
    private String concepto;

    @Column(nullable = false, length = 20)
    private String metodoPago;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private Double monto;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(length = 255)
    private String comprobante;

    @PrePersist
    protected void onCreate() {
        fecha = LocalDateTime.now();
    }
}