package com.test.service.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080/card");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Akshay Mehta");
        myContact.setEmail("akshaymehta555@gmail.com");

        Info information = new Info()
            .title("Card Transaction APIs")
            .version("1.0")
            .description("This API exposes endpoints to manage card transactions.")
            .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}