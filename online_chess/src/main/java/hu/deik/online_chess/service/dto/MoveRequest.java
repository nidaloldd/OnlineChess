package hu.deik.online_chess.service.dto;

import hu.deik.online_chess.model.Position;
import lombok.Data;

@Data
public class MoveRequest {
    private String from;
    private String to;
    private String id;
}
