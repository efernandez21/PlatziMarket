package com.platzi.platzimarket.web.security;


import com.platzi.platzimarket.domain.service.PlatziUserDetailsService;
import com.platzi.platzimarket.web.security.filter.JwtFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Extendemos esta clase para configuracion de seguridad en base a una clase ya definida
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PlatziUserDetailsService platziUserDetailsService;
    @Autowired
    private JwtFilterRequest jwtFilterRequest;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Configuracion en base a lo nosotros definamos
        auth.userDetailsService(platziUserDetailsService);
    }

    /**
     * Metodo sobrecargado para permitir el acceso a la documentacion de swagge
     * @throws Exception en caso de error
     */
    /*@Override
    public void configure(WebSecurity web)throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui",
                "/swagger-resources/**", "/configuration/security",
                "/swagger-ui/**", "/webjars/**");
    }*/
    //Método para permitir las peticiones a mi swagger
    @Override
    protected void configure(HttpSecurity http)throws Exception {
        //Desactivara las peticiones cruzadas y authorize unas peticiones
        http.csrf().disable().authorizeRequests()
                .antMatchers("/swagger*", "/**/authenticate","/v2/api-docs", "/configuration/ui",
                        "/swagger-resources/**", "/configuration/security",
                        "/swagger-ui/**", "/webjars/**").permitAll()
                .anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //Indicamos que es un filtro de usuario y contraseña
        http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
    }
    //Para el authenticationManagerBean
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
