package com.example.domain;

import java.util.Date;

/**
 * @author HP
 */
public class Song {
    private Integer id;
    private String name;
    private Integer singerId;
    private String introduction;
    private Date createTime;
    private Date updateTime;
    private String pic;
    private String lyric;
    private String url;

    public Song() {
    }

    public Song(Integer id, String name, Integer singerId, String introduction, Date createTime, Date updateTime, String pic, String lyric, String url) {
        this.id = id;
        this.name = name;
        this.singerId = singerId;
        this.introduction = introduction;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.pic = pic;
        this.lyric = lyric;
        this.url = url;
    }

    public Integer getId() {
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

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
