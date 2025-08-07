package com.example.ai_travel_agent_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
	 @Bean
	    public OpenAPI travelAgentOpenAPI() {
	        return new OpenAPI()
	                .info(new Info()
	                        .title("Agentic Travel AI API")
	                        .description("Spring AI-powered travel booking agent")
	                        .version("1.0.0"));
	    }

}
