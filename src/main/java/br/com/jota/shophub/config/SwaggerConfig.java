package br.com.jota.shophub.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "ShotHub",
                version = "1.0",
                description = "Uma API de E-Commerce destinada ao cadastro de clientes, fornecedores e produtos, permitindo que cada fornecedor disponibilize múltiplos produtos para comercialização.",
                contact = @Contact(
                        name = "João Victor",
                        url = "https://portfolio-blue-iota-23.vercel.app/sobre",
                        email = "joaovictormdasilva676@gmail.com"
                )
        )
)
public class SwaggerConfig {
}
