package com.dream.bpm.model.support;

import com.dream.util.SpringContextHolder;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.impl.ActivitiEntityWithVariablesEventImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.runtime.StartingExecution;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dream
 * 2018/2/6.
 */
public class GlobalEventListener implements ActivitiEventListener{

    //存储配置的自定义事件监听器，详见spring-activiti.xml中的配置
    private Map<String,String> handlers = new HashMap<>();


    @Override
    public void onEvent(ActivitiEvent activitiEvent) {
        String eventType = activitiEvent.getType().name();
        //根据事件的类型ID,找到对应的事件处理器
        String eventHandlerBeanId = handlers.get(eventType);
        if(eventHandlerBeanId != null){
            Boolean isGet = true;
            if(eventType.equals("ENTITY_INITIALIZED")){
                if(activitiEvent instanceof ActivitiEntityWithVariablesEventImpl){
                    ActivitiEntityWithVariablesEventImpl eventImpl = (ActivitiEntityWithVariablesEventImpl) activitiEvent;
                    ExecutionEntity entity = (ExecutionEntity)eventImpl.getEntity();
                    StartingExecution startingExecution = entity.getStartingExecution();
                    ActivityImpl initial = startingExecution.getInitial();
                    String type = initial.getProperty("type").toString();
                    if(type.equals("startEvent")){
                        isGet = true;
                    }
                }else {
                    isGet = false;
                }
            }
            if(isGet){
                EventHandler handler = SpringContextHolder.getBean(eventHandlerBeanId);
                handler.handle(activitiEvent);
            }
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }

    public Map<String,String> getHandlers(){
        return handlers;
    }

    public void setHandlers(Map<String, String> handlers){
        this.handlers = handlers;
    }
}
