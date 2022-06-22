package com.platzi.platzimarket.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Clase que me permite realizar la configuracion del swagger
 */
@Configuration
public class SwaggerConfig {

    /**
     * Metodo para definir la configuracion de mi documentacion de mi Api
     * @return la construccion de mi pagina de documentacion
     */
    @Bean
    public Docket api(){
        //Tipo de documentacion a usar con select mostramos que queremos que muestre en la aplicacion, y con el apis
        // le decimos que solo queremos que exponga los siguientes controladores en mi paquete base
        return  new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.platzi.platzimarket.web.controller"))
                //Se puede usar el PathSelectos.any() tambien
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointInfo());
    }

    /**
     * Metodo que me da una descripcion mas detallada de mi api
     * @return ApiInfo que se le pasa a mi Docket para documentar mas mi interfaz de swagger
     */
    private ApiInfo apiEndPointInfo(){
        return new ApiInfoBuilder().title("Api de mi tienda de supermercado")
                .description("Servicios para la consulta de productos y compras en un supermercado")
                .contact(new Contact("Elkin Fernandez","www.Elkin-Fernandez.com","elkinfernandez21@gmail.com"))
                .license("Apache 2.0")
                .version("1.0.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}
