package com.dream.bpm.model.support;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * Created by Dream
 * 2018/2/2.
 */
public class HumanTaskCopy implements TaskListener{
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("this is HumanTaskCopy");
    }
}
