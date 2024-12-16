package com.tata.ms_movements.controllers;

import com.tata.ms_movements.dtos.CuentaDTO;
import com.tata.ms_movements.dtos.Response;
import com.tata.ms_movements.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; 

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public ResponseEntity<Response<List<CuentaDTO>>> findAllCuentas() {
        Response<List<CuentaDTO>> response = cuentaService.findAllCuentas();

        if ("Error".equals(response.getType())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<CuentaDTO>> findCuentaById(@PathVariable Long id) {
        Response<CuentaDTO> response = cuentaService.findCuentaById(id);

        if ("Error".equals(response.getType())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<CuentaDTO>> createCuenta(@RequestBody CuentaDTO cuentaDTO) {
        Response<CuentaDTO> response = cuentaService.createCuenta(cuentaDTO);

        if ("Error".equals(response.getType())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<CuentaDTO>> updateCuenta(@PathVariable Long id, @RequestBody CuentaDTO cuentaDTO) {
        Response<CuentaDTO> response = cuentaService.updateCuenta(cuentaDTO);
        cuentaDTO.setIdCuenta(id);
        if ("Error".equals(response.getType())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deleteCuenta(@PathVariable Long id) {
        Response<Boolean> response = cuentaService.deleteCuenta(id);

        if ("Error".equals(response.getType())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
