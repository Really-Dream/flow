package com.dream.bpm.model.support;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import java.util.Collections;
import java.util.List;

/**
 * Created by Dream
 * 2018/2/2.
 */
public class ProxyTaskListener implements TaskListener{

    private List<TaskListener> taskListeners = Collections.EMPTY_LIST;

    public void notify(DelegateTask delegateTask) {
        for (TaskListener taskListener : taskListeners) {
            taskListener.notify(delegateTask);
        }
    }

    public void setTaskListeners(List<TaskListener> taskListeners) {
        this.taskListeners = taskListeners;
    }
}
