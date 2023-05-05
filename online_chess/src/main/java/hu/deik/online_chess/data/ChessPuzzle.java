package hu.deik.online_chess.data;


import hu.deik.online_chess.model.Color;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "chessPuzzle")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
