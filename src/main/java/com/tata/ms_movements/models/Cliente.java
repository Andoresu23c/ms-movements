package com.tata.ms_movements.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cliente extends Persona { //Hereda todos los atributos de Persona a cliente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    @Column(nullable = false)
    private String contrasenia;
    private boolean estado;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "fkCliente")
    @JsonManagedReference
    private List<Cuenta> cuentas = new ArrayList<>();
}
