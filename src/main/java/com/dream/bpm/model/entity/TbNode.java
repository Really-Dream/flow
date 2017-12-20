package com.dream.bpm.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Dream
 * 2017/12/18.
 * 流程节点基础信息
 */
@Entity
@Data
public class TbNode {

    /**
     * 主键ID
     */
    @Id
    private String id;

    /**
     * 流程定义ID
     */
    private String procDefId;

    /**
     * 节点ID
     */
    private String nodeId;

    /**
     * 节点类型
     */
    private String nodeType;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 节点处理人表达式
     */
    private String nextUser;

}
