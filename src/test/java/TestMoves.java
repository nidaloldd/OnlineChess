import Model.*;
import Model.Position;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMoves {
    ChessParty chessParty;
    @BeforeEach
    void init() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        chessParty = new ChessParty(player1,player2);
    }

    void jumpEarlyGame(){
        chessParty.getTable().makeMove(Position.toPosition("C2"),Position.toPosition("C4"));
        chessParty.getTable().makeMove(Position.toPosition("E7"),Position.toPosition("E6"));
        chessParty.getTable().makeMove(Position.toPosition("C4"),Position.toPosition("C5"));
        chessParty.getTable().makeMove(Position.toPosition("E6"),Position.toPosition("E5"));
        chessParty.getTable().makeMove(Position.toPosition("C5"),Position.toPosition("C6"));
        chessParty.getTable().makeMove(Position.toPosition("D7"),Position.toPosition("C6"));
        /*
                        """
                         BR  BN  BB  BQ  BK  BB  BN  BR\s
                         BP  BP  BP  00  00  BP  BP  BP\s
                         00  00  BP  00  00  00  00  00\s
                         00  00  00  00  BP  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         WP  WP  00  WP  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  WK  WB  WN  WR\s
                        """

         */
    }

    @Test
    void Pawn(){
        chessParty.getTable().makeMove(Position.toPosition("C2"),Position.toPosition("C4"));
        chessParty.getTable().makeMove(Position.toPosition("E7"),Position.toPosition("E6"));
        assertEquals("""
                         BR  BN  BB  BQ  BK  BB  BN  BR\s
                         BP  BP  BP  BP  00  BP  BP  BP\s
                         00  00  00  00  BP  00  00  00\s
                         00  00  VV  00  00  00  00  00\s
                         00  00  WP  00  00  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         WP  WP  00  WP  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  WK  WB  WN  WR\s
                        """,
                chessParty.getTable().makeTableToString(Position.toPosition("C4"))

        );
        chessParty.getTable().makeMove(Position.toPosition("C4"),Position.toPosition("C5"));
        chessParty.getTable().makeMove(Position.toPosition("E6"),Position.toPosition("E5"));
        chessParty.getTable().makeMove(Position.toPosition("C5"),Position.toPosition("C6"));

        assertEquals("""
                         BR  BN  BB  BQ  BK  BB  BN  BR\s
                         BP  VV  BP  VV  00  BP  BP  BP\s
                         00  00  WP  00  00  00  00  00\s
                         00  00  00  00  BP  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         WP  WP  00  WP  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  WK  WB  WN  WR\s
                        """
                ,chessParty.getTable().makeTableToString(Position.toPosition("C6"))

        );

        chessParty.getTable().makeMove(Position.toPosition("D7"),Position.toPosition("C6"));
        assertEquals("""
                         BR  BN  BB  BQ  BK  BB  BN  BR\s
                         BP  BP  BP  00  00  BP  BP  BP\s
                         00  00  BP  00  00  00  00  00\s
                         00  00  00  00  BP  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         WP  WP  00  WP  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  WK  WB  WN  WR\s
                        """
                ,chessParty.getTable().makeTableToString(Position.toPosition("C7"))

        );

    }
    @Test
    void King(){
        jumpEarlyGame();

        assertEquals("""
                         BR  BN  BB  BQ  BK  BB  BN  BR\s
                         BP  BP  BP  00  00  BP  BP  BP\s
                         00  00  BP  00  00  00  00  00\s
                         00  00  00  00  BP  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         WP  WP  00  WP  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  WK  WB  WN  WR\s
                        """
                ,chessParty.getTable().makeTableToString()
        );

        assertEquals("""
                         BR  BN  BB  BQ  BK  BB  BN  BR\s
                         BP  BP  BP  VV  VV  BP  BP  BP\s
                         00  00  BP  00  00  00  00  00\s
                         00  00  00  00  BP  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         WP  WP  00  WP  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  WK  WB  WN  WR\s
                        """
                ,chessParty.getTable().makeTableToString(Position.toPosition("E8"))
        );


        chessParty.getTable().makeMove(Position.toPosition("D2"),Position.toPosition("D4"));
        chessParty.getTable().makeMove(Position.toPosition("E8"),Position.toPosition("E7"));
        chessParty.getTable().makeMove(Position.toPosition("D4"),Position.toPosition("D5"));
        assertEquals("""
                         BR  BN  BB  BQ  VV  BB  BN  BR\s
                         BP  BP  BP  VV  BK  BP  BP  BP\s
                         00  00  BP  VV  00  VV  00  00\s
                         00  00  00  WP  BP  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         WP  WP  00  00  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  WK  WB  WN  WR\s
                        """
                ,chessParty.getTable().makeTableToString(Position.toPosition("E7"))
        );

        chessParty.getTable().makeMove(Position.toPosition("E7"),Position.toPosition("D6"));
        chessParty.getTable().makeMove(Position.toPosition("E1"),Position.toPosition("D2"));
        chessParty.getTable().makeMove(Position.toPosition("D6"),Position.toPosition("D5"));
        chessParty.getTable().makeMove(Position.toPosition("D2"),Position.toPosition("D3"));

        assertEquals("""
                         BR  BN  BB  BQ  00  BB  BN  BR\s
                         BP  BP  BP  00  00  BP  BP  BP\s
                         00  00  BP  VV  VV  00  00  00\s
                         00  00  VV  BK  BP  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  WK  00  00  00  00\s
                         WP  WP  00  00  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  00  WB  WN  WR\s
                        """
                ,chessParty.getTable().makeTableToString(Position.toPosition("D5"))
        );

        chessParty.getTable().makeMove(Position.toPosition("C8"),Position.toPosition("F5"));
        //assertTrue(chessParty.getTable().getKing(Color.White).inCheck);
        assertEquals("""
                         BR  BN  00  BQ  00  BB  BN  BR\s
                         BP  BP  BP  00  00  BP  BP  BP\s
                         00  00  BP  00  00  00  00  00\s
                         00  00  00  BK  BP  BB  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  VV  WK  VV  00  00  00\s
                         WP  WP  00  VV  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  00  WB  WN  WR\s
                        """
                ,chessParty.getTable().makeTableToString(Position.toPosition("D3"))
        );

        for(Position p : chessParty.getTable().getFigureOn("B2").getValidMoves(true)){
            System.out.println(Position.toString(p));
        }
        assertEquals("""
                         BR  BN  00  BQ  00  BB  BN  BR\s
                         BP  BP  BP  00  00  BP  BP  BP\s
                         00  00  BP  00  00  00  00  00\s
                         00  00  00  BK  BP  BB  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  WK  00  00  00  00\s
                         WP  WP  00  00  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  00  WB  WN  WR\s
                        """
                ,chessParty.getTable().makeTableToString(Position.toPosition("B2"))
        );
        assertEquals("""
                         BR  BN  00  BQ  00  BB  BN  BR\s
                         BP  BP  BP  00  00  BP  BP  BP\s
                         00  00  BP  00  00  00  00  00\s
                         00  00  00  BK  BP  BB  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  WK  00  00  00  00\s
                         WP  WP  00  00  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  00  WB  WN  WR\s
                        """
                ,chessParty.getTable().makeTableToString(Position.toPosition("D1"))
        );


    }

    @Test
    void StartingPositions(){
        assertEquals("""
                         BR  BN  BB  BQ  BK  BB  BN  BR\s
                         BP  BP  BP  BP  BP  BP  BP  BP\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         WP  WP  WP  WP  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  WK  WB  WN  WR\s
                        """
                ,chessParty.getTable().makeTableToString()
        );
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
