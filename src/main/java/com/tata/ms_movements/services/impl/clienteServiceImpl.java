package com.tata.ms_movements.services.impl;

import com.tata.ms_movements.dtos.ClienteDTO;
import com.tata.ms_movements.dtos.Response;
import com.tata.ms_movements.mappers.ClienteMapper;
import com.tata.ms_movements.models.Cliente;
import com.tata.ms_movements.repositories.ClienteRepository;
import com.tata.ms_movements.services.ClienteService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class clienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    @Override
    public Response<List<ClienteDTO>> findAllCliente() {
    	try {
            //Recuperar lista de entidades Cliente
            List<ClienteDTO> clientes = clienteRepository.findByEstadoTrue().stream()
                    .map(ClienteMapper::toDto)
                    .collect(Collectors.toList());;
           
            return new Response<>(UUID.randomUUID().toString(), "Object List", clientes, "Clientes listados correctamente.");
        } catch (Exception e) {
            return new Response<>(UUID.randomUUID().toString(), "Error", null, "Error al listar los clientes: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public Response<ClienteDTO> findClienteById(String idCliente) {
        try {
            
            Optional<Cliente> cliente = clienteRepository.findByIdCliente(idCliente);
            if (cliente.isEmpty()) {
                return new Response<>(UUID.randomUUID().toString(), "Error", null, "Cliente no encontrado con ID: " + idCliente);
            }

            //Mapear la entidad Cliente a ClienteDTO
            ClienteDTO clienteDTO = ClienteMapper.toDto(cliente.get());

            //Retornar respuesta con el DTO
            return new Response<>(UUID.randomUUID().toString(), "Object", clienteDTO, "Cliente encontrado correctamente.");
        } catch (Exception e) {
            //Manejar excepciones y retornar un mensaje de error
            return new Response<>(UUID.randomUUID().toString(), "Error", null, "Error al buscar el cliente: " + e.getMessage());
        }
    }


    @Override
    public Response<Cliente> createCliente(Cliente cliente) {
        try {
            //Validar campos heredados de Persona
            if (cliente.getNombre() == null || cliente.getIdentificacion() == null || cliente.getEdad() <= 0) {
                return new Response<>(UUID.randomUUID().toString(), "Error", null,
                        "Los datos de Persona están incompletos. Por favor, proporciona nombre, identificación y edad.");
            }

            //Verificar si ya existe un cliente con el mismo ID único
            if (clienteRepository.findByIdCliente(cliente.getIdCliente()).isPresent()) {
                return new Response<>(UUID.randomUUID().toString(), "Error", null,
                        "Ya existe un cliente con el ID único: " + cliente.getIdCliente());
            }

            //Persistir el cliente
            Cliente savedCliente = clienteRepository.save(cliente);
            return new Response<>(UUID.randomUUID().toString(), "Create", savedCliente,
                    "Cliente creado correctamente.");
        } catch (Exception e) {
            return new Response<>(UUID.randomUUID().toString(), "Error", null,
                    "Error al crear el cliente: " + e.getMessage());
        }
    }



    @Override
    public Response<Cliente> updateCliente(Cliente cliente) {
        try {
            Optional<Cliente> clienteExistente = clienteRepository.findByIdCliente(cliente.getIdCliente());
            if (clienteExistente.isEmpty()) {
                return new Response<>(UUID.randomUUID().toString(), "Error", null, "Cliente no encontrado con ID: " + cliente.getIdCliente());
            }

            Cliente clienteActualizado = clienteExistente.get();
            clienteActualizado.setContrasenia(cliente.getContrasenia());
            clienteActualizado.setEstado(cliente.isEstado());
            clienteActualizado.setNombre(cliente.getNombre()); //Actualiza datos heredados de Persona
            clienteActualizado.setDireccion(cliente.getDireccion());
            clienteActualizado.setTelefono(cliente.getTelefono());
            clienteActualizado.setGenero(cliente.getGenero());
            clienteActualizado.setIdentificacion(cliente.getIdentificacion());
            clienteActualizado.setEdad(cliente.getEdad());

            Cliente savedCliente = clienteRepository.save(clienteActualizado);

            return new Response<>(UUID.randomUUID().toString(), "Update", savedCliente, "Cliente actualizado correctamente.");
        } catch (Exception e) {
            return new Response<>(UUID.randomUUID().toString(), "Error", null, "Error al actualizar el cliente: " + e.getMessage());
        }
    }

    @Override
    public Response<Boolean> deleteCliente(String idCliente) {
        try {
            Optional<Cliente> cliente = clienteRepository.findByIdCliente(idCliente);
            if (cliente.isEmpty()) {
                return new Response<>(UUID.randomUUID().toString(), "Error", false, "Cliente no encontrado con ID: " + idCliente);
            }

            Cliente clienteEliminar = cliente.get();
            clienteEliminar.setEstado(false);
            clienteRepository.save(clienteEliminar);

            return new Response<>(UUID.randomUUID().toString(), "Delete", true, "Cliente eliminado correctamente.");
        } catch (Exception e) {
            return new Response<>(UUID.randomUUID().toString(), "Error", false, "Error al eliminar el cliente: " + e.getMessage());
        }
    }

}
