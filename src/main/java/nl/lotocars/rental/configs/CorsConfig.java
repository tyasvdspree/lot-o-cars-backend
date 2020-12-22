package nl.lotocars.rental.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedMethods("PUT", "GET", "POST", "PATCH", "OPTIONS", "DELETE")
                        .allowedOrigins("http://localhost:4200", "https://localhost:4200")
                        .allowCredentials(true)
                        .allowedHeaders("*");
            }
        };
    }
}
