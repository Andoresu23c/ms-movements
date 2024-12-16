package com.tata.ms_movements.controllers;

import com.tata.ms_movements.dtos.MovimientoDTO;
import com.tata.ms_movements.dtos.Response;
import com.tata.ms_movements.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    //Endpoint para listar todos los movimientos activos
    @GetMapping
    public ResponseEntity<Response<List<MovimientoDTO>>> getAllMovimientos() {
        Response<List<MovimientoDTO>> response = movimientoService.findAllMovimientos();
        return ResponseEntity.status(response.getType().equals("Error") ? HttpStatus.BAD_REQUEST : HttpStatus.OK).body(response);
    }

    //Endpoint para obtener un movimiento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Response<MovimientoDTO>> getMovimientoById(@PathVariable Long id) {
        Response<MovimientoDTO> response = movimientoService.findMovimientosById(id);
        return ResponseEntity.status(response.getType().equals("Error") ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(response);
    }

    //Endpoint para registrar un nuevo movimiento
    @PostMapping
    public ResponseEntity<Response<MovimientoDTO>> createMovimiento(@RequestBody MovimientoDTO movimiento) {
        Response<MovimientoDTO> response = movimientoService.createMovimientos(movimiento);
        return ResponseEntity.status(response.getType().equals("Error") ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED).body(response);
    }

    //Endpoint para actualizar un movimiento existente
    @PutMapping("/{id}")
    public ResponseEntity<Response<MovimientoDTO>> updateCuenta(@PathVariable Long id, @RequestBody MovimientoDTO movimiento) {
        Response<MovimientoDTO> response = movimientoService.updateMovimientos(movimiento);
        movimiento.setIdMovimiento(id);
        if ("Error".equals(response.getType())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }

    //Endpoint para eliminar un movimiento (borrado lógico)
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deleteMovimiento(@PathVariable Long id) {
        Response<Boolean> response = movimientoService.deleteMovimientos(id);
        return ResponseEntity.status(response.getType().equals("Error") ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(response);
    }
}
