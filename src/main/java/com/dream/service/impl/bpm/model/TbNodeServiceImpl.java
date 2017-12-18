package com.dream.service.impl.bpm.model;

import com.dream.entity.bpm.model.TbNode;
import com.dream.repository.bpm.model.TbNodeRepository;
import com.dream.service.bpm.model.TbNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dream
 * 2017/12/18.
 */
@Service
public class TbNodeServiceImpl implements TbNodeService{

    @Autowired
    TbNodeRepository repository;

    @Override
    public void save(TbNode tbNode) {
        repository.save(tbNode);
    }

    @Override
    public void save(List<TbNode> tbNodes) {
        repository.save(tbNodes);
    }

    @Override
    public void deleteAllByProcDefId(String procDefId) {
        repository.deleteAllByProcDefId(procDefId);
    }

    @Override
    public List<TbNode> findAllByProcDefId(String procDefId) {
        return repository.findAllByProcDefId(procDefId);
    }
}
