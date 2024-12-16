package com.tata.ms_movements.repositories;

import com.tata.ms_movements.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long>{
    List<Cliente> findByEstadoTrue();
    //Mediante la anotación Inheritance JPA raliza un join con la tabla de persona para poder recibir el dato requerido aquí
    Optional<Cliente> findByIdPersona(Long idPersona);
    Optional<Cliente> findByIdCliente(String idCliente);
}
