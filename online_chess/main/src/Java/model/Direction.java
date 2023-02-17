package Java.model;

public enum Direction {
    UP(0, -1),
    UP_RIGHT(1, -1),
    RIGHT(1, 0),
    DOWN_RIGHT(1, 1),
    DOWN(0, 1),
    DOWN_LEFT(-1, 1),
    LEFT(-1, 0),
    UP_LEFT(-1, -1);

    Direction(int xChange, int yChange) {
        this.xChange=xChange;
        this.yChange=yChange;
    }

    public static Direction[] getAllDirections(){
        return new Direction[]{UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT};
    }
    public static Direction[] getStraightDirections(){
        return new Direction[]{UP, RIGHT, DOWN, LEFT};
    }
    public static Direction[] getDiagonalDirections(){
        return new Direction[]{UP_RIGHT, DOWN_RIGHT, DOWN_LEFT, UP_LEFT};
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
        if(color == Color.WHITE){return UP;}
        else{return DOWN;}
    }
    public static Direction getDirectionForwardRight(Color color){
        if(color == Color.WHITE){return UP_RIGHT;}
        else {return DOWN_LEFT;}
    }
    public static Direction getDirectionForwardLeft(Color color){
        if(color == Color.WHITE){return UP_LEFT;}
        else {return DOWN_RIGHT;}
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
