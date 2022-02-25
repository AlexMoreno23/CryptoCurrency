package by.morunov.test.repo;

import by.morunov.test.domain.entity.UserNotify;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alex Morunov
 */
public interface UserNotifyRepo extends JpaRepository<UserNotify, Long> {


}
