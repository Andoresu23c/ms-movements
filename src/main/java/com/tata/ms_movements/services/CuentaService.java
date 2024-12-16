package com.tata.ms_movements.services;

import com.tata.ms_movements.dtos.CuentaDTO;
import com.tata.ms_movements.dtos.Response;

import java.util.List;

public interface CuentaService {
    Response<List<CuentaDTO>> findAllCuentas();
    Response<CuentaDTO> findCuentaById(Long id);
    Response<CuentaDTO> createCuenta(CuentaDTO cuentaDTO);
    Response<CuentaDTO> updateCuenta(CuentaDTO cuentaDTO);
    Response<Boolean> deleteCuenta(Long id);
}