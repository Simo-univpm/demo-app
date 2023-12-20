package org.openapitools.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfiguration {

    @Bean(name = "org.openapitools.configuration.SpringDocConfiguration.apiInfo")
    OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("API demo-app")
                                .description("Documentazione delle API RESTful esposte dal mio progetto demo svolto in Accenture. <br>Queste ruotano attorno ad un utente e ci permettono di registrare, loggare, editare <br>un utente e di visualizzarne i dati.<br>Inoltre ci permettono di ottenere la data dell' ultimo accesso dell'utente e di generare <br>dei report in formato csv riguardanti le varie operazioni effettuate sul server. <br>")
                                .contact(
                                        new Contact()
                                                .name("Simone Francalancia")
                                                .url("https://github.com/Simo-univpm/demo-app")
                                                .email("simone.francalancia@accenture.com")
                                )
                                .version("1.0.0")
                )
        ;
    }
}