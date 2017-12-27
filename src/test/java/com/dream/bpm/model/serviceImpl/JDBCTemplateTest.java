package com.dream.bpm.model.serviceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

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

}