package com.tata.ms_movements.controllers;

import com.tata.ms_movements.dtos.ClienteDTO;
import com.tata.ms_movements.dtos.Response;
import com.tata.ms_movements.models.Cliente;
import com.tata.ms_movements.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<Response<List<ClienteDTO>>> getAllClientes() {
        Response<List<ClienteDTO>> response = service.findAllCliente();
        return ResponseEntity.status(response.getType().equals("Error") ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<ClienteDTO>> getClienteById(@PathVariable String id) {
        Response<ClienteDTO> response = service.findClienteById(id);
        return ResponseEntity.status(response.getType().equals("Error") ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Response<Cliente>> createCliente(@RequestBody Cliente cliente) {
        Response<Cliente> response = service.createCliente(cliente);
        return ResponseEntity.status(
                response.getType().equals("Error") ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED
        ).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Cliente>> updateCliente(
            @PathVariable String id,
            @RequestBody Cliente cliente) {
        cliente.setIdCliente(id); //Aseguramos que el ID coincida
        Response<Cliente> response = service.updateCliente(cliente);
        return ResponseEntity.status(response.getType().equals("Error") ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deleteCliente(@PathVariable String id) {
        Response<Boolean> response = service.deleteCliente(id);
        return ResponseEntity.status(response.getType().equals("Error") ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(response);
    }
    
}
