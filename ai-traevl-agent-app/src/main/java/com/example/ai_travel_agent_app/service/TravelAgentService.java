package com.example.ai_travel_agent_app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import com.example.ai_travel_agent_app.config.SessionManager;
import com.example.ai_travel_agent_app.constants.Constants;

@Service
public class TravelAgentService {

	private final ChatClient chatClient;
	private final SessionManager sessionManager;
	private final Map<String, List<Message>> sessionHistory = new HashMap<>();

	public TravelAgentService(ChatClient chatClient, SessionManager sessionManager) {
		this.chatClient = chatClient;
		this.sessionManager = sessionManager;
	}

	public String generateSessionId() {
		return UUID.randomUUID().toString();
	}

	public String handleUserRequest(String sessionId, String message) {if (sessionManager.isSessionExpired(sessionId)) {
        sessionManager.removeSession(sessionId);
    }

    sessionManager.updateSession(sessionId);

    // Create user message and store it
    UserMessage userMessage = new UserMessage(message);
    sessionManager.addMessageToHistory(sessionId, userMessage);

    // Prepare prompt
    List<Message> promptMessages = new ArrayList<>();
    promptMessages.add(new SystemMessage(Constants.SYSTEM_TEMPLATE_PROMPT));
    promptMessages.addAll(sessionManager.getHistory(sessionId));
    
    System.out.println(promptMessages);

    String response = chatClient.prompt()
            .messages(promptMessages)
            .advisors(advisor -> advisor.param("conversationId", sessionId))
            .call()
            .content();

    sessionManager.addMessageToHistory(sessionId, new AssistantMessage(response));
    return response;}

}
