package com.example.ai_travel_agent_app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ai_travel_agent_app.dto.AgentRequest;
import com.example.ai_travel_agent_app.service.TravelAgentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/travel")
@Tag(name = "Travel Agent", description = "Agentic AI endpoints for travel booking")
public class TravelAgentController {
	
	private final TravelAgentService travelAgentService;

    public TravelAgentController(TravelAgentService travelAgentService) {
        this.travelAgentService = travelAgentService;
    }

    @PostMapping("/ask")
    @Operation(summary = "Ask the travel agent", 
    description = "Send a query to the agentic AI to book flights, cancel, check weather, hotels, etc.")
    public Map<String, String> handleAgentRequest(@RequestBody AgentRequest request) {
        String sessionId = request.getSessionId();
        String query = request.getQuery();

        if (query == null || query.isEmpty()) {
            throw new IllegalArgumentException("Query must not be empty");
        }

        if (sessionId == null || sessionId.isEmpty()) {
            sessionId = travelAgentService.generateSessionId();
        }

        String response = travelAgentService.handleUserRequest(sessionId, query);

        Map<String, String> result = new HashMap<>();
        result.put("sessionId", sessionId);
        result.put("response", response);
        return result;
    }
	

}
