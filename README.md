# ✈️ Agentic AI Flight Booking Assistant using Spring AI + OpenAI

This project is a hands-on implementation of an **Agentic AI** system built using **Spring AI 1.0.0** and **OpenAI** APIs. It demonstrates how to orchestrate multi-step tasks like **booking flights**, **retrieving real-time weather**, and **suggesting hotels** using a modular and intelligent workflow.

---

## 🔍 Features

- 🧠 Built using **Spring AI** function calling agents
- ✈️ Book flights dynamically based on natural language prompts
- 🌤️ Fetch **real-time weather** of the destination
- 🏨 Recommend **top hotels** at the destination
- 💬 Maintains **conversation history** for contextual decision making
- 🔒 Stateless + pluggable services with proper abstractions

---



## 🧩 Tech Stack
- Java 17+
- Spring Boot 3.x
- Spring AI 1.0.0
- OpenAI GPT-4 API
- Maven

---

## 🛠️ Setup & Run

### Prerequisites:
- Java 17+
- OpenAI API Key (set in `application.properties`)
- Maven

### Clone & Run

```bash
git clone https://github.com/yourusername/agentic-ai-flight-booking.git
cd agentic-ai-flight-booking
./mvnw spring-boot:run

**Create a file named application.properties under src/main/resources/ and add:**

spring.ai.openai.api-key=your-openai-api-key
spring.ai.openai.chat.model=gpt-4o
