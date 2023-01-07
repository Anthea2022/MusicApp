package com.example.service.impl;

import com.example.dao.AdminMapper;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HP
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean verifyPassword(String name, String password) {
        return adminMapper.verifyPassword(name, password) > 0;
    }
}
