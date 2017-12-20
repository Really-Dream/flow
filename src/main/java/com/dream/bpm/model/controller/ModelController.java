package com.dream.bpm.model.controller;

import com.dream.bpm.model.serviceImpl.ModelService;
import com.dream.util.Convert2Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by Dream
 * 2017/12/13.
 * 模型控制类
 */
@Controller
@RequestMapping("/bpm/model")
public class ModelController {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    ModelService modelService;

    @Autowired
    Gson gson;


    /**
     * 首页
     */
    @RequestMapping("index")
    public String index(){
        return "model/modelList";
    }

    /**
     * model列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map list(){
        List<Model> list = repositoryService.createModelQuery().orderByCreateTime().desc().list();
        return Convert2Page.getPage(list,list.size());
    }

    /**
     * 部署
     * @param modelId modelID
     */
    @RequestMapping("deploy")
    @ResponseBody
    public String deploy(String modelId){
        try {
            modelService.deploy(modelId);
            return gson.toJson("部署成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson("部署失败！");
        }
    }

    /**
     * 删除模型
     * @param modelId modelID
     */
    @RequestMapping("delete")
    @ResponseBody
    public String delete(String modelId){
        try{
            repositoryService.deleteModel(modelId);
        }catch (Exception e){
            e.printStackTrace();
            return gson.toJson("删除失败！");
        }
        return gson.toJson("删除成功！");
    }

    /**
     * 新建模型
     * @param modelName 名称
     * @param modelKey  Key
     * @param description   描述
     */
    @RequestMapping("createModel")
    public void createModel(String modelName, String modelKey,String description, HttpServletRequest request, HttpServletResponse response){
        try {
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

            RepositoryService repositoryService = processEngine.getRepositoryService();

            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            Model modelData = repositoryService.newModel();

            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, modelName);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName(modelName);
            modelData.setKey(modelKey);

            //保存模型
            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
            response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelData.getId());
        } catch (Exception e) {
            System.out.println("创建模型失败：");
        }
    }

    /**
     * 获取model的XML
     * @param modelId modelID
     */
    @RequestMapping("getBpmnXML")
    @ResponseBody
    public String getBpmnXML(String modelId){
        try{
            Model model = repositoryService.getModel(modelId);
            byte[] modelEditorSource = repositoryService.getModelEditorSource(model.getId());


            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            JsonNode editorNode = new ObjectMapper().readTree((new String(modelEditorSource,"UTF-8")).getBytes("UTF-8"));
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);

            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            byte[] exportBytes = xmlConverter.convertToXML(bpmnModel,"UTF-8");

            String XML =  new String(exportBytes,"UTF-8");
            return gson.toJson(XML);
        }catch (Exception e){
            e.printStackTrace();
            return gson.toJson("获取数据失败！");
        }
    }

    /**
     * 保存XML
     * @param modelId modelID
     * @param bpmnXML XML
     * @return
     */
    @RequestMapping("saveModelXML")
    @ResponseBody
    public String saveModelXML(String modelId,String bpmnXML){
        try {
            String unescapeHtml = StringEscapeUtils.unescapeHtml4(bpmnXML);
            InputStream in_nocode = new ByteArrayInputStream(unescapeHtml.getBytes("UTF-8"));
            XMLInputFactory xmlFactory  = XMLInputFactory.newInstance();
            XMLStreamReader reader = xmlFactory.createXMLStreamReader(in_nocode);

            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            BpmnModel bpmnModel = xmlConverter.convertToBpmnModel(reader);

            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            JsonNode j =jsonConverter.convertToJson(bpmnModel);

            byte[] modelEditorSource = new ObjectMapper().writeValueAsBytes(j);

            System.out.println(new String(modelEditorSource,"UTF-8"));
            repositoryService.addModelEditorSource(modelId, modelEditorSource);
            return gson.toJson("保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson("保存失败！");
        }
    }

    /**
     * XML文件导出
     * @param modelId modelID
     */
    @RequestMapping("export")
    public void export(String modelId,HttpServletResponse response){
        try {

            Model model = repositoryService.getModel(modelId);
            byte[] modelEditorSource = repositoryService.getModelEditorSource(model.getId());


            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            JsonNode editorNode = new ObjectMapper().readTree((new String(modelEditorSource,"UTF-8")).getBytes("UTF-8"));
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);

            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            byte[] exportBytes = xmlConverter.convertToXML(bpmnModel,"UTF-8");

            ByteArrayInputStream in = new ByteArrayInputStream(exportBytes);
            IOUtils.copy(in, response.getOutputStream());

            response.setHeader("Content-Disposition", "attachment; filename=" + model.getName()+".bpmn");
            response.flushBuffer();
        } catch (Exception e) {
        }
    }

}
