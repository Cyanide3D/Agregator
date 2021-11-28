package ru.news.Agregator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.news.Agregator.exception.UserExistException;
import ru.news.Agregator.model.User;
import ru.news.Agregator.repo.UserRepo;
import ru.news.Agregator.utils.LogUtils;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        if (userRepo.getByUsername(user.getUsername()).isPresent())
            throw new UserExistException();

        try {
            user.setRole("USER");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
        } catch (Exception e) {
            LogUtils.error(this, "Can't save new user. ", e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.getByUsername(username).orElse(new User());
    }

}
