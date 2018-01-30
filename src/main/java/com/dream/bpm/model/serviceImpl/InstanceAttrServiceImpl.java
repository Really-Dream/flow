package com.dream.bpm.model.serviceImpl;

import com.dream.bpm.model.entity.InstanceAttr;
import com.dream.bpm.model.repository.InstanceAttrRepository;
import com.dream.bpm.model.service.InstanceAttrService;
import com.dream.util.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Override
    public void save(InstanceAttr instanceAttr){
        repository.save(instanceAttr);
    }

    @Override
    public void save(Map<String,Object> map,String businessKey){
        List<InstanceAttr> old = repository.findAllByBusinessKey(businessKey);

        //如果
        List<InstanceAttr> list = new ArrayList<>();
        for (String key : map.keySet()) {
            InstanceAttr instanceAttr = new InstanceAttr();
            instanceAttr.setId(KeyUtil.getUniqueKey());
            instanceAttr.setAttrCode(key);
            instanceAttr.setAttrValue((String)map.get(key));
            instanceAttr.setCreateTime(new Date());
            instanceAttr.setCreateUser("zhangsan");
            instanceAttr.setAttrName("");
            instanceAttr.setBusinessKey(businessKey);
            list.add(instanceAttr);
        }
        repository.save(list);
    }
}
