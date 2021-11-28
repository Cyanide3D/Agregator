package ru.news.Agregator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.news.Agregator.model.Filter;
import ru.news.Agregator.model.News;
import ru.news.Agregator.model.User;
import ru.news.Agregator.repo.NewsRepo;
import ru.news.Agregator.utils.LogUtils;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepo newsRepo;

    public void deleteNews(Long id) {
        News entity = newsRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        newsRepo.delete(entity);
    }

    public void saveNews(News news, User user) {
        try {
            news.setPostDate(LocalDateTime.now());
            news.setUser(user);
            newsRepo.save(news);
        } catch (Exception e) {
            LogUtils.error(this, "Can't save entity", e);
        }
    }

    public News getNewsById(Long id) {
        if (id == null)
            return new News();

        return newsRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<News> getAllNews() {
        return newsRepo.findAll();
    }

    public List<News> getFilteredNews(Filter filter) {
        return newsRepo.findByNameIgnoreCaseContainingAndPostDateAfter(filter.getPartOfName(), filter.getSearchDate());
    }

}
