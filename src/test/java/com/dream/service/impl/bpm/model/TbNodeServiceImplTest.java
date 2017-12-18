package com.dream.service.impl.bpm.model;

import com.dream.entity.bpm.model.TbNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

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
    public void deleteAllByProcDefId() {
    }

    @Test
    public void findAllByProcDefId() {
    }

}