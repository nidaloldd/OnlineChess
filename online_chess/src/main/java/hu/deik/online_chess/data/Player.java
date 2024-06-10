package hu.deik.online_chess.data;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "player")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column( unique=true, nullable=false )
    private String username;

    @Column( unique=true, nullable=false )
    private String email;
    @Column( nullable=false )
    private String password;
    private double score;
    private String role;
    private String activation;
    private Boolean enabled;

    public Player(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


}
