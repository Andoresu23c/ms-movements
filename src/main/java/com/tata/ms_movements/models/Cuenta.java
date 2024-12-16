package com.tata.ms_movements.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cuenta implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idCuenta;
    //No permite que el número de cuenta se repita ni que este incompleto
    @Column(unique = true, nullable = false)
    private String numeroCuenta;

    private String tipoCuenta;
    private double saldoInicial;
    private boolean estado;

    //Define la relación de las entidades de N:1, varias cuentas pueden ser creadas por un solo cliente
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fkCliente", nullable = false)
    @JsonBackReference
    private Cliente fkCliente;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "fkCuenta")
    @JsonManagedReference
    private List<Movimiento> movimientos = new ArrayList<>();
}
