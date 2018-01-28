package com.dream.bpm.model.repository;

import com.dream.bpm.model.entity.TbNodeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created By Dream
 * 2017/12/24 2:00
 */
public interface TbNodeInfoRepository extends JpaRepository<TbNodeInfo,String>{

    List<TbNodeInfo> findAllByProcDefIdAndAndTaskDefKey(String procDefId,String taskDefKey);

    TbNodeInfo findTbNodeInfoByOperationId(String operationId);

}


