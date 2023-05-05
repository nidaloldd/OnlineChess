package hu.deik.online_chess.model.figures;

import hu.deik.online_chess.model.Color;
import hu.deik.online_chess.model.Direction;
import hu.deik.online_chess.model.Draw.DrawFigure;
import hu.deik.online_chess.model.Position;
import hu.deik.online_chess.model.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public abstract class Figure {
    public final String imageSource;
    private boolean isFigureNotMoved;
    protected Color color;
    protected Position position;

    protected Figure(Color color, Position position){
        this.color = color;
        this.position = position;
        isFigureNotMoved = true;
        imageSource = DrawFigure.getPNG(this);
    }

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
    public List<Position> getValidMoves(Table table) {
        return getValidMoves(table,true);
    }
    public abstract List<Position> getValidMoves(Table table,boolean handleKingInCheck);
    public abstract String figureAsString();
    public List<Position> getValidMoveFromOneDirectionOnlyOneStep(Table table,Direction direction){
        List<Position> validMoves = new ArrayList<>();
        Position actualPosition = position;

        actualPosition = actualPosition.stepToDirection(direction);
        if(actualPosition.isPositionNotValid() || table.isPositionOccupiedByAlly(actualPosition,color)){
            return validMoves;
        }
        validMoves.add(actualPosition);
        return validMoves;
    }
    public List<Position> getValidMovesFromOneDirectionManyStep(Table table,Direction direction ){
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

    public List<Position> handleKingInCheck(Table table,List<Position> validMoves){

        Position startingPos = new Position(position.getPosX(), position.getPosY());
        List<Position>forRemove = new ArrayList<>();
        List<Figure> originalFigures = new ArrayList<>(table.getFigures());

        for(Position p : validMoves){
            Figure takenFigure = null;
            if(table.getFigureOn(p) != null){
                takenFigure = table.getFigureOn(p);
                table.getFigures().remove(takenFigure);
            }

            position = p;

            if(Boolean.TRUE.equals(table.isKingInCheck(color))){
                forRemove.add(p);
            }

            if(takenFigure != null){
                table.getFigures().add(takenFigure);
            }
            position = startingPos;
        }
        validMoves.removeAll(forRemove);
        table.setFigures(originalFigures);
        return validMoves;
    }

    public static Class<?> getFigureTypeBy(Character character){
        HashMap<Character,  Class<?>> figureTypes = new HashMap<>();
        figureTypes.put('K', King.class);
        figureTypes.put('Q', Queen.class);
        figureTypes.put('B', Bishop.class);
        figureTypes.put('N', Knight.class);
        figureTypes.put('R', Rook.class);

        return figureTypes.getOrDefault(character, Object.class);
    }

    public static Character getCharacterBy(Class<?> type){
        HashMap<Class<?>,Character> figureTypes = new HashMap<>();
        figureTypes.put(King.class,'K');
        figureTypes.put(Queen.class,'Q');
        figureTypes.put(Bishop.class,'B');
        figureTypes.put(Knight.class,'N');
        figureTypes.put(Rook.class,'R');

        return figureTypes.getOrDefault(type, 'X');
    }


}

