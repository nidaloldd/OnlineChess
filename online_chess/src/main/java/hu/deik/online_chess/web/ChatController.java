package hu.deik.online_chess.web;


import hu.deik.online_chess.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/chat.sendMessage")
    //@SendTo("/topic/chat")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        simpMessagingTemplate.convertAndSend("/topic/chat", chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    //@SendTo("/topic/chat")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());

        simpMessagingTemplate.convertAndSend("/topic/chat", chatMessage);
        return chatMessage;
    }

}
