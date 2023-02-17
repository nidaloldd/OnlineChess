package Java.model.figures;

import Java.model.Color;
import Java.model.Direction;
import Java.model.Position;
import Java.model.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Figure {
    private boolean isFigureNotMoved;
    protected Color color;
    protected Position position;
    protected Table table;

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        isFigureNotMoved = false;
        this.position = position;
    }
    public boolean getIfFigureNotMoved(){
        return isFigureNotMoved;
    }

    protected Figure(Table table,Color color, Position position){
        this.color = color;
        this.position = position;
        this.table = table;
        isFigureNotMoved = true;
    }

    public List<Position> getValidMoves() {
        return getValidMoves(true);
    }

    public abstract List<Position> getValidMoves(boolean handleKingInCheck);
    public List<Position> getValidMovesFromOneDirectionOnlyOneStep(Direction direction){
        List<Position> validMoves = new ArrayList<>();
        Position actualPosition = position;
        while (true){
            actualPosition = actualPosition.stepToDirection(direction);
            if(actualPosition.isPositionNotValid()){return validMoves;}
            if(table.isPositionOccupiedByEnemy(actualPosition,color)){
                validMoves.add(actualPosition);
                return validMoves;
            }
            if(table.isPositionNotOccupied(actualPosition)) {
                validMoves.add(actualPosition);
            }
            return validMoves;
        }
    }
    public List<Position> getValidMovesFromOneDirectionManyStep(Direction direction ){
        List<Position> validMoves = new ArrayList<>();
        Position actualPosition = position;
        while (true){
            actualPosition = actualPosition.stepToDirection(direction);
            if(actualPosition.isPositionNotValid()){return validMoves;}
            if(table.isPositionOccupiedByEnemy(actualPosition,color)){
                validMoves.add(actualPosition);
                return validMoves;
            }
            if(table.isPositionNotOccupied(actualPosition)) {
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

    public static Class<?> getFigureTypeBy(Character character){
        HashMap<Character,  Class<?>> figureTypes = new HashMap<Character,  Class<?>>();
        figureTypes.put('K', King.class);
        figureTypes.put('Q', Queen.class);
        figureTypes.put('B', Bishop.class);
        figureTypes.put('N', Knight.class);
        figureTypes.put('R', Rook.class);

        return figureTypes.getOrDefault(character, Object.class);
    }


}

