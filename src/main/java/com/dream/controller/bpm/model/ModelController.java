package com.dream.controller.bpm.model;

import com.google.gson.Gson;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Dream
 * 2017/12/13.
 */
@Controller
@RequestMapping("/bpm/model")
public class ModelController {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    Gson gson;

    @RequestMapping("/list")
    @ResponseBody
    public Page<Model> list(){
        List<Model> list = repositoryService.createModelQuery().orderByCreateTime().desc().list();
//        PageRequest request = new PageRequest(page - 1, limit, null);
//        Page<Model> models =
        Page<Model> page1 = new PageImpl<>(list);
        return page1;
    }
}
