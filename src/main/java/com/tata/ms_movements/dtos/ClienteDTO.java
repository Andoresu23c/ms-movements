package com.tata.ms_movements.dtos;

import lombok.Data;

@Data
public class ClienteDTO {
	private String idCliente;
	private String nombres;
	private String direccion;
	private String telefono;
    private String contrasenia;
    private boolean estado;
}
