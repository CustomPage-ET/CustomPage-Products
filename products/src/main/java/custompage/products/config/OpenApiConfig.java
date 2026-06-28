package custompage.products.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CustomPage - Microservicio de Productos y Servicios")
                        .version("1.0.0")
                        .description("API para la gestión y autogestión del catálogo de productos para las PYMES")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")));
    }
}