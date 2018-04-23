package com.dream.bpm.model.service;

import org.activiti.bpmn.model.UserTask;

import java.util.List;
import java.util.Map;

/**
 * Created by Dream
 * 2018/1/16.
 */
public interface FlowService {

    List<UserTask> getNextNode(String procDefId, String taskDefKey, Map<String,Object> map,List<UserTask> nextUser);
}
