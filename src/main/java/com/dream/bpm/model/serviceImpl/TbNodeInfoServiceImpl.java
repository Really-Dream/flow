package com.dream.bpm.model.serviceImpl;

import com.dream.bpm.model.DTO.TbNodeInfoDTO;
import com.dream.bpm.model.entity.InstanceAttr;
import com.dream.bpm.model.entity.TbNodeInfo;
import com.dream.bpm.model.entity.User;
import com.dream.bpm.model.repository.TbNodeInfoRepository;
import com.dream.bpm.model.service.TbNodeInfoService;
import com.dream.util.JDBCTemplate;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Autowired
    InstanceAttrServiceImpl instanceAttrService;

    @Override
    public List<TbNodeInfo> findAllByProcDefIdAndAndTaskDefKey(String procDefId,String taskDefKey) {
        return repository.findAllByProcDefIdAndAndTaskDefKey(procDefId,taskDefKey);
    }

    @Override
    public void save(TbNodeInfo tbNodeInfo) {
        repository.save(tbNodeInfo);
    }

    @Override
    public void delete(String operationId){
        repository.delete(operationId);
    }

    @Override
    public TbNodeInfo findTbNodeInfoByOperationId(String operationId){
        return repository.findTbNodeInfoByOperationId(operationId);
    }

    @Override
    public void update(TbNodeInfo tbNodeInfo){
        repository.save(tbNodeInfo);
    }

    @Override
    public List<TbNodeInfoDTO> findTbNodeInfoDTOList(String procDefId, String taskDefKey,String businessKey) {
        List<TbNodeInfo> tbNodeInfos = this.findAllByProcDefIdAndAndTaskDefKey(procDefId,taskDefKey);
        List<TbNodeInfoDTO> tbNodeInfoDTOS = new ArrayList<>();

        for(TbNodeInfo tbNodeInfo : tbNodeInfos){
            TbNodeInfoDTO tbNodeInfoDTO = new TbNodeInfoDTO();

            if(!"0".equals(tbNodeInfo.getUserType())){
//                根据表达式获取下一步处理人信息
                tbNodeInfoDTO.setUserList(this.getUserBySQL(tbNodeInfo.getOperationSql(),businessKey));
            }else{
                tbNodeInfoDTO.setUserList(null);
            }
            tbNodeInfoDTOS.add(tbNodeInfoDTO);
        }
        return tbNodeInfoDTOS;
    }

    //下一步处理人为SQL，或者单纯一个表达式
    public List<User> getUserBySQL(String SQL,String businessKey){
        List<String> ls=new ArrayList<>();
        Pattern pattern = Pattern.compile("(?<=\\{)(.+?)(?=\\})");
        Matcher matcher = pattern.matcher(SQL);
        while(matcher.find()){
            ls.add(matcher.group());
        }
        for (String string : ls) {
            SQL = SQL.replace("{"+string+"}","'"+this.getTaskMap(businessKey).get(string)+"'");
        }
        return jdbcTemplate.getUser(SQL);
    }

    /**
     * 获取Attr表中的数据
     */
    public Map<String,String> getTaskMap(String businessKey){
        Map<String,String> map = new HashMap<>();
        List<InstanceAttr> instanceAttrs = instanceAttrService.findAllByBusinessKey(businessKey);
        for(InstanceAttr instanceAttr : instanceAttrs){
            map.put(instanceAttr.getAttrCode(),instanceAttr.getAttrValue());
        }
        return map;
    }

}
