package com.dream.bpm.model.serviceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Dream
 * 2018/1/16.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StartServiceImplTest {

    @Autowired
    StartServiceImpl startService;

    @Test
    public void startProcess() throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("createUser","lisi");
        startService.startProcess("BatchOnline",map);
    }

    @Test
    public void complete() throws Exception {

    }

}