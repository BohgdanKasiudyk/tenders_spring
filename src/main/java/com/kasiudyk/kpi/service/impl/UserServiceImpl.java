package com.kasiudyk.kpi.service.impl;

import com.kasiudyk.kpi.persistence.UserRepository;
import com.kasiudyk.kpi.service.UserService;
import com.kasiudyk.kpi.service.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void register(User user) {
        userRepository.create(user);
    }

    @Override
    public User login(User user) {
        return userRepository.getByEmailAndPassword(user.getEmail(), user.getPassword());
    }
}
