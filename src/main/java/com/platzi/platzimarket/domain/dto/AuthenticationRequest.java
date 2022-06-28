package com.platzi.platzimarket.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase DTO Plana para el recibimento de la solicitud
 */
@Getter
@Setter
public class AuthenticationRequest {
    private String username;
    private String password;
}
