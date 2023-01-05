package Model;

public enum Color {

    White,
    Black;

    public static Color getOpposite(Color color){
        if(color == Color.White){return Color.Black;}
        else if(color == Color.Black){return Color.White;}
        return null;
    }
}
