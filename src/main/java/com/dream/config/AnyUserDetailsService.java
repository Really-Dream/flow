package com.dream.config;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dream
 * 2018/2/1.
 */
@Service
public class AnyUserDetailsService implements UserDetailsService{

    @Autowired
    IdentityService identityService;

    /**
     * 验证用户信息
     * @param username 用户ID
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = identityService.createUserQuery().userId(username).singleResult();
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<Group> list = identityService.createGroupQuery().groupMember(username).list();
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = createAuthorities(list);
        return new org.springframework.security.core.userdetails.User(user.getId(),user.getPassword(),simpleGrantedAuthorities);
    }

    /**
     * 添加用户组
     * @param list 用户组
     */
    private List<SimpleGrantedAuthority> createAuthorities(List<Group> list){
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for(Group group : list){
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(group.getType()));
        }
        return simpleGrantedAuthorities;
    }

}
