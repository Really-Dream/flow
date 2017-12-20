package com.dream.basic.manage.repository;

import com.dream.basic.manage.entity.TbMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Dream
 * 2017/12/20.
 */
public interface TbMenuRepository extends JpaRepository<TbMenu,String>{

    List<TbMenu> findAllByMenuLevelOrderByMenuOrderDesc(String level);
    void deleteById(String id);
    void deleteAllByParentId(String parentId);
}
