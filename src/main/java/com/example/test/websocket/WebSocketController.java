package com.example.test.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class WebSocketController {

    @GetMapping
    public String response(){
        return "generate";
    }
}
