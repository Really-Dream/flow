package com.dream.bpm.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Dream
 * 2018/1/2.
 */

@Data
@Entity
public class User {

    @Id
    private String userId;
    private String userName;


}
