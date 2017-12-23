package com.dream.bpm.model.serviceImpl;

import com.dream.bpm.model.entity.TbNodeInfo;
import com.dream.bpm.model.repository.TbNodeInfoRepository;
import com.dream.bpm.model.service.TbNodeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Dream
 * 2017/12/24 2:03
 */
@Service
public class TbNodeInfoServiceImpl implements TbNodeInfoService{

    @Autowired
    TbNodeInfoRepository repository;

    @Override
    public List<TbNodeInfo> findAllByProcDefIdAndAndTaskDefKey(String procDefId,String taskDefKey) {
        return repository.findAllByProcDefIdAndAndTaskDefKey(procDefId,taskDefKey);
    }

    @Override
    public void save(TbNodeInfo tbNodeInfo) {
        repository.save(tbNodeInfo);
    }
}
