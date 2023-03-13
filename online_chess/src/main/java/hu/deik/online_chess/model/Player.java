package hu.deik.online_chess.model;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Player {
    @Id
    private Long id;
    private String username;
    private String password;
    private String role;
}
