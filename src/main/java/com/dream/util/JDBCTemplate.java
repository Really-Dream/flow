package com.dream.util;

import com.dream.bpm.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dream
 * 2017/12/27.
 */
@Service
public class JDBCTemplate {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> getList(){
        String sql = "select node_name FROM tb_node WHERE id='1513825615854135647'";
        return jdbcTemplate.query(sql, new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs,int rowNum) throws SQLException{
                return rs.getString("node_name");
            }
        });
    }

    public List<User> getUser(String SQL){
        return jdbcTemplate.query(SQL, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs,int rowNum) throws SQLException{
                User user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setUserName(rs.getString("user_name"));
                return user;
            }
        });
    }
}
