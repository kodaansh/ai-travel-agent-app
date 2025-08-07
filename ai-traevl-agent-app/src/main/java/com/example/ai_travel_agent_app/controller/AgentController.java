package com.example.ai_travel_agent_app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ai_travel_agent_app.service.TravelAgentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AgentController {

    private final TravelAgentService travelAgentService;

    // Store chat history per sessionId (for UI display)
    private final Map<String, List<String>> chatHistory = new HashMap<>();

    public AgentController(TravelAgentService travelAgentService) {
        this.travelAgentService = travelAgentService;
    }

    /** Home page - Assign unique sessionId per browser session */
    @GetMapping("/")
    public String home(Map<String, Object> model, HttpSession session) {
        String sessionId = (String) session.getAttribute("sessionId");
       
        if (sessionId == null) {
            sessionId = travelAgentService.generateSessionId();
            session.setAttribute("sessionId", sessionId);
        }

        chatHistory.putIfAbsent(sessionId, new ArrayList<>());
        System.out.println("========="+sessionId+"=============");
        model.put("sessionId", sessionId);
        model.put("chatHistory", chatHistory.get(sessionId));
        return "index";
    }

    /** AJAX endpoint for sending messages to the AI agent */
    @PostMapping("/ask-ajax")
    @ResponseBody
    public Map<String, String> askAgentAjax(@RequestParam String sessionId,
                                           @RequestParam String query,
                                           HttpSession session) {
        // Make sure the server session matches this sessionId
    	 System.out.println("========="+sessionId+"=============");
        if (!sessionId.equals(session.getAttribute("sessionId"))) {
            session.setAttribute("sessionId", sessionId);
        }

        // Ask AI agent
        String response = travelAgentService.handleUserRequest(sessionId, query);

        // Store chat history for UI display
        chatHistory.computeIfAbsent(sessionId, k -> new ArrayList<>())
                   .add("👤 " + query);
        chatHistory.get(sessionId).add("🤖 " + response);

        // Return JSON response
        Map<String, String> result = new HashMap<>();
        result.put("query", query);
        result.put("response", response);
        return result;
    }

    /** Clear chat for current session */
    @PostMapping("/clear-chat")
    @ResponseBody
    public String clearChat(HttpSession session) {
        String sessionId = (String) session.getAttribute("sessionId");
        if (sessionId != null) {
            chatHistory.remove(sessionId);
        }
        return "cleared";
    }
}
