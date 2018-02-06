package com.dream.bpm.model.support;

import org.activiti.engine.delegate.event.ActivitiEvent;

/**
 * Created by Dream
 * 2018/2/6.
 */
public interface EventHandler {

        void handle(ActivitiEvent event);
}
