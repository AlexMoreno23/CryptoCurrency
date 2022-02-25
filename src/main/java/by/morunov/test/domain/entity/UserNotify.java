package by.morunov.test.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Alex Morunov
 */
@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class UserNotify {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    @ManyToOne
    @JoinColumn(name = "cryptocurrency_id")
    private Cryptocurrency cryptocurrency;

    public UserNotify(String username, Cryptocurrency cryptocurrency) {
        this.username = username;
        this.cryptocurrency = cryptocurrency;
    }
}
