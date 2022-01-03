package tech.vtsign.authservice.configuration;

import feign.codec.ErrorDecoder;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.vtsign.authservice.proxy.FeignCustomErrorDecoder;

@Configuration
public class GlobalConfiguration {
    @Value("${tech.vtsign.api-url}")
    private String apiUrl;
    @Value("${server.servlet.context-path}")
    private String path;
    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignCustomErrorDecoder();
    }
    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI()
                .info(new Info().title("Auth Service API")
                        .description("API documentation Auth Service")
                        .version(appVersion)
                )
                .addServersItem(new Server().url(apiUrl+path));
    }

}
