package com.platzi.platzimarket.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * Clase para generar el token y trabajar con el
 */
@Component
public class JWTUtil {
    //Definicion de la clave que se tomara en cuenta para construir mi java token
    private static final String KEY = "pl4tz1";

    public String generateToken(UserDetails userDetails){
        //Otra forma de crear la fecha de expiracion es con esto
        /*Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY,10);
        calendar.getTimeInMillis();*/
        //Generamos el JWT y le pasamos los parametros de su creacion
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 *10))
                .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }
    // Validacion del token
    public boolean validateToken(String token, UserDetails userDetails){
        return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
    }

    //Extraemos el usuario usando los claims o propiedades del token
    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }
    //Comprobacion si ya expiro el token
    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }
    // Obtenemos los claims, verificando la firma del token, obtenemos el cuerpo del jwt separado por los objetos
    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }

}
