package com.dream.bpm.model.repository;

import com.dream.bpm.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dream
 * 2018/1/3.
 */
public interface UserRepository extends JpaRepository<User,String>{

    User findByUserId(String userId);

}
