package com.tata.ms_movements.mappers;

import com.tata.ms_movements.dtos.ClienteDTO;
import com.tata.ms_movements.models.Cliente;


public class ClienteMapper {

	public static ClienteDTO toDto(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(cliente.getIdCliente());
        dto.setNombres(cliente.getNombre());
        dto.setDireccion(cliente.getDireccion());
        dto.setTelefono(cliente.getTelefono());
        dto.setContrasenia(cliente.getContrasenia());
        dto.setEstado(cliente.isEstado());
        return dto;
    }
	
	public static Cliente toEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(dto.getIdCliente());
        cliente.setNombre(dto.getNombres());
        cliente.setDireccion(dto.getDireccion());
        cliente.setTelefono(dto.getTelefono());
        cliente.setContrasenia(dto.getContrasenia());
        cliente.setEstado(dto.isEstado());       
        return cliente;
    }
}
