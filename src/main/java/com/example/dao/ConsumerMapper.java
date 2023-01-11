package com.example.dao;

import com.example.domain.Consumer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author HP
 */
@Repository
public interface ConsumerMapper {
    public int insert(Consumer consumer);

    public int update(Consumer consumer);

    public int delete(Integer id);

    public Consumer selectByPrimaryKey(Integer id);

    public List<Consumer> selectAll();

    public Consumer selectByName(String name);

    public int verifyPassword(String username, String password);

    public List<Consumer> login(String username);
}
