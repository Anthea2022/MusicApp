package com.example.domain;

import java.io.Serializable;

/**
 * @author HP
 */
/*实现前后端的连接，序列化*/

public class Admin implements Serializable {
    private Integer id;

    /*姓名*/

    private String name;

    /*密码*/

    private String password;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Admin() {
    }
}
