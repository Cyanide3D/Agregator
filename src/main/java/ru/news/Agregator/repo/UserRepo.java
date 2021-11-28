package ru.news.Agregator.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.news.Agregator.model.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> getByUsername(String username);

}
