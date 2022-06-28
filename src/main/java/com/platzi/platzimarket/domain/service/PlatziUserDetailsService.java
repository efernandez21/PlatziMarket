package com.platzi.platzimarket.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Servicio para la implementacion de la credencial de un usuario para mi aplicacion
 * este servicio es el que deberia hacer la verificacion en bd de las credenciales de los usuarios
 */
@Service
public class PlatziUserDetailsService  implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Definicion de un usuario con su contraseña, y un arrayList de Roles permitidos a este usuario, usamos
        // {noop} por lo que no esta codificada la contraseña
        return new User("alejandro","{noop}123456",new ArrayList<>());
    }
}
