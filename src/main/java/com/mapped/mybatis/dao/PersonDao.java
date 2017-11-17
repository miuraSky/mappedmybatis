package com.mapped.mybatis.dao;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

public interface PersonDao {

    HashMap<String, Object> selectPerson(@Param("id") Integer id);
}
