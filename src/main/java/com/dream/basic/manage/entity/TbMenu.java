package com.dream.basic.manage.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Dream
 * 2017/12/20.
 */

@Entity
@Data
public class TbMenu {

    @Id
    private String id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单链接
     */
    private String menuAction;

    /**
     * 父节点ID
     */
    private String parentId;

    /**
     * 菜单级别
     */
    private String menuLevel;

    /**
     * 菜单排序
     */
    private String menuOrder;
}
