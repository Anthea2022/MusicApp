package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collect {
    private Integer id;

    private Integer userId;

    private Byte type;

    private Integer songId;

    private Integer songListId;

    private Date createTime;
}
