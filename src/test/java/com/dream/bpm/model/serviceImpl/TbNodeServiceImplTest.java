package com.dream.bpm.model.serviceImpl;

import com.dream.bpm.model.entity.TbNode;
import com.dream.bpm.model.serviceImpl.TbNodeServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dream
 * 2017/12/18.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TbNodeServiceImplTest {

    @Autowired
    TbNodeServiceImpl service;

    @Test
    public void save(){
        TbNode tbNode = new TbNode();
        tbNode.setId("1");
        tbNode.setNodeId("sda");
        tbNode.setNodeName("A");
        tbNode.setNextUser("s");
        tbNode.setProcDefId("s");
        tbNode.setNodeType("s");
        service.save(tbNode);
    }

    @Test
    public void save1(){
    }

    @Test
    @Transactional
    public void deleteAllByProcDefId() {
        service.deleteAllByProcDefId("process:19:20012");
        List<TbNode> list = service.findAllByProcDefId("process:19:20012");
        Assert.assertEquals(list.size(),0);
    }

    @Test
    public void findAllByProcDefId() {
        List<TbNode> list = service.findAllByProcDefId("process:19:20012");
        Assert.assertNotNull(list);
    }

}