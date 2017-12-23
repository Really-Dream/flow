package com.dream.bpm.model.service;

import com.dream.bpm.model.entity.TbNodeInfo;

import java.util.List;

/**
 * Created By Dream
 * 2017/12/24 2:02
 */
public interface TbNodeInfoService {

    List<TbNodeInfo> findAllByProcDefIdAndAndTaskDefKey(String procDefId,String taskDefKey);

    void save(TbNodeInfo tbNodeInfo);
}
