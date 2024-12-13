package com.tata.ms_movements.repositories;

import com.tata.ms_movements.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface clienteRepository extends JpaRepository <Cliente, Long>{
    List<Cliente> findByEstadoTrue();
}
