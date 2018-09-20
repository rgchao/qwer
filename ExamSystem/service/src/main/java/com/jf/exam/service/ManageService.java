package com.jf.exam.service;


import com.jf.exam.mapper.ManagerMapper;
import com.jf.exam.pojo.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageService {
    @Autowired
    ManagerMapper managerMapper;

    public Manager manageLogin(String name){
        return managerMapper.getManageByName(name);
    }
    public int updatePassword(Manager manager){
        return managerMapper.updateByPrimaryKey(manager);
    }
}
