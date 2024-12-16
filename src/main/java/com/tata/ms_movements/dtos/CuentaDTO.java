package com.tata.ms_movements.dtos;

import lombok.Data;

@Data
public class CuentaDTO {
	private Long idCuenta;
	private String numeroCuenta;
	private String tipoCuenta;
	private double saldoInicial;
	private boolean estado;
	private String fkCliente;
}
