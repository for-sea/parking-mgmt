package com.forsea.dao;

import com.forsea.pojo.dto.AdminUpdateDTO;
import com.forsea.pojo.dto.LoginDTO;
import com.forsea.pojo.entity.User;
import com.forsea.pojo.dto.UserUpdateDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDAO {
    /**
     * 通过用户id查询用户
     * @param uid
     * @return User对象
     */
    @Select("<script>" +
            "select * from user" +
                "<where>" +
                    "<if test='uid != null'>and uid=#{uid}</if>" +
                "</where>" +
            "</script>")
    @Results(id = "User", value = {
            @Result(id = true, column = "uid", property = "uid"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "license", property = "license"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "role", property = "role"),
            @Result(column = "perms", property = "perms"),
            @Result(column = "status", property = "status"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
    })
    User selectUserByUid(Long uid);

    /**
     * 通过用户名和密码查询用户
     * @param loginDTO
     * @return 登录的User对象
     */
    @Select("<script>" +
            "select * from user" +
            "<where>" +
                "<if test='username != null'>and username=#{username}</if>" +
                "<if test='password != null'>and password=#{password}</if>" +
            "</where>" +
            "</script>")
    @ResultMap(value = "User")
    User selectUserLogin(LoginDTO loginDTO);


    /**
     *
     * 通过用户名查询用户
     * @param username
     * @return
     */
    @Select("select * from user where username=#{username};")
    User selectUserByUsername(String username);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    @Select("select * from user")
    @ResultMap(value = "User")
    List<User> selectUsers();

    /**
     * 插入用户
     * @param user
     */
    @Insert("<script>insert into user (" +
                "<if test='username != null'>username,</if>" +
                "<if test='password != null'>password,</if>" +
                "<if test='license != null'>license,</if>" +
                "<if test='phone != null'>phone,</if>" +
                "<if test='role != null'>role,</if>" +
                "<if test='perms != null'>perms,</if>" +
                "<if test='status != null'>status,</if>" +
                "<if test='createTime != null'>create_time,</if>" +
                "<if test='updateTime != null'>update_time</if>" +
            ") values (" +
                "<if test='username != null'>#{username},</if>" +
                "<if test='password != null'>#{password},</if>" +
                "<if test='license != null'>#{license},</if>" +
                "<if test='phone != null'>#{phone},</if>" +
                "<if test='role != null'>#{role},</if>" +
                "<if test='perms != null'>#{perms},</if>" +
                "<if test='status != null'>#{status},</if>" +
                "<if test='createTime != null'>#{createTime},</if>" +
                "<if test='updateTime != null'>#{updateTime}</if>" +
            ")</script>")
    @Options(keyColumn = "uid", keyProperty = "uid", useGeneratedKeys = true)
    @ResultMap(value = "User")
    void insertUser(User user);

    /**
     * 删除用户
     * @param uid
     */
    @Delete("delete from user where uid=#{uid}")
    void deleteUser(Long uid);

    /**
     * 管理员更新用户信息
     * @param user
     */
    @Update("<script>update user set " +
                "<if test='username != null'>username=#{username},</if>" +
                "<if test='license != null'>license=#{license},</if>" +
                "<if test='phone != null'>phone=#{phone},</if>" +
                "<if test='role != null'>role=#{role},</if>" +
                "<if test='perms != null'>perms=#{perms},</if>" +
                "<if test='status != null'>status=#{status},</if>" +
                "<if test='updateTime != null'>update_time=#{updateTime},</if>" +
            "uid=#{uid} " +
            "where uid=#{uid};</script>")
    @ResultMap(value = "User")
    void updateUserAdmin(AdminUpdateDTO user);

    /**
     * 用户更新用户信息
     * @param user
     */
    @Update("<script>update user set" +
                "<if test='username != null'>username=#{username},</if>" +
                "<if test='newPassword != null'>password=#{newPassword},</if>" +
                "<if test='license != null'>license=#{license},</if>" +
                "<if test='phone != null'>phone=#{phone},</if>" +
                "<if test='updateTime != null'>update_time=#{updateTime},</if>" +
            "uid=#{uid} " +
            "where uid=#{uid} and password=#{password};</script>")
    @ResultMap(value = "User")
    void updateUser(UserUpdateDTO user);
}
