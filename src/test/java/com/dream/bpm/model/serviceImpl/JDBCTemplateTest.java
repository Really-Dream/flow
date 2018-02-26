package com.dream.bpm.model.serviceImpl;

import com.dream.util.JDBCTemplate;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.FelContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



import java.util.List;

/**
 * Created by Dream
 * 2017/12/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JDBCTemplateTest {

    @Autowired
    JDBCTemplate jdbcTemplate;

    @Test
    public void getList() throws Exception {
        List<String> list = jdbcTemplate.getList();
        System.out.println(list);
    }

    @Test
    public void set(){
        FelEngine fel = new FelEngineImpl();
        FelContext ctx = fel.getContext();
        ctx.set("a", "cat");
        Object result = fel.eval("a=='cat1'");
        System.out.println(result);
    }

}