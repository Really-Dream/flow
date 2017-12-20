package com.dream.basic.manage.service;

import com.dream.basic.manage.entity.TbMenu;

import java.util.List;

/**
 * Created by Dream
 * 2017/12/20.
 */
public interface TbMenuService {

    void save(TbMenu tbMenu);
    void delete(String id);
    List<TbMenu> findAllByMenuLevel(String level);
}
