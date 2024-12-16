package com.tata.ms_movements.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cliente extends Persona implements Serializable{ //Hereda todos los atributos de Persona a cliente
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(unique = true, nullable = false)
    private String idCliente;
    @Column(nullable = false)
    private String contrasenia;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "fkCliente")
    @JsonManagedReference
    private List<Cuenta> cuentas = new ArrayList<>();
}
