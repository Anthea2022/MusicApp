package com.example.service;

import org.springframework.stereotype.Repository;


/**
 * @author HP
 */
public interface AdminService {
    /**
     *
     * @param name
     * @param password
     * @return
     */
    public boolean verifyPassword(String name, String password);
}
