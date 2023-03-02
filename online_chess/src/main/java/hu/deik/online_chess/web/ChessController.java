package hu.deik.online_chess.web;

import hu.deik.online_chess.model.*;
import hu.deik.online_chess.service.ChessPartyService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
public class ChessController {
    private final ChessPartyService chessPartyService;
    public ChessController(final ChessPartyService chessPartyService) {
        this.chessPartyService = chessPartyService;
    }

}
