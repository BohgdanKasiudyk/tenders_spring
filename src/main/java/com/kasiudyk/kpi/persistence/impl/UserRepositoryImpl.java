package com.kasiudyk.kpi.persistence.impl;

import com.kasiudyk.kpi.persistence.UserRepository;
import com.kasiudyk.kpi.service.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final List<User> usersDb;
    private Long idCounter;

    public UserRepositoryImpl() {
        this.usersDb = new ArrayList<>();
        idCounter = 1L;
    }

    @Override
    public void create(User user) {
        user.setId(idCounter++);
        usersDb.add(user);
    }

    @Override
    public User getByEmailAndPassword(String email, String password) {
        return usersDb.stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User getById(Long userId) {
        return usersDb.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }
}