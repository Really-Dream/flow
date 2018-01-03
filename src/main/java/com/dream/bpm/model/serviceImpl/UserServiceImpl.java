package com.dream.bpm.model.serviceImpl;

import com.dream.bpm.model.entity.User;
import com.dream.bpm.model.repository.UserRepository;
import com.dream.bpm.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Dream
 * 2018/1/3.
 */
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository repository;

    @Override
    public User findByUserId(String userId) {
        return repository.findByUserId(userId);
    }
}
