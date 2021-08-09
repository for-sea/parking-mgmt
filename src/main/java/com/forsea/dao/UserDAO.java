package com.forsea.dao;

import com.forsea.pojo.LoginProfile;
import com.forsea.pojo.User;
import com.forsea.pojo.UserUpdateProfile;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDAO {
    /**
     * 登录
     * @param loginProfile
     * @return
     */
    @Select("<script>" +
            "select * from user" +
            "<where>" +
                "<if test='username != null'>and username=#{username}</if>" +
                "<if test='password != null'>and password=#{password}</if>" +
            "</where>" +
            "</script>")
    User login(LoginProfile loginProfile);

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    List<User> queryUsers();

    /**
     * 插入用户
     * @param user
     */
    @Insert("insert into user (username, password, license, phone, status)" +
            "values (#{username}, #{password}, #{license}, #{phone}, #{status});")
    @Options(keyColumn = "uid", keyProperty = "uid", useGeneratedKeys = true)
    void insertUser(User user);

    /**
     * 通过id查询用户
     * @param uid
     * @return
     */
    @Select("<script>" +
            "select * from user" +
                "<where>" +
                    "<if test='uid != null'>and uid=#{uid}</if>" +
                "</where>" +
            "</script>")
    User getUserByUid(Long uid);

    @Delete("delete from user where uid=#{uid}")
    void deleteUser(Long uid);

    @Update("<script>update user set " +
            "<if test='username != null'>username=#{username}, </if>" +
            "<if test='license != null'>license=#{license}, </if>" +
            "<if test='phone != null'>phone=#{phone}, </if>" +
            "<if test='role != null'>role=#{role}, </if>" +
            "<if test='perms != null'>perms=#{perms}, </if>" +
            "<if test='status != null'>status=#{status}</if>" +
            "where uid=#{uid};</script>")
    void updateUserAdmin(User user);

    @Update("<script>update user set " +
            "<if test='username != null'>username=#{username},</if>" +
            "<if test='newPassword != null'>password=#{newPassword},</if>" +
            "<if test='license != null'>license=#{license},</if>" +
            "<if test='phone != null'>phone=#{phone}</if>" +
            "where uid=#{uid} and password=#{oldPassword};</script>")
    void updateUser(UserUpdateProfile user);
}
