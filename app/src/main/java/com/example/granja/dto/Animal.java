package com.example.granja.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Animal {

    private String nombre;
    private int sonido;
    private int imagen;
    private String descripcion;
}
