package com.mapped.mybatis.dao;

import com.mapped.mybatis.entity.OrganDO;

import java.util.List;
import java.util.Map;

public interface OrganDao {

    int save(OrganDO organDO);

    List<Map<String, Object>> listAll();
}
