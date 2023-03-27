package hu.deik.online_chess.repo;

import hu.deik.online_chess.data.ChessPuzzle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuzzleRepository extends JpaRepository<ChessPuzzle,Long> {
}
