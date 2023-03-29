package hu.deik.online_chess.repo;

import hu.deik.online_chess.data.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {
    Player findByUsername(String username);
    Player findByActivation(String code);
    @Modifying
    @Query("UPDATE player p SET p.score = :newScore WHERE p.id = :playerId")
    void updatePlayerScore(@Param("playerId") Long playerId, @Param("newScore") double score);
}
