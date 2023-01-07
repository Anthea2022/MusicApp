package com.example.service;

import com.example.domain.Consumer;

import java.util.List;

/**
 * @author HP
 */
public interface ConsumerService {

    public boolean insert(Consumer consumer);

    public boolean update(Consumer consumer);

    public boolean delete(Integer id);

    public Consumer selectByPrimaryKey(Integer id);

    public List<Consumer> selectAll();

    public Consumer selectByName(String name);

    public boolean verifyPassword(String name, String password);
}
