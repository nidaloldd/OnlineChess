package model;
import model.Draw.DrawTable;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        ChessParty chessParty = new ChessParty(player1,player2);
        Scanner in = new Scanner(System.in);

        System.out.println("Beginning of the Game ");

        while (true){
            System.out.println(chessParty.getTable().getActivePlayerColor()+" "+chessParty.getActivePlayer().getName()+"'s turn");

            String move = in.nextLine();
            if(move.equals("x")){
                System.out.println("exit");
                break;
            }
            chessParty.getTable().makeMove(move);
            DrawTable.toConsole(chessParty.getTable());
        }


    }
}
