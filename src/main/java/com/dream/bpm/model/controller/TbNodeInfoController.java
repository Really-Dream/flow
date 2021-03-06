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
 * 已部署模型的节点相关信息
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
     */
    @RequestMapping("create")
    @ResponseBody
    public String create(TbNodeInfo tbNodeInfo){
        try{
            tbNodeInfo.setOperationId(KeyUtil.getUniqueKey());
            nodeInfoService.save(tbNodeInfo);
            return gson.toJson(ReturnJson.SUCCESS("/bpm/tbNodeInfo/index?procDefId="+tbNodeInfo.getProcDefId()+"&taskDefKey="+tbNodeInfo.getTaskDefKey()));
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
     * 修改的输入框
     * @param operationId 操作项ID
     */
    @RequestMapping("edit")
    public String edit(String operationId,Model model){
        TbNodeInfo tbNodeInfo = nodeInfoService.findTbNodeInfoByOperationId(operationId);
        model.addAttribute("tbNodeInfo",tbNodeInfo);
        return "model/nodeInfoModal";
    }

    /**
     * 保存修改之后的内容
     * @param tbNodeInfo 操作项实体类
     */
    @RequestMapping("saveEdit")
    @ResponseBody
    public String saveEdit(TbNodeInfo tbNodeInfo){
        try{
            nodeInfoService.update(tbNodeInfo);
            return gson.toJson(ReturnJson.SUCCESS("/"));
        }catch(Exception e){
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
