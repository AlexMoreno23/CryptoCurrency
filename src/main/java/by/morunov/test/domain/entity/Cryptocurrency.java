package by.morunov.test.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Alex Morunov
 */

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Cryptocurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String crypto;
    private String currency;
    @Column(name = "price_USD")
    private Double price;

    public Cryptocurrency(String crypto, String currency, Double price) {
        this.crypto = crypto;
        this.currency = currency;
        this.price = price;
    }
}
