package com.mapped.mybatis.dao;

import com.mapped.mybatis.entity.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserDao {

    Map<String, Object> selectWithMap(@Param("id") Long id);

    UserDO selectWithPOJO(@Param("id") Long id);

    int insert(@Param("user") UserDO userDO);

    long getLastInsertId();
}
