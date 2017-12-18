package com.dream.repository.bpm.model;

import com.dream.entity.bpm.model.TbNode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Created by Dream
 * 2017/12/18.
 */
public interface TbNodeRepository extends JpaRepository<TbNode,String> {
    void deleteAllByProcDefId(String procDefId);
    List<TbNode> findAllByProcDefId(String procDefId);
}
