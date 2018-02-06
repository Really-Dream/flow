package com.dream.bpm.model.support;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import java.util.Map;

/**
 * Created by Dream
 * 2018/2/6.
 */
public class TaskInfoListener implements TaskListener{


    @Override
    public void notify(DelegateTask delegateTask) {
        if("".equals(delegateTask.getEventName())){

        }

        Map<String,Object> map = delegateTask.getVariables();
        System.out.println("ssss");
    }
}
