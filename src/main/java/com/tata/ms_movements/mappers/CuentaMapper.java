package com.tata.ms_movements.mappers;

import com.tata.ms_movements.dtos.CuentaDTO;
import com.tata.ms_movements.models.Cliente;
import com.tata.ms_movements.models.Cuenta;

public class CuentaMapper {

	 //Convierte de Cuenta a CuentaDTO
    public static CuentaDTO toDto(Cuenta cuenta) {
        CuentaDTO dto = new CuentaDTO();
        dto.setIdCuenta(cuenta.getIdCuenta());
        dto.setNumeroCuenta(cuenta.getNumeroCuenta());
        dto.setTipoCuenta(cuenta.getTipoCuenta());
        dto.setSaldoInicial(cuenta.getSaldoInicial());
        dto.setEstado(cuenta.isEstado());
        // Asigna solo el ID del cliente desde la relación
        dto.setFkCliente(cuenta.getFkCliente() != null ? cuenta.getFkCliente().getIdCliente() : null);
        return dto;
    }

    public static Cuenta toEntity(CuentaDTO dto, Cliente cliente) {
        Cuenta cuenta = new Cuenta();
        cuenta.setIdCuenta(dto.getIdCuenta());
        cuenta.setNumeroCuenta(dto.getNumeroCuenta());
        cuenta.setTipoCuenta(dto.getTipoCuenta());
        cuenta.setSaldoInicial(dto.getSaldoInicial());
        cuenta.setEstado(dto.isEstado());
        //Asocia el cliente a la cuenta
        cuenta.setFkCliente(cliente);
        return cuenta;
    }
}
