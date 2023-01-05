package Model;

import java.util.ArrayList;
import java.util.List;

public abstract class Figure {
    protected Color color;
    protected Position position;
    protected Table table;

    public Color getColor() {
        return color;
    }
    public Color getEnemyColor(){return Color.getOpposite(color);}
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }

    public Figure(Table table,Color color, Position position){
        this.color = color;
        this.position = position;
        this.table = table;
    }

    public boolean isPositionOccupied(Position position){
        if(table.getFigureOn(position)==null){return false;}
        return true;
    }
    public boolean isPositionOccupiedByEnemy(Position position, Color color){
        if(!isPositionOccupied(position)){return false;}

        if(table.getFigureOn(position).getColor() == Color.getOpposite(color)){return true;}
        return false;
    }

    public List<Position> getValidMoves() {
        return getValidMoves(true);
    }

    public abstract List<Position> getValidMoves(boolean handleKingInCheck);
    public List<Position> getValidMovesFromOneDirection(Direction direction,String step ){
        List<Position> validMoves = new ArrayList<>();
        Position actualPosition = position;
        while (true){
            actualPosition = actualPosition.stepToDirection(direction);
            if(!actualPosition.isPositionValid()){return validMoves;}
            if(isPositionOccupiedByEnemy(actualPosition,color)){
                validMoves.add(actualPosition);
                return validMoves;
            }
            if(!isPositionOccupied(actualPosition)) {
                validMoves.add(actualPosition);
            }
            if(step == ("onlyOneStep")){return validMoves;}
        }
    }
    public List<Position> getValidMovesFromOneDirection(Direction direction ){
        List<Position> validMoves = new ArrayList<>();
        Position actualPosition = position;
        while (true){
            actualPosition = actualPosition.stepToDirection(direction);
            if(!actualPosition.isPositionValid()){return validMoves;}
            if(isPositionOccupiedByEnemy(actualPosition,color)){
                validMoves.add(actualPosition);
                return validMoves;
            }
            if(!isPositionOccupied(actualPosition)) {
                validMoves.add(actualPosition);
            }
            else {
                return validMoves;
            }
        }
    }

    public List<Position> handleKingInCheck(List<Position> validMoves){

        if(!table.isKingInCheck(color)){return validMoves;}
        Position startingPos = new Position(position.getPosX(), position.getPosY());
        List<Position>forRemove = new ArrayList<>();
        for(Position p : validMoves){
            position = p;

            if(table.isKingInCheck(color)){
                forRemove.add(p);
            }
        }
        position = startingPos;
        validMoves.removeAll(forRemove);
        return validMoves;
    }

}

