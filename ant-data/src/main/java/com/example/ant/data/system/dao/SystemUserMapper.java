package com.example.ant.data.system.dao;

import com.example.ant.data.system.entity.SystemRole;
import com.example.ant.data.system.entity.SystemUser;
import com.example.ant.data.system.model.SystemUserModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SystemUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    SystemUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);

    /**
     * 条件查询
     * @param params
     * @return
     */
    List<SystemUser> selectListByParams(Map params);

    /**
     * 查询创建者，根据ID进行查询
     * @param createUserId
     * @return
     */
    SystemUser selectCreateUser(String createUserId);

    /**
     * 查询用户的角色
     * @param systemUserId
     * @return
     */
    SystemRole selectSystemUserRoleList(String systemUserId);

    /**
     * 根据帐号进行查询，关联角色表
     * @param account
     * @return
     */
    SystemUserModel selectSystemUserCorrelationByAccount(String account);

    /**
     * 根据条件查询，关联创建人对象，角色对象
     * @param params
     * @return
     */
    List<SystemUserModel> selectListCorrelationByParams(Map params);

    /**
     * 删除用户角色
     * @param id
     * @return
     */
    int deleteUserRole(@Param("id") String id);

    /**
     * 添加用户角色
     * @param params
     * @return
     */
    int createUserRole(Map params);
}