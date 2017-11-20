package com.mapped.mybatis.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserDO {

    private Long id;

    private String name;

    private String password;

    private Date gmtCreate;
}
