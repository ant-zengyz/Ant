package com.example.ant.data.system.dao;

import com.example.ant.data.system.entity.SystemRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SystemRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemRole record);

    int insertSelective(SystemRole record);

    SystemRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemRole record);

    int updateByPrimaryKey(SystemRole record);

    /**
     * 参数查询
     * @param params
     * @return
     */
    List<SystemRole> selectListByParams(Map params);

    /**
     * 删除角色权限
     * @param id
     * @return
     */
    int deleteRolePermission(@Param("id") String id);

    /**
     * 添加角色权限
     * @param params
     * @return
     */
    int createRolePermission(Map params);
}