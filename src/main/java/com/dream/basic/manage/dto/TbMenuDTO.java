package com.dream.basic.manage.dto;

import com.dream.util.CheckNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dream
 * 2017/12/20.
 */
@Data
public class TbMenuDTO {

    private String id;
    private String menuName;
    private String menuAction;
    private String parentId;
    private String menuLevel;
    private String menuOrder;

    private List<TbMenuDTO> list;

    public void add2List(TbMenuDTO tbMenuDTO){
        if(!CheckNull.checkList(this.list)){
            this.list = new ArrayList<>();
        }
        this.list.add(tbMenuDTO);
    }

}
