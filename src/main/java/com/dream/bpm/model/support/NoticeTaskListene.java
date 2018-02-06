package com.dream.bpm.model.support;

import org.activiti.engine.delegate.event.ActivitiEvent;

/**
 * Created by Dream
 * 2018/2/6.
 */
public class NoticeTaskListene implements EventHandler{

    @Override
    public void handle(ActivitiEvent event) {
        System.out.println("this is NoticeTaskListene");
    }
}
