package com.greeneyback.member.controller.api;

import com.greeneyback.member.service.ChatService;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/greeney/main")
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final ChatService chatService;
    private final ChatgptService chatgptService;

    // chat-gpt와 간단한 채팅 서비스 소스
    @PostMapping("/chat")
    public HashMap<String, Object> chatbot(@RequestBody String question) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            String prompt = chatService.getChatResponse(question);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(prompt);

            map.put("success", Boolean.TRUE);
            map.put("message", jsonObj);
        } catch(Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        return map;
    }
}
