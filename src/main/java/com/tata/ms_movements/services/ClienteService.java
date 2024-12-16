package com.tata.ms_movements.services;

import com.tata.ms_movements.dtos.ClienteDTO;
import com.tata.ms_movements.dtos.Response;
import com.tata.ms_movements.models.Cliente;

import java.util.List;

public interface ClienteService {
    public Response<List<ClienteDTO>> findAllCliente();
    public Response<ClienteDTO> findClienteById(String idCliente);
    public Response<Cliente> createCliente(Cliente cliente);
    public Response<Cliente> updateCliente(Cliente cliente);
    public Response<Boolean> deleteCliente(String id);
}
