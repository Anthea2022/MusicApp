package com.example.service.impl;

import com.example.dao.ConsumerMapper;
import com.example.domain.Consumer;
import com.example.service.ConsumerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerMapper consumerMapper;

    public ConsumerServiceImpl(ConsumerMapper consumerMapper) {
        this.consumerMapper = consumerMapper;
    }

    @Override
    public boolean insert(Consumer consumer) {
        return consumerMapper.insert(consumer)>0;
    }

    @Override
    public boolean update(Consumer consumer) {
        return consumerMapper.update(consumer)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return consumerMapper.delete(id)>0;
    }

    @Override
    public Consumer selectByPrimaryKey(Integer id) {
        return consumerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Consumer> selectAll() {
        return consumerMapper.selectAll();
    }

    @Override
    public Consumer selectByName(String name) {
        return consumerMapper.selectByName(name);
    }

    @Override
    public boolean verifyPassword(String name, String password) {
        return consumerMapper.verifyPassword(name,password)>0;
    }

    @Override
    public List<Consumer> login(String username) {
        return consumerMapper.login(username);
    }
}
