package ua.weatherex.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.weatherex.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
