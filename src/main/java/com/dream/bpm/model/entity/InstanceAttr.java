package com.dream.bpm.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Dream
 * 2018/1/2.
 */
@Entity
@Data
public class InstanceAttr {

    @Id
    private String id;
    private String businessKey;
    private String attrName;
    private String attrCode;
    private String attrValue;
    private Date createTime;
    private String createUser;

}
