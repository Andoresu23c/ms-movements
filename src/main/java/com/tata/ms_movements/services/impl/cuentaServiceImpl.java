package com.tata.ms_movements.services.impl;

import com.tata.ms_movements.dtos.Response;
import com.tata.ms_movements.models.Cuenta;
import com.tata.ms_movements.repositories.cuentaRepository;
import com.tata.ms_movements.services.cuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class cuentaServiceImpl implements cuentaService {

    @Autowired
    private cuentaRepository repo;
    LocalDateTime fecha = LocalDateTime.now();

    @Override
    public Response<List<Cuenta>> findAllCuentas() {
        try {
            List<Cuenta> cuentas = repo.findByEstadoTrue();
            return new Response<>(UUID.randomUUID().toString(), "List", cuentas, "Cuentas listadas correctamente.");
        }catch (Exception e) {
            return new Response<>(UUID.randomUUID().toString(), "Error", null, "Ocurrió un error al listar las cuentas: " + e.getMessage());
        }
    }

    @Override
    public Response<Cuenta> findCuentaById(Long id) {
        try {
            Cuenta cuenta = repo.findById(id).orElse(null);
            return new Response<>(UUID.randomUUID().toString(), "List", cuenta, "Cuentas listadas correctamente.");
        }catch (Exception e) {
            return new Response<>(UUID.randomUUID().toString(), "Error", null, "Ocurrió un error al listar las cuentas: " + e.getMessage());
        }
    }

    @Override
    public Response<Boolean> createCuenta(Cuenta cuenta) {
        try {
            repo.save(cuenta);
            return new Response<>(UUID.randomUUID().toString(), "List", true , "Cuenta creada correctamente.");
        } catch (Exception e) {
            return new Response<>(UUID.randomUUID().toString(), "Error", null, "Ocurrió un error al crear la cuenta: " + e.getMessage());
        }
    }

    @Override
    public Response<Cuenta> updateCuenta(Cuenta cuenta) {
        try {
            Cuenta cuentasExistentes = repo.findById(cuenta.getIdCuenta()).orElse(null);
            if (cuentasExistentes != null) {
                cuentasExistentes.setNumeroCuenta(cuenta.getNumeroCuenta());
                cuentasExistentes.setTipoCuenta(cuenta.getTipoCuenta());
                cuentasExistentes.setSaldoInicial(cuenta.getSaldoInicial());
                cuentasExistentes.setEstado(cuenta.isEstado());
                repo.save(cuentasExistentes);
            }
            return new Response<>(UUID.randomUUID().toString(), "List", cuentasExistentes , "Cuenta editada correctamente el " + fecha.toString());
        }
        catch (Exception e) {
            return new Response<>(UUID.randomUUID().toString(), "Error", null, "Ocurrió un error al editar la cuenta: " + e.getMessage());
        }
    }

    @Override
    public Response<Boolean> deleteCuenta(Long id) {
        Cuenta cuenta = repo.findById(id).orElse(null);
        try {
            if (cuenta == null || !cuenta.isEstado()) {
                return new Response<>(UUID.randomUUID().toString(), "Error", false, "Cuenta no encontrada.");
            }
            else{
                //Realiza un borrado lógico y no físico mediante el estado
                cuenta.setEstado(false);
                repo.save(cuenta);
                return new Response<>(UUID.randomUUID().toString(), "Boolean", true, "Cuenta eliminada correctamente el "+fecha.toString());
            }
        }catch (Exception e) {
            return new Response<>(UUID.randomUUID().toString(), "Error", false, "Ocurrió un error al eliminar la cuenta: ."+e.getMessage());
        }
    }
}
