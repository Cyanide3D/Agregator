package ru.news.Agregator.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.news.Agregator.model.News;

import java.time.LocalDateTime;
import java.util.List;

public interface NewsRepo extends JpaRepository<News, Long> {

    List<News> findByNameIgnoreCaseContainingAndPostDateAfter(String name, LocalDateTime date);

}
