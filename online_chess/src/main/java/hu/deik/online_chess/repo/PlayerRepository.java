package hu.deik.online_chess.repo;

import hu.deik.online_chess.data.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {
    Player findByUsername(String username);
    Player findByActivation(String code);
}
