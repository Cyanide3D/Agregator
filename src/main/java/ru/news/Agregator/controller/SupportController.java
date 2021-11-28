package ru.news.Agregator.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.news.Agregator.exception.UserExistException;
import ru.news.Agregator.model.User;
import ru.news.Agregator.utils.LogUtils;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@ControllerAdvice
public class SupportController {

    @ModelAttribute
    public void attributes(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", Objects.requireNonNullElse(user, new User("anon","USER")));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public String entityNotFound(Model model) {
        LogUtils.warning(this, "Can't find entity with some ID.");
        model.addAttribute("exception", "Can't find entity by ID.");
        return "exception";
    }


    @ExceptionHandler(UserExistException.class)
    public String userExist(Model model) {
        LogUtils.warning(this, "User with some cred. already exist.");
        model.addAttribute("exception", "User with this cred. already exist.");
        return "exception";
    }
}
