package com.dream.basic.manage.serviceImpl;

import com.dream.basic.manage.dto.TbMenuDTO;
import com.dream.basic.manage.entity.TbMenu;
import com.dream.basic.manage.repository.TbMenuRepository;
import com.dream.basic.manage.service.TbMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dream
 * 2017/12/20.
 */
@Service
public class TbMenuServiceImpl implements TbMenuService{


    private TbMenuRepository repository;

    @Autowired
    public TbMenuServiceImpl(TbMenuRepository tbMenuRepository){
        this.repository = tbMenuRepository;
    }

    @Override
    public void save(TbMenu tbMenu) {
        repository.save(tbMenu);
    }

    @Override
    @Transactional
    public void delete(String id) {
        repository.deleteById(id);
        repository.deleteAllByParentId(id);
    }

    @Override
    public List<TbMenu> findAllByMenuLevel(String level) {
        return repository.findAllByMenuLevelOrderByMenuOrderAsc(level);
    }

    public List<TbMenuDTO> getMenu(){
        List<TbMenu> tbMenus_1 = findAllByMenuLevel("1");
        List<TbMenu> tbMenus_2 = findAllByMenuLevel("2");
        List<TbMenuDTO> list = new ArrayList<>();
        for(TbMenu tbMenu : tbMenus_1){
            TbMenuDTO tbMenuDTO = new TbMenuDTO();
            BeanUtils.copyProperties(tbMenu,tbMenuDTO);
            for(TbMenu tbMenu1 : tbMenus_2){
                TbMenuDTO tbMenuDTO1 = new TbMenuDTO();
                BeanUtils.copyProperties(tbMenu1,tbMenuDTO1);
                if(tbMenu1.getParentId().equals(tbMenu.getId())){
                    tbMenuDTO.add2List(tbMenuDTO1);
                }
            }
            list.add(tbMenuDTO);
        }
        return list;
    }
}
