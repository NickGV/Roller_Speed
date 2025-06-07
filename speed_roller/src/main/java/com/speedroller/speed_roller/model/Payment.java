package com.speedroller.speed_roller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Schema(description = "Entidad que representa un pago realizado por un estudiante")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_pagos")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del pago", example = "1")
    private Long id;

    @Schema(description = "Estudiante que realizó el pago")
    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Student estudiante;

    @Schema(description = "Fecha y hora del pago", example = "2025-06-07T10:30:00")
    @Column(nullable = false)
    private LocalDateTime fecha;

    @Schema(description = "Concepto del pago", example = "Mensualidad Junio")
    @Column(nullable = false, length = 50)
    private String concepto;

    @Schema(description = "Método de pago", example = "Tarjeta")
    @Column(nullable = false, length = 20)
    private String metodoPago;

    @Schema(description = "Monto pagado", example = "100.00")
    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private Double monto;

    @Schema(description = "Estado del pago", example = "Completado")
    @Column(nullable = false, length = 20)
    private String estado;

    @Schema(description = "Comprobante del pago (URL o nombre de archivo)")
    @Column(length = 255)
    private String comprobante;

    @PrePersist
    protected void onCreate() {
        fecha = LocalDateTime.now();
    }
}