package com.dream.bpm.model.repository;

import com.dream.bpm.model.entity.TaskInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created By Dream
 * 2018/2/6 22:18
 */
public interface TaskInfoRepository extends JpaRepository<TaskInfo,String>{

    TaskInfo findById(String id);

    List<TaskInfo> findByAssigneeAndStatusAndTypeIsNull(String assignee,String status);
}
