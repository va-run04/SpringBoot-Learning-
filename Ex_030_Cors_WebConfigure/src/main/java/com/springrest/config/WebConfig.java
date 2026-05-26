package com.springrest.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	public void addCorsMapping(CorsRegistry corsRegistry) {
		
		corsRegistry.addMapping("/**").
		allowedOrigins("http://localhost:3000","http://localhost:5173").
		allowedMethods("GET",
                "POST",
                "PUT",
                "PATCH",
                "DELETE").allowedHeaders("*").allowCredentials(true).maxAge(3600);
	}

}
