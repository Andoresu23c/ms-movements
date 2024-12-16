package com.tata.ms_movements.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MovimientoDTO {
	
	private Long idMovimiento;
	private LocalDate fecha;
	private String tipoMovimiento;
	private double valor;
	private double saldo;
	private boolean estado;
	private Long fkCuenta;
}
