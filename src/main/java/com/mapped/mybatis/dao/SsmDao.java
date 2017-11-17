package com.mapped.mybatis.dao;

import com.mapped.mybatis.entity.SsmDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SsmDao {

    List<SsmDO> listAll();

    @Select("SELECT * FROM ssm WHERE id = #{id}")
    @ResultMap("entityMap")
    SsmDO getById(@Param("id") Integer id);

    @Insert("INSERT INTO ssm (`ssm`) VALUES (#{ssm, jdbcType=VARCHAR})")
    int save(SsmDO ssmDO);
}
