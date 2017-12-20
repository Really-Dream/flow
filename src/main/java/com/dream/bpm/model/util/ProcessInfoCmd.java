package com.dream.bpm.model.util;

import com.dream.bpm.model.entity.TbNode;
import com.dream.bpm.model.service.TbNodeService;
import com.dream.util.KeyUtil;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.impl.cmd.GetBpmnModelCmd;
import org.activiti.engine.impl.cmd.GetDeploymentProcessDefinitionCmd;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Dream
 * 2017/12/18.
 */
public class ProcessInfoCmd implements Command<Void>{

    private String processDefinitionId;

    private ProcessDefinitionEntity processDefinitionEntity;

    private Map<String,String> userMap;

    private TbNodeService repository;

    public ProcessInfoCmd(String processDefinitionId,TbNodeService repository){
        this.processDefinitionId = processDefinitionId;
        this.repository = repository;
    }

    @Override
    public Void execute(CommandContext commandContext) {
        this.processDefinitionEntity = new GetDeploymentProcessDefinitionCmd(processDefinitionId).execute(commandContext);
        BpmnModel bpmnModel = new GetBpmnModelCmd(processDefinitionId).execute(commandContext);

        this.setUser(bpmnModel);

        List<ActivityImpl> activityList = processDefinitionEntity.getActivities();
        List<TbNode> nodes = new ArrayList<>();
        for (ActivityImpl activity : activityList) {
            TbNode tbNode = new TbNode();
            tbNode.setId(KeyUtil.getUniqueKey());
            tbNode.setProcDefId(processDefinitionId);
            tbNode.setNodeId(activity.getId());
            tbNode.setNodeName((String) activity.getProperties().get("name"));
            tbNode.setNodeType((String) activity.getProperties().get("type"));
            tbNode.setNextUser(userMap.get(activity.getId()));
            nodes.add(tbNode);
        }
        repository.save(nodes);
        return null;
    }

    private void setUser(BpmnModel bpmnModel){
        Map<String,String> map = new HashMap<>();
        Map<String,String> formKeyMap = new HashMap<>();
        Process process = bpmnModel.getProcesses().get(0);
        Collection<FlowElement> flowElements = process.getFlowElements();
        for(FlowElement flowElement : flowElements){
            if(flowElement instanceof UserTask){
                map.put(flowElement.getId(),((UserTask) flowElement).getAssignee());
                formKeyMap.put(flowElement.getId(),((UserTask) flowElement).getFormKey());
            }else if(flowElement instanceof StartEvent){
                formKeyMap.put(flowElement.getId(),((StartEvent) flowElement).getFormKey());
            }
        }
        this.userMap = map;
    }

}
