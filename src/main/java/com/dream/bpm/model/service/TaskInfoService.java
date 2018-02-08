package com.dream.bpm.model.service;

import com.dream.bpm.model.entity.TaskInfo;

import java.util.List;

/**
 * Created By Dream
 * 2018/2/6 22:19
 */
public interface TaskInfoService {

    void save(TaskInfo taskInfo);

    TaskInfo findById(String id);

    List<TaskInfo> findActive(String assignee, String status);

}
