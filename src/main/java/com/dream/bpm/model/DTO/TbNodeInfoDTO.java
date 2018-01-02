package com.dream.bpm.model.DTO;

import com.dream.bpm.model.entity.TbNodeInfo;
import com.dream.bpm.model.entity.User;
import lombok.Data;

import java.util.List;

/**
 * Created by Dream
 * 2018/1/2.
 */
@Data
public class TbNodeInfoDTO {

    private TbNodeInfo tbNodeInfo;

    private List<User> userList;
}
