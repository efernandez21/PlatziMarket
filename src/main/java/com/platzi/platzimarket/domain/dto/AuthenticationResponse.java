package com.platzi.platzimarket.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase para la respuesta que daremos con un controlador en un servicio
 */
@AllArgsConstructor
@Getter
@Setter
public class AuthenticationResponse {
    private String jwt;
}
