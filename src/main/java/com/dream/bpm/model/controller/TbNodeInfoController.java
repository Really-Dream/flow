package com.dream.bpm.model.controller;

import com.dream.bpm.model.DTO.TbNodeInfoDTO;
import com.dream.bpm.model.entity.TbNodeInfo;
import com.dream.bpm.model.serviceImpl.TbNodeInfoServiceImpl;
import com.dream.util.KeyUtil;
import com.dream.util.ReturnJson;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created By Dream
 * 2017/12/24 2:04
 */
@Controller
@RequestMapping("/bpm/tbNodeInfo")
public class TbNodeInfoController {

    @Autowired
    TbNodeInfoServiceImpl nodeInfoService;

    @Autowired
    Gson gson;

    /**
     * 节点信息列表
     * @param procDefId 流程定义ID
     * @param taskDefKey 节点定义KEY
     */
    @RequestMapping("index")
    public String index(String procDefId, String taskDefKey, Model model){
        List<TbNodeInfo> list = nodeInfoService.findAllByProcDefIdAndAndTaskDefKey(procDefId,taskDefKey);
        model.addAttribute("list",list);
        model.addAttribute("procDefId",procDefId);
        model.addAttribute("taskDefKey",taskDefKey);
        return "model/nodeInfoList";
    }

    /**
     * 新增操作项
     * @param operationKey 操作项ID
     * @param operationName 操作项名称
     * @param operationSql  操作项SQL
     * @param userType  选人模式
     * @param procDefId 流程定义ID
     * @param taskDefKey 任务节点定义KEY
     * @return
     */
    @RequestMapping("create")
    @ResponseBody
    public String create(String operationKey,String operationName,String operationSql,String userType,String procDefId,String taskDefKey){
        try{
            TbNodeInfo tbNodeInfo = new TbNodeInfo();
            tbNodeInfo.setOperationId(KeyUtil.getUniqueKey());
            tbNodeInfo.setOperationKey(operationKey);
            tbNodeInfo.setOperationName(operationName);
            tbNodeInfo.setOperationSql(operationSql);
            tbNodeInfo.setUserType(userType);
            tbNodeInfo.setProcDefId(procDefId);
            tbNodeInfo.setTaskDefKey(taskDefKey);
            nodeInfoService.save(tbNodeInfo);
            return gson.toJson(ReturnJson.SUCCESS("/bpm/tbNodeInfo/index?procDefId="+procDefId+"&taskDefKey="+taskDefKey));
        }catch (Exception e){
            e.printStackTrace();
            return gson.toJson(ReturnJson.ERROR());
        }
    }

    /**
     * 删除操作项
     * @param operationId 操作项ID
     */
    @RequestMapping("delete")
    @ResponseBody
    public String delete(String operationId){
        try{
            nodeInfoService.delete(operationId);
            return gson.toJson(ReturnJson.SUCCESS("/"));
        }catch (Exception e){
            e.printStackTrace();
            return gson.toJson(ReturnJson.ERROR());
        }
    }

    /**
     * 获取操作区的内容：操作项，下一步处理人
     * @param procDefId 流程定义ID
     * @param taskDefKey    任务定义ID
     * @param businessKey   任务唯一标识
     */
    @RequestMapping("getOperations")
    @ResponseBody
    public List<TbNodeInfoDTO> getOperations(String procDefId, String taskDefKey,String businessKey){
        return nodeInfoService.findTbNodeInfoDTOList(procDefId,taskDefKey,businessKey);
    }

}
