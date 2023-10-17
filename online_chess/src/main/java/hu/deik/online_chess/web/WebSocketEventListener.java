package hu.deik.online_chess.web;

import hu.deik.online_chess.manager.ChessGameManager;
import hu.deik.online_chess.model.ChatMessage;
import hu.deik.online_chess.model.GameStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
public class WebSocketEventListener {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        String gameID = (String) headerAccessor.getSessionAttributes().get("gameID");
        if(username != null && gameID != null) {
            log.info("User Disconnected : " + username);

            var game = ChessGameManager.getGameManager().getGames().get(gameID);
            game.getTable().setGameOver(true);
            game.setStatus(GameStatus.FINISHED);
            messagingTemplate.convertAndSend("/topic/game-progress/" + gameID, game);


            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(ChatMessage.MessageType.LEAVE);
            chatMessage.setSender(username);
            messagingTemplate.convertAndSend("/topic/chat/"+gameID, chatMessage);

            log.info("Disconnection");
        }
    }
}
