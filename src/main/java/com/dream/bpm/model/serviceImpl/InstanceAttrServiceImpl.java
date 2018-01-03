package com.dream.bpm.model.serviceImpl;

import com.dream.bpm.model.entity.InstanceAttr;
import com.dream.bpm.model.repository.InstanceAttrRepository;
import com.dream.bpm.model.service.InstanceAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dream
 * 2018/1/3.
 */
@Service
public class InstanceAttrServiceImpl implements InstanceAttrService{

    @Autowired
    InstanceAttrRepository repository;

    @Override
    public List<InstanceAttr> findAllByBusinessKey(String businessKey) {
        return repository.findAllByBusinessKey(businessKey);
    }
}
