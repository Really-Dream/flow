package com.dream.repository.bpm.model;

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
public class TbNodeRepositoryTest {

    @Autowired
    TbNodeRepository repository;

    @Test
    public void saveTest(){
        TbNode tbNode = new TbNode();
        tbNode.setId("1");
        tbNode.setNodeId("sda");
        tbNode.setNodeName("A");
        tbNode.setNextUser("s");
        tbNode.setProcDefId("s");
        tbNode.setNodeType("s");
        repository.save(tbNode);
    }

    @Test
    public void deleteTest(){
        repository.delete("1");
    }
}