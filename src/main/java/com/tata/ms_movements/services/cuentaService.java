package com.tata.ms_movements.services;

import com.tata.ms_movements.dtos.Response;
import com.tata.ms_movements.models.Cuenta;

import java.util.List;

public interface cuentaService {
    public Response<List<Cuenta>> findAllCuentas();
    public Response<Cuenta> findCuentaById(Long id);
    public Response<Boolean> createCuenta(Cuenta cuenta);
    public Response<Cuenta> updateCuenta(Cuenta cuenta);
    public Response<Boolean> deleteCuenta(Long id);
}
