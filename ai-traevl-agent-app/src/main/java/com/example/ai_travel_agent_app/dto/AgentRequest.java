package com.example.ai_travel_agent_app.dto;

public class AgentRequest {
	private String sessionId;
    private String query;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }


}
