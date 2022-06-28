package com.platzi.platzimarket.web.controller;

import com.platzi.platzimarket.domain.dto.AuthenticationRequest;
import com.platzi.platzimarket.domain.dto.AuthenticationResponse;
import com.platzi.platzimarket.domain.service.PlatziUserDetailsService;
import com.platzi.platzimarket.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    //Inyectamos el authenticationManager para probar las credenciales que recibamos
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PlatziUserDetailsService platziUserDetailsService;
    @Autowired
    private JWTUtil jwtUtil;

    /**
     * Metodo que recibira la solicitud de autenticacion
     * @param request la peticion que sera enviada en el body
     * @return un DTO con el token
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request){
        try{
            //Comprobacion del usuario y contraseña
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
            //Revisamos o cargamos los detalles del usuario en caso de ser correctos
            UserDetails userDetails = platziUserDetailsService.loadUserByUsername(request.getUsername());
            //Generamos el JWT
            String jwt = jwtUtil.generateToken(userDetails);
            //Retornamos el jwt
            return new ResponseEntity<>( new AuthenticationResponse(jwt),HttpStatus.OK);
        } catch (BadCredentialsException e){
            // Se lanza cuando el usuario no es el correcto y/O la contraseña no es la correcta
            //e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
