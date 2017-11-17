package com.mapped.mybatis.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PersonDO {

    private Integer id;

    private String name;

    private Date gmtCreate;
}
