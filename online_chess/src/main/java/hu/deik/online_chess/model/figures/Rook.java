package hu.deik.online_chess.model.figures;

import hu.deik.online_chess.model.Color;
import hu.deik.online_chess.model.Direction;
import hu.deik.online_chess.model.Position;
import hu.deik.online_chess.model.Table;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Figure implements ChessFigure {

    public Rook(Color color, String strPos) {
        super(color, Position.toPosition(strPos));
    }

    @Override
    public List<Position> getAllPossibleMoves(Table table) {
        List<Position> validMoves = new ArrayList<>();

        for(Direction direction : Direction.getStraightDirections()){
            validMoves.addAll(getValidMovesFromOneDirectionManyStep(table,direction));
        }

        return validMoves;
    }

    @Override
    public String getFigureAsString() {
        if(color == Color.WHITE){
            return " WR ";
        }
        return " BR ";
    }
}
