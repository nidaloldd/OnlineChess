package Model;

public enum Direction {
    Up(0, -1),
    UpRight(1, -1),
    Right(1, 0),
    DownRight(1, 1),
    Down(0, 1),
    DownLeft(-1, 1),
    Left(-1, 0),
    UpLeft(-1, -1);

    Direction(int xChange, int yChange) {
        this.xChange=xChange;
        this.yChange=yChange;
    }

    public static Direction[] getAllDirections(){
        Direction[] directions = {Up,UpRight,Right,DownRight,Down,DownLeft,Left,UpLeft};
        return directions;
    }
    public static Direction[] getStraightDirections(){
        Direction[] directions = {Up,Right,Down,Left};
        return directions;
    }
    public static Direction[] getDiagonalDirections(){
        Direction[] directions = {UpRight,DownRight,DownLeft,UpLeft};
        return directions;
    }
    public static Direction getDirectionOf(int rowChange,int colChange){
        for(Direction dir : getAllDirections()){
            if(dir.xChange==rowChange && dir.yChange==colChange){
                return dir;
            }
        }
        return null;
    }
    public static Direction getDirectionForward(Color color){
        if(color == Color.WHITE){return Up;}
        else if(color == Color.BLACK){return Down;}
        return null;
    }
    public static Direction getDirectionForwardRight(Color color){
        if(color == Color.WHITE){return UpRight;}
        else if(color == Color.BLACK){return DownLeft;}
        return null;
    }
    public static Direction getDirectionForwardLeft(Color color){
        if(color == Color.WHITE){return UpLeft;}
        else if(color == Color.BLACK){return DownRight;}
        return null;
    }

    public int getXChange() {
        return xChange;
    }

    public int getYChange() {
        return yChange;
    }

    private final int xChange;
    private final int yChange;


}
