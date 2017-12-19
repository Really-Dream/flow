package com.dream.bpm.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Dream
 * 2017/12/18.
 */
@Entity
@Data
public class TbNode {

    @Id
    private String id;
    private String procDefId;
    private String nodeId;
    private String nodeType;
    private String nodeName;
    private String nextUser;

}
