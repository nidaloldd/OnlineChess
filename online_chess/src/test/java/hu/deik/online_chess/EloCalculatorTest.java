package java.hu.deik.online_chess;

import hu.deik.online_chess.model.GameResult;
import hu.deik.online_chess.service.PlayerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EloCalculatorTest {

    @Autowired
    PlayerService playerService;
    @Test
    void test(){

        Assertions.assertEquals(11.7,
                playerService.absoluteRatingChange(1750,1810, GameResult.WIN));
        Assertions.assertEquals(1.7,
                playerService.absoluteRatingChange(1750,1810, GameResult.DRAW));
        Assertions.assertEquals(8.3,
                playerService.absoluteRatingChange(1750,1810, GameResult.LOSE));


        Assertions.assertEquals(13.8,
                playerService.absoluteRatingChange(1200,1340, GameResult.WIN));
        Assertions.assertEquals(3.8,
                playerService.absoluteRatingChange(1200,1340, GameResult.DRAW));
        Assertions.assertEquals(6.2,
                playerService.absoluteRatingChange(1200,1340, GameResult.LOSE));


        Assertions.assertEquals(10,
                playerService.absoluteRatingChange(400,400, GameResult.WIN));
        Assertions.assertEquals(0,
                playerService.absoluteRatingChange(400,400, GameResult.DRAW));
        Assertions.assertEquals(10,
                playerService.absoluteRatingChange(400,400, GameResult.LOSE));


        Assertions.assertEquals(20,
                playerService.absoluteRatingChange(20,2344, GameResult.WIN));
        Assertions.assertEquals(10,
                playerService.absoluteRatingChange(20,2344, GameResult.DRAW));
        Assertions.assertEquals(0,
                playerService.absoluteRatingChange(20,2344, GameResult.LOSE));
    }
}
