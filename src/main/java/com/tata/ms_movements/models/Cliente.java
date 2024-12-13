package com.tata.ms_movements.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cliente extends Persona {
    @Column(unique = true, nullable = false)
    private String idCliente;
    @Column(nullable = false)
    private String contrasenia;
    private String estado;
}
