package hu.deik.online_chess.data;


import hu.deik.online_chess.model.Color;
import hu.deik.online_chess.model.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Entity(name = "chessPuzzle")
@Data
@NoArgsConstructor
public class ChessPuzzle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String tableString;
    private Color color;
    private String solutionMoves;
    private String enemyMoves;

    public ChessPuzzle(String tableString,Color color,String solutionMoves,String enemyMoves){
        this.tableString = tableString;
        this.color = color;
        this.solutionMoves = solutionMoves;
        this.enemyMoves = enemyMoves;
    }
}
