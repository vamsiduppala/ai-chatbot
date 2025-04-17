package com.ai.chatbot.controller;


import com.ai.chatbot.model.ChatRequest;
import com.ai.chatbot.model.ChatResponse;
import com.ai.chatbot.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*") // For frontend access
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        return chatService.getBotResponse(request);
    }
}
