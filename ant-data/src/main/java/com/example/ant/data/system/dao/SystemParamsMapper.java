package com.example.ant.data.system.dao;

import com.example.ant.data.system.entity.SystemParams;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SystemParamsMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemParams record);

    int insertSelective(SystemParams record);

    SystemParams selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemParams record);

    int updateByPrimaryKey(SystemParams record);

    /**
     * 条件查询
     * @param params
     * @return
     */
    List<SystemParams> selectListByParams(Map params);

    /**
     * 根据KEY进行查询
     * @param key
     * @return
     */
    SystemParams selectOneByKEY(String key);
}