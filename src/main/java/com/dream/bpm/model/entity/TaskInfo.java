package com.dream.bpm.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Dream
 * 2018/2/6.
 */
@Entity
@Data
public class TaskInfo {

    @Id
    private String id;

    //业务编号
    private String businessKey;

    //任务名称-业务层面
    private String wfinstname;

    //处理人
    private String assignee;

    //任务节点名称
    private String name;

    //流程执行ID
    private String executionId;

    //流程实例ID
    private String processInstanceId;

    //流程定义ID
    private String processDefinitionId;

    //任务节点Key
    private String taskDefinitionKey;

    //任务创建时间
    private Date createTime;

    //任务提交时间
    private Date completeTime;

    //下一步任务Key
    private String nextCode;

    //任务类型-待办，待阅
    private String type;

    //提交选项
    private String completeType;

    //状态
    private String status;


}
