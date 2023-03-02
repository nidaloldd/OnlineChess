package hu.deik.online_chess.service.dto;

import hu.deik.online_chess.model.Player;
import lombok.Data;

@Data
public class ConnectRequest {
    private Player player;
    private String gameId;
}
