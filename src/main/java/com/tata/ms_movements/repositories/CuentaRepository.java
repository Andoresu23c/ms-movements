package com.tata.ms_movements.repositories;


import com.tata.ms_movements.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository <Cuenta, Long> {
    //Se usa para solamente listar las cuentas activas
    List<Cuenta> findByEstadoTrue();
    Optional<Cuenta> findByIdCuenta(Long id); 
}
