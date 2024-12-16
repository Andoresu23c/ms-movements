package com.tata.ms_movements;

import com.tata.ms_movements.controllers.ClienteController;
import com.tata.ms_movements.dtos.ClienteDTO;
import com.tata.ms_movements.dtos.Response;
import com.tata.ms_movements.services.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

	@InjectMocks //Instancia el controlador
    private ClienteController clienteController;

    @Mock //Simula el servicio de cliente
    private ClienteService clienteService;

    @Test
    void testFindClienteById_Success() {
        //Inputs de los datos de prueba
        String clienteId = "CLI001";
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(clienteId);
        clienteDTO.setNombres("Juan Pérez");
        clienteDTO.setDireccion("Calle Falsa 123");
        clienteDTO.setTelefono("0987654321");
        clienteDTO.setEstado(true);
        //Respuesta generica
        Response<ClienteDTO> response = new Response<>(
            UUID.randomUUID().toString(),
            "Object",
            clienteDTO,
            "Cliente encontrado correctamente."
        );

        //Mock del servicio
        Mockito.when(clienteService.findClienteById(clienteId)).thenReturn(response);//Llama al servicio como tal

        //Ejecución del método
        ResponseEntity<Response<ClienteDTO>> result = clienteController.getClienteById(clienteId);

        //Verificaciones
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("Cliente encontrado correctamente.", result.getBody().getMessage());
        assertEquals(clienteDTO, result.getBody().getData());
    }

}




