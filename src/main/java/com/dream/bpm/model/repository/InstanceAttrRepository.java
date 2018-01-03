package com.dream.bpm.model.repository;

import com.dream.bpm.model.entity.InstanceAttr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Dream
 * 2018/1/3.
 */
public interface InstanceAttrRepository extends JpaRepository<InstanceAttr,String> {

    List<InstanceAttr> findAllByBusinessKey(String businessKey);
}
