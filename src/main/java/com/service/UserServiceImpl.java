package com.service;

import com.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(String login) {
        User user = new User();
        user.setLogin(login);
        user.setPassword("d033e22ae348aeb5660fc2140aec35850c4da997");//user.setPassword("admin");
        return user;
    }

}
