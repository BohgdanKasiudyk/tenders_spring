package com.kasiudyk.kpi.persistence;

import com.kasiudyk.kpi.service.model.User;
import org.springframework.stereotype.Repository;


public interface UserRepository {

    void create(User user);
    User getByEmailAndPassword(String email, String password);
    User getById(Long userId);
}
