package hu.deik.online_chess.model.figures;

import hu.deik.online_chess.model.Color;
import hu.deik.online_chess.model.Direction;
import hu.deik.online_chess.model.Position;
import hu.deik.online_chess.model.Table;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Figure implements ChessFigure {

    public Bishop(Color color, String strPos) {
        super(color, Position.toPosition(strPos));
    }

    public List<Position> getAllPossibleMoves(Table table) {
        List<Position> validMoves = new ArrayList<>();

        for(Direction direction : Direction.getDiagonalDirections()){
            validMoves.addAll(getValidMovesFromOneDirectionManyStep(table,direction));
        }

        return validMoves;
    }

    @Override
    public String getFigureAsString() {
        if(color == Color.WHITE){
            return " WB ";
        }
        return " BB ";
    }
}
