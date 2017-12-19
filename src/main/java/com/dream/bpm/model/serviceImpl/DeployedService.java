package com.dream.bpm.model.serviceImpl;

import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dream
 * 2017/12/19.
 */
@Service
public class DeployedService {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    TbNodeServiceImpl tbNodeService;

    @Transactional
    public void delete(String deploymentId,String procDefId) throws Exception{
        repositoryService.deleteDeployment(deploymentId);
        tbNodeService.deleteAllByProcDefId(procDefId);
    }
}
