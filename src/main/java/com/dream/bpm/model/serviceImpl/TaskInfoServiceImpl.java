package com.dream.bpm.model.serviceImpl;

import com.dream.bpm.model.entity.TaskInfo;
import com.dream.bpm.model.repository.TaskInfoRepository;
import com.dream.bpm.model.service.TaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Dream
 * 2018/2/6 22:19
 */
@Service
public class TaskInfoServiceImpl implements TaskInfoService{

    @Autowired
    TaskInfoRepository repository;

    @Override
    public void save(TaskInfo taskInfo) {
        repository.save(taskInfo);
    }

    @Override
    public TaskInfo findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<TaskInfo> findActie(String assignee, String status) {
        return repository.findByAssigneeAndStatusAndTypeIsNull(assignee,status);
    }
}
