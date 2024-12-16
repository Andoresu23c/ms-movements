package com.tata.ms_movements.services.impl;

import com.tata.ms_movements.dtos.CuentaDTO;
import com.tata.ms_movements.dtos.Response;
import com.tata.ms_movements.mappers.CuentaMapper;
import com.tata.ms_movements.models.Cliente;
import com.tata.ms_movements.models.Cuenta;
import com.tata.ms_movements.repositories.ClienteRepository;
import com.tata.ms_movements.repositories.CuentaRepository;
import com.tata.ms_movements.services.CuentaService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class cuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository repo;
    @Autowired
    private ClienteRepository  clienteRepository;
    LocalDateTime fecha = LocalDateTime.now();

    @Transactional
    @Override
    public Response<List<CuentaDTO>> findAllCuentas() {
        try {
            //Convierte la lista de entidades a DTOs
            List<CuentaDTO> cuentaDTOs = repo.findByEstadoTrue().stream()
                .map(CuentaMapper::toDto)
                .collect(Collectors.toList());
            return new Response<>(UUID.randomUUID().toString(), "List", cuentaDTOs, "Cuentas listadas correctamente.");
        }catch (Exception e) {
            return new Response<>(UUID.randomUUID().toString(), "Error", null, "Ocurrió un error al listar las cuentas: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public Response<CuentaDTO> findCuentaById(Long id) {
        try {
            
            Optional<Cuenta> cuenta = repo.findByIdCuenta(id);
            if (cuenta.isEmpty()) {
                return new Response<>(UUID.randomUUID().toString(), "Error", null, "Cliente no encontrado con ID: " + id);
            }

            //Mapear la entidad
            CuentaDTO cuentadto = CuentaMapper.toDto(cuenta.get());

            //Retornar respuesta con el DTO
            return new Response<>(UUID.randomUUID().toString(), "Object", cuentadto, "Cliente encontrado correctamente.");
        } catch (Exception e) {
            //Manejar excepciones y retornar un mensaje de error
            return new Response<>(UUID.randomUUID().toString(), "Error", null, "Error al buscar el cliente: " + e.getMessage());
        }
    }
    
    @Override
    public Response<CuentaDTO> createCuenta(CuentaDTO cuentaDTO) {
        try {
            Cliente cliente = clienteRepository.findByIdCliente(cuentaDTO.getFkCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + cuentaDTO.getFkCliente()));

            Cuenta cuenta = CuentaMapper.toEntity(cuentaDTO, cliente);
            Cuenta savedCuenta = repo.save(cuenta);
            CuentaDTO savedCuentaDTO = CuentaMapper.toDto(savedCuenta);

            return new Response<>(
                UUID.randomUUID().toString(),
                "Create",
                savedCuentaDTO,
                "Cuenta creada correctamente."
            );
        } catch (Exception e) {
            return new Response<>(
                UUID.randomUUID().toString(),
                "Error",
                null,
                "Ocurrió un error al crear la cuenta: " + e.getMessage()
            );
        }
    }

 
    @Override
    public Response<CuentaDTO> updateCuenta(CuentaDTO cuentaDTO) {
        try {
            // Validar que el ID no sea nulo
            if (cuentaDTO.getIdCuenta() == null) {
                throw new IllegalArgumentException("El ID de la cuenta no puede ser nulo.");
            }

            // Buscar la cuenta por ID
            Cuenta cuenta = repo.findById(cuentaDTO.getIdCuenta())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con ID: " + cuentaDTO.getIdCuenta()));

            // Buscar y validar el cliente
            Cliente cliente = clienteRepository.findByIdCliente(cuentaDTO.getFkCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + cuentaDTO.getFkCliente()));

            // Actualizar la cuenta
            cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
            cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
            cuenta.setSaldoInicial(cuentaDTO.getSaldoInicial());
            cuenta.setEstado(cuentaDTO.isEstado());
            cuenta.setFkCliente(cliente);

            // Guardar la cuenta actualizada
            Cuenta updatedCuenta = repo.save(cuenta);

            // Convertir a DTO y retornar respuesta
            CuentaDTO updatedCuentaDTO = CuentaMapper.toDto(updatedCuenta);

            return new Response<>(
                UUID.randomUUID().toString(),
                "Update",
                updatedCuentaDTO,
                "Cuenta actualizada correctamente."
            );
        } catch (IllegalArgumentException e) {
            return new Response<>(
                UUID.randomUUID().toString(),
                "Error",
                null,
                e.getMessage()
            );
        } catch (Exception e) {
            return new Response<>(
                UUID.randomUUID().toString(),
                "Error",
                null,
                "Ocurrió un error al actualizar la cuenta: " + e.getMessage()
            );
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
