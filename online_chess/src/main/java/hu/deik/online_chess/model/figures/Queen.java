package hu.deik.online_chess.model.figures;

import hu.deik.online_chess.model.Color;
import hu.deik.online_chess.model.Direction;
import hu.deik.online_chess.model.Position;
import hu.deik.online_chess.model.Table;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Figure implements ChessFigure {

    public Queen(Color color, String strPos) {
        super(color, Position.toPosition(strPos));
    }
    @Override
    public List<Position> getValidMoves(Table table,boolean handleKingInCheck){
        List<Position> validMoves = new ArrayList<>();

        for(Direction direction : Direction.getAllDirections()){
            validMoves.addAll(getValidMovesFromOneDirectionManyStep(table,direction));
        }

        if(handleKingInCheck){
            validMoves = handleKingInCheck(table,validMoves);
        }

        return validMoves;
    }
    @Override
    public String figureAsString() {
        if(color == Color.WHITE){
            return " WQ ";
        }
        return " BQ ";
    }
}
