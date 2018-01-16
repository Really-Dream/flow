package com.dream.bpm.model.controller;

import com.dream.bpm.model.DTO.TbNodeInfoDTO;
import com.dream.bpm.model.entity.TbNodeInfo;
import com.dream.bpm.model.serviceImpl.TbNodeInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created By Dream
 * 2017/12/24 2:04
 */
@Controller
@RequestMapping("tbNodeInfo")
public class TbNodeInfoController {

    @Autowired
    TbNodeInfoServiceImpl nodeInfoService;

    @RequestMapping("index")
    public String index(){
        return "";
    }

    @RequestMapping("list")
    public List<TbNodeInfo> list(String procDefId, String taskDefKey){
        return nodeInfoService.findAllByProcDefIdAndAndTaskDefKey(procDefId,taskDefKey);
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
