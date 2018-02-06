package com.dream.bpm.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Dream
 * 2018/2/6.
 */
@Entity
@Data
public class TaskInfo {

    @Id
    private String id;

    private String assignee;

    private String name;

    private String executionId;

    private String processInstanceId;

    private String processDefinitionId;

    private String taskDefinitionKey;
}
