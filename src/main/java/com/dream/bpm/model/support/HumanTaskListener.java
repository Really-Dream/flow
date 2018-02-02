package com.dream.bpm.model.support;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;

/**
 * Created by Dream
 * 2018/2/2.
 */
public class HumanTaskListener implements ActivitiEventListener {


    @Override
    public void onEvent(ActivitiEvent activitiEvent) {
        System.out.println(activitiEvent.getProcessDefinitionId());
        System.out.println("this is HumanTaskListener");

    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
