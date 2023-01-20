import Model.ChessParty;
import Model.Player;
import Model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
    public class ChessGameTest {
        ChessParty chessParty;

        @BeforeEach
        void init() {
            Player player1 = new Player("player1");
            Player player2 = new Player("player2");
            chessParty = new ChessParty(player1,player2);
        }

        @Test
        void TestChessGame(){

            chessParty.getTable().makeMove(Position.toPosition("G1"),Position.toPosition("F3"));
            assertEquals("""
                         BR  BN  BB  BQ  BK  BB  BN  BR\s
                         BP  BP  BP  BP  BP  BP  BP  BP\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  WN  00  00\s
                         WP  WP  WP  WP  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  WK  WB  00  WR\s
                        """
                    ,chessParty.getTable().makeTableToString()
            );

            chessParty.getTable().makeMove(Position.toPosition("G8"),Position.toPosition("F6"));
            assertEquals("""
                         BR  BN  BB  BQ  BK  BB  00  BR\s
                         BP  BP  BP  BP  BP  BP  BP  BP\s
                         00  00  00  00  00  BN  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  WN  00  00\s
                         WP  WP  WP  WP  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  WK  WB  00  WR\s
                        """
                    ,chessParty.getTable().makeTableToString()
            );

            chessParty.getTable().makeMove(Position.toPosition("C2"),Position.toPosition("C4"));
            assertEquals("""
                         BR  BN  BB  BQ  BK  BB  00  BR\s
                         BP  BP  BP  BP  BP  BP  BP  BP\s
                         00  00  00  00  00  BN  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WP  00  00  00  00  00\s
                         00  00  00  00  00  WN  00  00\s
                         WP  WP  00  WP  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  WK  WB  00  WR\s
                        """
                    ,chessParty.getTable().makeTableToString()
            );

            chessParty.getTable().makeMove(Position.toPosition("G7"),Position.toPosition("G6"));
            assertEquals("""
                         BR  BN  BB  BQ  BK  BB  00  BR\s
                         BP  BP  BP  BP  BP  BP  00  BP\s
                         00  00  00  00  00  BN  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WP  00  00  00  00  00\s
                         00  00  00  00  00  WN  00  00\s
                         WP  WP  00  WP  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  WK  WB  00  WR\s
                        """
                    ,chessParty.getTable().makeTableToString()
            );

            chessParty.getTable().makeMove(Position.toPosition("B1"),Position.toPosition("C3"));
            assertEquals("""
                         BR  BN  BB  BQ  BK  BB  00  BR\s
                         BP  BP  BP  BP  BP  BP  00  BP\s
                         00  00  00  00  00  BN  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WP  00  00  00  00  00\s
                         00  00  WN  00  00  WN  00  00\s
                         WP  WP  00  WP  WP  WP  WP  WP\s
                         WR  00  WB  WQ  WK  WB  00  WR\s
                        """
                    ,chessParty.getTable().makeTableToString()
            );

            chessParty.getTable().makeMove(Position.toPosition("F8"),Position.toPosition("G7"));
            assertEquals("""
                         BR  BN  BB  BQ  BK  00  00  BR\s
                         BP  BP  BP  BP  BP  BP  BB  BP\s
                         00  00  00  00  00  BN  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WP  00  00  00  00  00\s
                         00  00  WN  00  00  WN  00  00\s
                         WP  WP  00  WP  WP  WP  WP  WP\s
                         WR  00  WB  WQ  WK  WB  00  WR\s
                        """
                    ,chessParty.getTable().makeTableToString()
            );

            chessParty.getTable().makeMove(Position.toPosition("D2"),Position.toPosition("D4"));
            assertEquals("""
                         BR  BN  BB  BQ  BK  00  00  BR\s
                         BP  BP  BP  BP  BP  BP  BB  BP\s
                         00  00  00  00  00  BN  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WP  WP  00  00  00  00\s
                         00  00  WN  00  00  WN  00  00\s
                         WP  WP  00  00  WP  WP  WP  WP\s
                         WR  00  WB  WQ  WK  WB  00  WR\s
                        """
                    ,chessParty.getTable().makeTableToString()
            );


        /*
        chessParty.getTable().makeMove(Position.toPosition("C7"),Position.toPosition("C6"));
        chessParty.getTable().makeMove(Position.toPosition("B3"),Position.toPosition("B4"));
        chessParty.getTable().makeMove(Position.toPosition("C6"),Position.toPosition("C5"));
        chessParty.getTable().makeMove(Position.toPosition("B4"),Position.toPosition("C5"));
        */
    }



}
