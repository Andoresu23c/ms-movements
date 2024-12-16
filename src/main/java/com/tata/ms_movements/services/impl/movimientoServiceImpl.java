package com.tata.ms_movements.services.impl;

import com.tata.ms_movements.dtos.MovimientoDTO;
import com.tata.ms_movements.dtos.Response;
import com.tata.ms_movements.mappers.MovimientoMapper;
import com.tata.ms_movements.models.Cuenta;
import com.tata.ms_movements.models.Movimiento;
import com.tata.ms_movements.repositories.CuentaRepository;
import com.tata.ms_movements.repositories.MovimientoRepository;
import com.tata.ms_movements.services.MovimientoService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class movimientoServiceImpl implements MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepo;

    @Autowired
    private CuentaRepository cuentaRepo;

    LocalDateTime fecha = LocalDateTime.now();

    @Transactional
    @Override
    public Response<List<MovimientoDTO>> findAllMovimientos() {
        try {
        	List<MovimientoDTO> movimientoDTOs = movimientoRepo.findByEstadoTrue().stream()
                    .map(MovimientoMapper::toDto)
                    .collect(Collectors.toList());
            return new Response<>(
                    UUID.randomUUID().toString(),
                    "List",
                    movimientoDTOs,
                    "Movimientos listados correctamente.");
        } catch (Exception e) {
            return new Response<>(UUID.randomUUID().toString(), "Error", null, "Ocurrió un error al listar los movimientos: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public Response<MovimientoDTO> findMovimientosById(Long id) {
        try {
        	Optional<Movimiento> movimiento = movimientoRepo.findById(id);

            if (movimiento.isEmpty()) {
                return new Response<>(
                    UUID.randomUUID().toString(),
                    "Error",
                    null,
                    "Movimiento no encontrado con ID: " + id
                );
            }

            //Mapear la entidad Movimiento a un DTO
            MovimientoDTO movimientoDTO = MovimientoMapper.toDto(movimiento.get());
            return new Response<>(
                    UUID.randomUUID().toString(),
                    "Object",
                    movimientoDTO,
                    "Movimiento encontrado correctamente."
                );
        } catch (Exception e) {
            return new Response<>(UUID.randomUUID().toString(), "Error", null, "Ocurrió un error al buscar el movimiento: " + e.getMessage());
        }
    }

    @Override
    public Response<MovimientoDTO> createMovimientos(MovimientoDTO movimientoDTO) {
        try {
            //Valida que la cuenta existe
            Cuenta cuenta = cuentaRepo.findById(movimientoDTO.getFkCuenta())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con ID: " + movimientoDTO.getFkCuenta()));

            //Verifica antes si la cuenta esta activa o no
            if (!cuenta.isEstado()) {
                return new Response<>(UUID.randomUUID().toString(), "Error", null, "Su cuenta no está activa.");
            }
            //Valida saldo disponible si el movimiento es negativo
            if (movimientoDTO.getValor() < 0 && cuenta.getSaldoInicial() + movimientoDTO.getValor() < 0) {
                return new Response<>(UUID.randomUUID().toString(), "Error", null, "Saldo no disponible");
            }

            //Actualizar el saldo de la cuenta
            double nuevoSaldo = cuenta.getSaldoInicial() + movimientoDTO.getValor();
            cuenta.setSaldoInicial(nuevoSaldo);
            cuentaRepo.save(cuenta);

            //Registrar el movimiento
            Movimiento movimiento = MovimientoMapper.toEntity(movimientoDTO, cuenta);
            movimiento.setSaldo(nuevoSaldo); // Almacena el nuevo saldo después del movimiento
            movimiento.setFecha(LocalDate.now());
            Movimiento savedMovimiento = movimientoRepo.save(movimiento);

            //Mapear entidad a DTO para la respuesta
            MovimientoDTO savedMovimientoDTO = MovimientoMapper.toDto(savedMovimiento);

            return new Response<>(
                UUID.randomUUID().toString(),
                "Create",
                savedMovimientoDTO,
                "Movimiento registrado correctamente."
            );
        } catch (Exception e) {
            return new Response<>(
                UUID.randomUUID().toString(),
                "Error",
                null,
                "Ocurrió un error al registrar el movimiento: " + e.getMessage()
            );
        }
    }


    @Override
    public Response<MovimientoDTO> updateMovimientos(MovimientoDTO movimientoDTO) {
        try {
            //Valida que el movimiento existe realmente y esta persistido
            Movimiento movimiento = movimientoRepo.findById(movimientoDTO.getIdMovimiento())
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado con ID: " + movimientoDTO.getIdMovimiento()));

            //Valida que la cuenta asociada al movimiento existe
            Cuenta cuenta = cuentaRepo.findById(movimientoDTO.getFkCuenta())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con ID: " + movimientoDTO.getFkCuenta()));

            //Obtiene el valor original del movimiento
            double valorOriginal = movimiento.getValor();

            //Valida el saldo disponible si el movimiento es un retiro (valor negativo)
            double nuevoSaldo = cuenta.getSaldoInicial() - valorOriginal + movimientoDTO.getValor();
            if (movimientoDTO.getValor() < 0 && nuevoSaldo < 0) {
                return new Response<>(UUID.randomUUID().toString(), "Error", null, "Saldo no disponible.");
            }

            //Actualiza el saldo de la cuenta
            cuenta.setSaldoInicial(nuevoSaldo);
            cuentaRepo.save(cuenta);

            //Actualiza los datos del movimiento
            movimiento.setTipoMovimiento(movimientoDTO.getTipoMovimiento());
            movimiento.setValor(movimientoDTO.getValor());
            movimiento.setSaldo(nuevoSaldo); // Guardar el saldo después de la actualización
            movimiento.setEstado(movimientoDTO.isEstado());

            //Guarda el movimiento actualizado
            Movimiento updatedMovimiento = movimientoRepo.save(movimiento);

            //Mapea la entidad a DTO para la respuesta
            MovimientoDTO updatedMovimientoDTO = MovimientoMapper.toDto(updatedMovimiento);

            return new Response<>(
                UUID.randomUUID().toString(),
                "Update",
                updatedMovimientoDTO,
                "Movimiento actualizado correctamente."
            );
        } catch (Exception e) {
            return new Response<>(
                UUID.randomUUID().toString(),
                "Error",
                null,
                "Ocurrió un error al actualizar el movimiento: " + e.getMessage()
            );
        }
    }

	
    @Override
    public Response<Boolean> deleteMovimientos(Long id) {
        try {
            Optional<Movimiento> movimientoExiste = movimientoRepo.findById(id);
            //Valida si existe con ese id
            if (movimientoExiste.isEmpty()) {
                return new Response<>(
                        UUID.randomUUID().toString(),
                        "Error",
                        false,
                        "Movimiento no encontrado con ID: " + id
                );
            }

            Movimiento movimiento = movimientoExiste.get();
            movimiento.setEstado(false); //Borrado lógico
            movimientoRepo.save(movimiento);

            return new Response<>(
                    UUID.randomUUID().toString(),
                    "Delete",
                    true,
                    "Movimiento eliminado correctamente."
            );
        } catch (Exception e) {
            return new Response<>(
                    UUID.randomUUID().toString(),
                    "Error",
                    false,
                    "Ocurrió un error al eliminar el movimiento: " + e.getMessage()
            );
        }
    }

}

