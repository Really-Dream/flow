package com.dream.bpm.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created By Dream
 * 2017/12/24 1:47
 */
@Entity
@Data
public class TbNodeInfo {

    /**
     * 节点操作项ID
     */
    @Id
    private String operationId;

    /**
     * 流程定义ID
     */
    private String procDefId;

    /**
     * 节点KEy
     */
    private String taskDefKey;

    /**
     * 操作项名称
     */
    private String operationName;

    /**
     * 下一步处理人SQL
     */
    private String operationSql;

    /**
     * 下一步选人模式
     * 0无 1单人 2多人
     */
    private String userType;

}
