package tech.vtsign.authservice.configuration;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.vtsign.authservice.proxy.FeignCustomErrorDecoder;

@Configuration
public class GlobalConfiguration {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignCustomErrorDecoder();
    }
}
