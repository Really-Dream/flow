package com.dream.bpm.model.entity;

import com.dream.util.SpringContextHolder;
import lombok.Data;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.Map;

/**
 * 流程相关信息
 * Created by Dream
 * 2018/2/27.
 */
@Data
public class Bpm {

    //流程定义ID
    private String procDefId;
    //流程定义Key
    private String procDefKey;
    //流程实例ID
    private String procInsId;

    //任务执行ID
    private String executionId;
    //任务ID
    private String taskId;
    //任务名称
    private String taskName;
    //任务Key
    private String taskDefKey;

    //业务ID
    private String businessKey;

    public Bpm(String taskId){
        Task task = SpringContextHolder.getBean(TaskService.class).createTaskQuery().taskId(taskId).singleResult();
        this.procDefId = task.getProcessDefinitionId();
        this.procDefKey = this.procDefId.split(":")[0];
        this.procInsId = task.getProcessInstanceId();

        this.executionId = task.getExecutionId();
        this.taskId = taskId;
        this.taskName = task.getName();
        this.taskDefKey = task.getTaskDefinitionKey();

        Map<String,Object> map = SpringContextHolder.getBean(RuntimeService.class).getVariables(procInsId);
        this.businessKey = map.get("businessKey").toString();
    }
}
