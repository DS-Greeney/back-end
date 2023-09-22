package com.greeneyback.member.service;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatgptService chatgptService;
    public String getChatResponse(String prompt) {
        // chatGPT에게 질문을 던짐
        return chatgptService.sendMessage(prompt);
    }
}
