package com.example.ant.data.system.dao;

import com.example.ant.data.system.entity.SystemPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SystemPermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemPermission record);

    int insertSelective(SystemPermission record);

    SystemPermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemPermission record);

    int updateByPrimaryKey(SystemPermission record);

    /**
     * 根据角色ID查询该角色的权限
     * @param systemRoleId
     * @return
     */
    List<SystemPermission> selectListBySystemRoleId(@Param("systemRoleId") String systemRoleId);

    /**
     * 参数查询
     * @param params
     * @return
     */
    List<SystemPermission> selectListByParams(Map params);


}