package com.example.ai_travel_agent_app.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.ai_travel_agent_app.tools.TravelAgentTools;

@Configuration
public class AiConfig {
	
	private final TravelAgentTools travelAgentTools;

    public AiConfig(TravelAgentTools travelAgentTools) {
        this.travelAgentTools = travelAgentTools;
    }

    @Bean
    public ChatClient chatClient(OpenAiChatModel chatModel) {
        return ChatClient.builder(chatModel)
                .defaultTools(travelAgentTools)// ✅ registers all @Tool methods
                .build();
    }

}
