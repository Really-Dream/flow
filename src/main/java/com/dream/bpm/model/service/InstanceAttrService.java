package com.dream.bpm.model.service;

import com.dream.bpm.model.entity.InstanceAttr;

import java.util.List;

/**
 * Created by Dream
 * 2018/1/3.
 */
public interface InstanceAttrService {

    List<InstanceAttr> findAllByBusinessKey(String businessKey);
}
