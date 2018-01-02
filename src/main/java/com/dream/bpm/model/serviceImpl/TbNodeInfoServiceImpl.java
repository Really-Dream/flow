package com.dream.bpm.model.serviceImpl;

import com.dream.bpm.model.DTO.TbNodeInfoDTO;
import com.dream.bpm.model.entity.TbNodeInfo;
import com.dream.bpm.model.entity.User;
import com.dream.bpm.model.repository.TbNodeInfoRepository;
import com.dream.bpm.model.service.TbNodeInfoService;
import com.dream.util.JDBCTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created By Dream
 * 2017/12/24 2:03
 */
@Service
public class TbNodeInfoServiceImpl implements TbNodeInfoService{

    @Autowired
    TbNodeInfoRepository repository;

    @Autowired
    JDBCTemplate jdbcTemplate;

    @Override
    public List<TbNodeInfo> findAllByProcDefIdAndAndTaskDefKey(String procDefId,String taskDefKey) {
        return repository.findAllByProcDefIdAndAndTaskDefKey(procDefId,taskDefKey);
    }

    @Override
    public void save(TbNodeInfo tbNodeInfo) {
        repository.save(tbNodeInfo);
    }

    @Override
    public List<TbNodeInfoDTO> findTbNodeInfoDTOList(String procDefId, String taskDefKey) {
        List<TbNodeInfo> tbNodeInfos = this.findAllByProcDefIdAndAndTaskDefKey(procDefId,taskDefKey);
        List<TbNodeInfoDTO> tbNodeInfoDTOS = new ArrayList<>();

        for(TbNodeInfo tbNodeInfo : tbNodeInfos){
            TbNodeInfoDTO tbNodeInfoDTO = new TbNodeInfoDTO();

            if(!"0".equals(tbNodeInfo.getUserType())){
//                根据表达式获取下一步处理人信息
            }else{
                tbNodeInfoDTO.setUserList(null);
            }
            tbNodeInfoDTOS.add(tbNodeInfoDTO);
        }
        return tbNodeInfoDTOS;
    }

    public List<User> getUserBySQL(String SQL){
        SQL = "select * from tb_node where id={id}";
        List<String> ls=new ArrayList<>();
        Pattern pattern = Pattern.compile("(?<=\\{)(.+?)(?=\\})");
        Matcher matcher = pattern.matcher(SQL);
        while(matcher.find()){
            ls.add(matcher.group());
        }
        for (String string : ls) {
            SQL = SQL.replace("{"+string+"}","'1231231'");
        }
        return jdbcTemplate.getUser(SQL);
    }

    public Map<String,Object> getTaskMap(String businessKey){
        return null;
    }

}
