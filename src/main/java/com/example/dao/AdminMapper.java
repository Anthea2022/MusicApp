package com.example.dao;

import org.springframework.stereotype.Repository;

/**
 * @author HP
 */

/*便于service的调用*/

@Repository
public interface AdminMapper {
    public int verifyPassword(String name,String password);
}
