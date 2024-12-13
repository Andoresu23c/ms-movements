package com.tata.ms_movements.repositories;

import com.tata.ms_movements.models.Cuenta;
import com.tata.ms_movements.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface personaRepository extends JpaRepository<Persona, Long> {
    List<Persona> findByEstadoTrue();
}
