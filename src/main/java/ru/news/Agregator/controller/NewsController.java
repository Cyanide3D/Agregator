package ru.news.Agregator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.news.Agregator.model.Filter;
import ru.news.Agregator.model.News;
import ru.news.Agregator.model.User;
import ru.news.Agregator.service.NewsService;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public String news(Model model) {
        model.addAttribute("filter", new Filter());
        model.addAttribute("news", newsService.getAllNews());
        return "news/index";
    }

    @GetMapping("/news/filtered")
    public String filteredNews(Model model, @ModelAttribute("filter") Filter filter) {
        model.addAttribute("filter", new Filter());
        model.addAttribute("news", newsService.getFilteredNews(filter));
        return "news/index";
    }

    @GetMapping("/news/{id}")
    public String showNews(@PathVariable Long id, Model model) {
        model.addAttribute("news", newsService.getNewsById(id));
        return "news/show";
    }

    @GetMapping("/news/new")
    public String newNews(Model model) {
        model.addAttribute("news", new News());
        return "news/new";
    }

    @PostMapping("/news/new")
    public String saveNews(@ModelAttribute("news") News news, @AuthenticationPrincipal User user) {
        newsService.saveNews(news, user);
        return "redirect:/";
    }

    @GetMapping("/news/delete/{id}")
    public String deleteNews(@PathVariable("id") Long id) {
        newsService.deleteNews(id);
        return "redirect:/";
    }

}
