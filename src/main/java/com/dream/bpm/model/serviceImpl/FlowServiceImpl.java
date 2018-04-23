package com.dream.bpm.model.serviceImpl;

import com.dream.bpm.model.service.FlowService;
import com.dream.util.FelSupport;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Dream
 * 2018/1/16.
 */
@Service
public class FlowServiceImpl implements FlowService{

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProcessEngine processEngine;

    /**
     * 启动流程
     * @param proDefKey 流程定义KEy
     * @param businessKey 业务ID
     * @param map 流程数据
     * @return 流程实例ID
     */
    public String startProcess(String proDefKey,String businessKey,Map<String,Object> map){
        //设置流程启动人
        identityService.setAuthenticatedUserId("zhangsan");
        //根据流程定义Key部署最新的流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(proDefKey,businessKey,map);
        //设置流程名称
        runtimeService.setProcessInstanceName(processInstance.getProcessInstanceId(),(String) map.get("wfinstname"));
        return processInstance.getProcessInstanceId();
    }

    public String complete(String taskId, Map<String,Object> map){
        taskService.complete(taskId,map);
        return null;
    }

    /**
     * 获取下一步的任务节点
     * @param procDefId 流程定义ID
     * @param taskDefKey    任务节点Key
     * @param map   ACT_RU_VARIABLE中的数据
     * @param nextUser 下一步的UserTask
     * @return 下一步的UserTask
     */
    @Override
    public List<UserTask> getNextNode(String procDefId, String taskDefKey, Map<String, Object> map,List<UserTask> nextUser) {
        //获取BpmnModel对象
        BpmnModel bpmnModel = processEngine.getRepositoryService().getBpmnModel(procDefId);
        //获取Process对象
        Process process = bpmnModel.getProcesses().get(bpmnModel.getProcesses().size()-1);
        //获取所有的FlowElement信息
        Collection<FlowElement> flowElements = process.getFlowElements();
        //获取当前节点信息
        FlowElement flowElement = getFlowElementById(taskDefKey,flowElements);

        //获取Task的出线信息--可以拥有多个
        List<SequenceFlow> outGoingFlows = null;
        if(flowElement instanceof Task){
            outGoingFlows = ((Task) flowElement).getOutgoingFlows();
        }else if(flowElement instanceof Gateway){
            outGoingFlows = ((Gateway) flowElement).getOutgoingFlows();
        }

        if(outGoingFlows != null && outGoingFlows.size()>0) {
            //遍历所有的出线--找到可以正确执行的那一条
            for (SequenceFlow sequenceFlow : outGoingFlows) {

                //1.有表达式，且为true
                //2.无表达式
                if(sequenceFlow.getConditionExpression() == null || Boolean.valueOf(String.valueOf(FelSupport.result(map,sequenceFlow.getConditionExpression())))){
                    //出线的下一节点
                    String nextFlowElementID = sequenceFlow.getTargetRef();
                    //查询下一节点的信息
                    FlowElement nextFlowElement = getFlowElementById(nextFlowElementID, flowElements);

                    //用户任务
                    if (nextFlowElement instanceof UserTask) {
                        nextUser.add((UserTask) nextFlowElement);
                    }
                    //排他网关
                    else if (nextFlowElement instanceof ExclusiveGateway) {
                        return getNextNode(procDefId, nextFlowElementID, map, nextUser);
                    }
                    //并行网关
                    else if (nextFlowElement instanceof ParallelGateway) {
                        return getNextNode(procDefId, nextFlowElementID, map, nextUser);
                    }
                    //接收任务
                    else if (nextFlowElement instanceof ReceiveTask) {
                        return getNextNode(procDefId, nextFlowElementID, map, nextUser);
                    }
                }
            }
        }
        return nextUser;
    }

    /**
     * 获取流程元素详细信息
     * @param Id 流程元素Id
     * @param flowElements 流程的全部元素
     * @return 流程元素
     */
    private FlowElement getFlowElementById(String Id,Collection<FlowElement> flowElements){
        for(FlowElement flowElement : flowElements){
            if(flowElement.getId().equals(Id)){
                return flowElement;
            }
        }
        return null;
    }

}
