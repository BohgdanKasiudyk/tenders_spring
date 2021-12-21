package com.kasiudyk.kpi.service;

import com.kasiudyk.kpi.service.model.User;

public interface UserService {

    void register(User user);
    User login(User user);

}
