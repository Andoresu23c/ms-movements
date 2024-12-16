package com.tata.ms_movements.services;

import com.tata.ms_movements.dtos.MovimientoDTO;
import com.tata.ms_movements.dtos.Response;

import java.util.List;

public interface MovimientoService {
    public Response<List<MovimientoDTO>> findAllMovimientos();
    public Response<MovimientoDTO> findMovimientosById(Long id);
    public Response<MovimientoDTO> createMovimientos(MovimientoDTO movimientoDTO);
    public Response<MovimientoDTO> updateMovimientos(MovimientoDTO movimientoDTO);
    public Response<Boolean> deleteMovimientos(Long id);
}
