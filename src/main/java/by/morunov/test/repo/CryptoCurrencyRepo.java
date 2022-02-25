package by.morunov.test.repo;

import by.morunov.test.domain.entity.Cryptocurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Alex Morunov
 */
@Repository
public interface CryptoCurrencyRepo extends JpaRepository<Cryptocurrency, Long> {

    @Query(value = "select c from Cryptocurrency c where c.crypto = :cryptoName order by c.id DESC LIMIT 1", nativeQuery = true)
    Cryptocurrency getByCryptoName(String cryptoName);


}
