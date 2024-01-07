package com.healthcareapp.gptservice.dto;
import java.util.ArrayList;
import java.util.List;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ChatGPTRequest {
    private String model;
    private List<Message> messages;
    public ChatGPTRequest(String model, String content) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user",content));
    }
}
