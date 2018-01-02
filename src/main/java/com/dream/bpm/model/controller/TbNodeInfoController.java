package com.dream.bpm.model.controller;

import com.dream.bpm.model.entity.TbNodeInfo;
import com.dream.bpm.model.serviceImpl.TbNodeInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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


}
