package com.example.domain;

import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author HP
 */
public class Consumer {
    public Integer id;

    public String userName;

    public String password;

    public Byte sex;

    public String phoneNum;

    public String email;

    public Date birth;

    public String introduction;

    public String location;

    public String avatar;

    public Date createTime;

    public Date updateTime;

    public Consumer() {
    }

    public Consumer(Integer id, String userName, String password, Byte sex, String phoneNum, String email, Date birth,
                    String introduction, String location, String avatar, Date createTime, Date updateTime) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.sex = sex;
        this.phoneNum = phoneNum;
        this.email = email;
        this.birth = birth;
        this.introduction = introduction;
        this.location = location;
        this.avatar = avatar;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
