package hu.deik.online_chess.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;
    private String gameId;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

}
