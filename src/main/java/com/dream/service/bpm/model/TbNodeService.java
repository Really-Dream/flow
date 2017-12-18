package com.dream.service.bpm.model;

import com.dream.entity.bpm.model.TbNode;

import java.util.List;

/**
 * Created by Dream
 * 2017/12/18.
 */
public interface TbNodeService {

    void save(TbNode tbNode);
    void save(List<TbNode> tbNodes);
    void deleteAllByProcDefId(String procDefId);
    List<TbNode> findAllByProcDefId(String procDefId);
}
