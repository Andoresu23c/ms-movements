package com.tata.ms_movements.services;

import com.tata.ms_movements.dtos.Response;
import com.tata.ms_movements.models.Cliente;
import com.tata.ms_movements.models.Cuenta;

import java.util.List;

public interface clienteService {
    public Response<List<Cliente>> findAllCliente();
    public Response<Cliente> findClienteById(Long id);
    public Response<Cliente> createCliente(Cliente cliente);
    public Response<Cliente> updateCliente(Cliente cliente);
    public Response<Cliente> deleteCliente(Long id);
}
