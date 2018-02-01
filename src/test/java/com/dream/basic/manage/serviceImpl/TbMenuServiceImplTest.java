package com.dream.basic.manage.serviceImpl;

import com.dream.basic.manage.dto.TbMenuDTO;
import com.dream.basic.manage.entity.TbMenu;
import com.dream.util.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Dream
 * 2017/12/20.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TbMenuServiceImplTest {

    @Autowired
    TbMenuServiceImpl tbMenuService;

    @Test
    public void saveTest(){
        TbMenu tbMenu = new TbMenu();
        tbMenu.setId(KeyUtil.getUniqueKey());
        tbMenu.setMenuAction("#/flow/myDone");
        tbMenu.setMenuName("我的已办");
        tbMenu.setMenuLevel("2");
        tbMenu.setParentId("1517466125558556538");
        tbMenu.setMenuOrder("2");
        tbMenuService.save(tbMenu);
    }

    @Test
    public void deleteTest(){
        tbMenuService.delete("s");
    }

    @Test
    public void getMenu(){
        List<TbMenuDTO> list = tbMenuService.getMenu();
        System.out.println(list.size());
    }
}