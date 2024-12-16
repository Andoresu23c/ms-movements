package com.tata.ms_movements.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable {
    //Permite serializar la persistencia de datos de los objetos
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idPersona;
    private String nombre;
    private String genero;
    //No permite ingresar la misma identificación
    @Column(unique = true, nullable = false)
    private String identificacion;
    @Column(nullable = false)
    private int edad;
    private String direccion;
    @Column(unique = true)
    private String telefono;
    @Column(nullable = false)
    private boolean estado;
    
}
