package hu.deik.online_chess.model;

public enum Color {
    WHITE,
    BLACK;

    public static Color getOpposite(Color color){
        if(color == Color.WHITE){return Color.BLACK;}
        else if(color == Color.BLACK){return Color.WHITE;}
        return null;
    }
}
