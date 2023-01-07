package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author HP
 */
@Configuration
public class FileConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/img/SingerPic/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"SingerPic"+System.getProperty("file.separator")
        );//歌手图片的定位
        registry.addResourceHandler("/img/SongPic/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                        +System.getProperty("file.separator")+"SongPic"+System.getProperty("file.separator")
        );//歌曲图片的定位
        registry.addResourceHandler("/img/SongListPic/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                        +System.getProperty("file.separator")+"SongListPic"+System.getProperty("file.separator")
        );//歌单图片的定位
        registry.addResourceHandler("/img/ConListPic/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                        +System.getProperty("file.separator")+"ConsumerPic"+System.getProperty("file.separator")
        );//歌单图片的定位
        registry.addResourceHandler("/song/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")
                        +System.getProperty("file.separator")+"song"+System.getProperty("file.separator")
        );//歌曲地址
    }

}
