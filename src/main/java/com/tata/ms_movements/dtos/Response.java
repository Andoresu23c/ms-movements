package com.tata.ms_movements.dtos;

import lombok.Data;

@Data
public class Response<T> {
    private String id;
    private String type;
    private T data;
    private String message;
    //Este apatado dara la respuesta completa de manera generica para poder identificar su tipo y contenido
    public Response() {}
    public Response(String id, String type, T data, String message) {
        this.id = id;
        this.type = type;
        this.data = data;
        this.message = message;
    }
}
