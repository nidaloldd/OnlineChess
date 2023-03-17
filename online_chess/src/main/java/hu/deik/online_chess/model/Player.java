package hu.deik.online_chess.model;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;


@Entity(name = "player")
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column( unique=true, nullable=false )
    private String username;
    @Column( nullable=false )
    private String password;
    private String role;
    private String activation;
    private Boolean enabled;
}
