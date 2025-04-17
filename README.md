
# 🤖 Smart Chatbot System

A simple Spring Boot-based AI chatbot integrated with Google's Gemini API and a responsive Bootstrap UI.

---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/ai/chatbot/
│   │   ├── SmartChatbotSystemApplication.java    # Main application class
│   │   ├── config/CorsConfig.java                # CORS setup
│   │   ├── controller/ChatController.java        # REST controller
│   │   ├── model/ChatRequest.java, ChatResponse.java
│   │   └── service/ChatService.java              # Gemini API integration logic
│   └── resources/
│       └── application.properties                # API key stored here
```

---

## 🔑 Gemini API Integration

- **Model**: `gemini-2.0-flash`
- **API Endpoint**:  
  `https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=YOUR_API_KEY`
- **API Key** is read from `application.properties`:

```
gemini.api.key=your-gemini-api-key
```

- **Sample Request Payload** (from service layer):
```json
{
  "contents": [
    {
      "parts": [
        { "text": "Your message here..." }
      ]
    }
  ]
}
```

---

## 🌐 User Interface (UI)

- Built using plain HTML + Bootstrap 5
- Responsive, dark-themed layout
- User messages align **right** (with "You:" label)
- Bot replies align **left**
- Chat auto-scrolls as new messages appear
- Simple input and "Send" button trigger POST to `/api/chat`

---

## 🧪 Running the App

1. Add your API key to `application.properties`
2. Run with:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Open `index.html` in browser to chat

---
