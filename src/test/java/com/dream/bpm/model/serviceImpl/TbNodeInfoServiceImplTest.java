package com.dream.bpm.model.serviceImpl;

import com.dream.bpm.model.entity.TbNodeInfo;
import com.dream.util.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by Dream
 * 2017/12/27.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TbNodeInfoServiceImplTest {

    @Autowired
    TbNodeInfoServiceImpl tbNodeInfoService;


    @Test
    public void findAllByProcDefIdAndAndTaskDefKey() throws Exception {
        List<TbNodeInfo> tbNodeInfo = tbNodeInfoService.findAllByProcDefIdAndAndTaskDefKey("BatchOnline:1:25008","bo_Adev_principal");
        System.out.println(tbNodeInfo);
    }

    @Test
    public void save() throws Exception {
        TbNodeInfo tbNodeInfo = new TbNodeInfo();
        tbNodeInfo.setOperationId(KeyUtil.getUniqueKey());
        tbNodeInfo.setOperationName("提交至甲方负责人审核");
        tbNodeInfo.setProcDefId("BatchOnline:1:25008");
        tbNodeInfo.setTaskDefKey("bo_Adev_principal");
        tbNodeInfo.setUserType("2");
        tbNodeInfo.setOperationSql("");
        tbNodeInfoService.save(tbNodeInfo);
    }

    @Test
    public void getUserBySQL(){
        tbNodeInfoService.getUserBySQL("","");
    }

}