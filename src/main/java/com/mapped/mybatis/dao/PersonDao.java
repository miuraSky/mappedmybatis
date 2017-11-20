package com.mapped.mybatis.dao;

import com.mapped.mybatis.entity.PersonDO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

public interface PersonDao {

    HashMap<String, Object> selectPerson(@Param("id") Integer id);

    int save(PersonDO personDO);

    int saveNone(PersonDO personDO);
}
