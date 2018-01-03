package com.dream.bpm.model.service;

import com.dream.bpm.model.entity.User;

/**
 * Created by Dream
 * 2018/1/3.
 */
public interface UserService {

    User findByUserId(String userId);
}
