package com.dream.bpm.model.serviceImpl;

import com.dream.bpm.model.service.TbNodeService;
import com.dream.bpm.model.util.ProcessInfoCmd;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dream
 * 2017/12/19.
 */
@Service
public class ModelService {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    ProcessEngine processEngine;

    @Autowired
    TbNodeService tbNodeService;

    @Transactional
    public void deploy(String modelId) throws Exception{
        Model modelData = repositoryService.getModel(modelId);
        ObjectNode modelNode;
        modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
        byte[] bpmnBytes;

        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        bpmnBytes = new BpmnXMLConverter().convertToXML(model,"UTF-8");

        String processName = modelData.getName() + ".bpmn20.xml";
        Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(
                processName, new String(bpmnBytes,"UTF-8")).deploy();

        List<ProcessDefinition> processDefinitions = repositoryService
                .createProcessDefinitionQuery()
                .deploymentId(deployment.getId()).list();

        for (ProcessDefinition pdf : processDefinitions) {
            processEngine.getManagementService().executeCommand(new ProcessInfoCmd(pdf.getId(),tbNodeService));
        }
    }

}
