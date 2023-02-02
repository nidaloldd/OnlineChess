package model.Draw;

import model.Color;
import model.figures.*;

public class DrawFigure {
    private static final String IMAGESOURCE = "/img/";
    public static String getPNG(Figure figure){

        if(figure.getColor() == Color.WHITE){
            if (King.class.equals(figure.getClass())) {
                return IMAGESOURCE +"WhiteKing.png";
            } else if (Queen.class.equals(figure.getClass())) {
                return IMAGESOURCE +"WhiteQueen.png";
            } else if (Bishop.class.equals(figure.getClass())) {
                return IMAGESOURCE +"WhiteBishop.png";
            } else if (Knight.class.equals(figure.getClass())) {
                return IMAGESOURCE +"WhiteKnight.png";
            } else if (Rook.class.equals(figure.getClass())) {
                return IMAGESOURCE +"WhiteRook.png";
            } else if (Pawn.class.equals(figure.getClass())) {
                return IMAGESOURCE +"WhitePawn.png";
            }
            return "";
        }else {
            if (King.class.equals(figure.getClass())) {
                return IMAGESOURCE +"BlackKing.png";
            } else if (Queen.class.equals(figure.getClass())) {
                return IMAGESOURCE +"BlackQueen.png";
            } else if (Bishop.class.equals(figure.getClass())) {
                return IMAGESOURCE +"BlackBishop.png";
            } else if (Knight.class.equals(figure.getClass())) {
                return IMAGESOURCE +"BlackKnight.png";
            } else if (Rook.class.equals(figure.getClass())) {
                return IMAGESOURCE +"BlackRook.png";
            } else if (Pawn.class.equals(figure.getClass())) {
                return IMAGESOURCE +"BlackPawn.png";
            }
            return "";
        }
    }

    public static String getPNGImageTag(Figure figure){
        return "<img src="+ getPNG(figure) + ">";
    }


}
