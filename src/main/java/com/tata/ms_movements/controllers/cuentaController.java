package com.tata.ms_movements.controllers;

import com.tata.ms_movements.dtos.Response;
import com.tata.ms_movements.models.Cuenta;
import com.tata.ms_movements.services.impl.cuentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class cuentaController {
    @Autowired
    private cuentaServiceImpl cuentaService;

    //Endpoint listar general
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Response<List<Cuenta>>> getAllCuentas() {
        Response<List<Cuenta>> response = cuentaService.findAllCuentas();
        return ResponseEntity.ok(response);
    }
    //Endpoint listar por id
    @GetMapping("/{id}")
    public ResponseEntity<Response<Cuenta>> getCuentaById(@PathVariable Long id) {
        Response<Cuenta> response = cuentaService.findCuentaById(id);
        return ResponseEntity.ok(response);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public Response<Boolean> save(@RequestBody Cuenta cuenta) {
        return cuentaService.createCuenta(cuenta);
    }
    //Endpoint para editar por id
    @PutMapping("/{id}")
    public ResponseEntity<Response<Cuenta>> updateCuenta(
            @PathVariable Long id,
            @RequestBody Cuenta cuenta) {
        cuenta.setIdCuenta(id);
        Response<Cuenta> response = cuentaService.updateCuenta(cuenta);
        return ResponseEntity.ok(response);
    }

    //Endpoint para borrado lógico
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deleteCuenta(@PathVariable Long id) {
        Response<Boolean> response = cuentaService.deleteCuenta(id);
        return ResponseEntity.ok(response);
    }
}
