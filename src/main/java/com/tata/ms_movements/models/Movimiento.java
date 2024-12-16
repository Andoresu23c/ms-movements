package com.tata.ms_movements.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
public class Movimiento implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idMovimiento;
    private LocalDate fecha;
    private String tipoMovimiento;
    private double valor;
    private double saldo;
    private boolean estado;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fkCuenta", nullable = false)
    @JsonBackReference
    private Cuenta fkCuenta;
}
