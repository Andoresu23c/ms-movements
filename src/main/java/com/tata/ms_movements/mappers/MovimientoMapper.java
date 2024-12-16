package com.tata.ms_movements.mappers;

import com.tata.ms_movements.dtos.MovimientoDTO;
import com.tata.ms_movements.models.Cuenta;
import com.tata.ms_movements.models.Movimiento;

public class MovimientoMapper {
	
	public static MovimientoDTO toDto(Movimiento movimiento) {
        MovimientoDTO dto = new MovimientoDTO();
        dto.setIdMovimiento(movimiento.getIdMovimiento());
        dto.setFecha(movimiento.getFecha());
        dto.setTipoMovimiento(movimiento.getTipoMovimiento());
        dto.setValor(movimiento.getValor());
        dto.setSaldo(movimiento.getSaldo());
        dto.setEstado(movimiento.isEstado());
        // Asigna solo el ID de la cuenta desde la relación
        dto.setFkCuenta(movimiento.getFkCuenta() != null ? movimiento.getFkCuenta().getIdCuenta() : null);
        return dto;
    }
	
	public static Movimiento toEntity(MovimientoDTO dto, Cuenta cuenta) {
        Movimiento movimiento = new Movimiento();
        movimiento.setIdMovimiento(dto.getIdMovimiento());
        movimiento.setFecha(dto.getFecha());
        movimiento.setTipoMovimiento(dto.getTipoMovimiento());
        movimiento.setValor(dto.getValor());
        movimiento.setSaldo(dto.getSaldo());
        movimiento.setEstado(dto.isEstado());
        // Asocia la cuenta al movimiento
        movimiento.setFkCuenta(cuenta);
        return movimiento;
    }
}
