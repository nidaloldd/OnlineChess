package hu.deik.online_chess.model;

public enum Color {
    WHITE,
    BLACK;

    public static Color getOpposite(Color color){
        if(color == Color.WHITE){return Color.BLACK;}
        else {return Color.WHITE;}
    }
}
