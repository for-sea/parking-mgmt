package com.forsea.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.forsea.pojo.*;
import com.forsea.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param loginProfile
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody LoginProfile loginProfile){
        UserProfile userProfile = userService.login(loginProfile);
        log.info("用户{}登录成功: =====> {}", userProfile.getUid(), userProfile.toString());
        return Result.success().message("用户登录成功").data(userProfile);
    }

    /**
     * 用户注册
     * @param user
     * @return 用户常用属性：id、username、license
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result register(@Validated @RequestBody User user) throws Exception{
        UserProfile userProfile = userService.insertUser(user);
        log.info("用户{}注册成功: ======> {}", userProfile.getUid(), userProfile.toString());
        return Result.success().message("创建用户成功").data(userProfile);
    }

    /**
     * 用户更新个人信息
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/personal", method = RequestMethod.PUT)
    public Result updateUser(@Validated @RequestBody UserUpdateProfile user) throws Exception{
        UserProfile userProfile = userService.updateUser(user);
        return Result.success().message("用户更新个人信息成功").data(userProfile);
    }

    /**
     * 管理员查询所有用户
     * @return 用户列表
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result queryUsers() throws Exception{
        List<User> users = userService.queryUsers();
        log.info("用户列表: ======> {}", users);
        return Result.success().message("成功获取用户列表").data(users);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result insertAdmin(@Validated @RequestBody User user) throws Exception{
        User admin = userService.insertAdmin(user);
        log.info("添加管理成功: ======> {}", admin.toString());
        return Result.success().message("添加管理员成功").data(admin);
    }

    /**
     * 管理员删除用户
     * @param uid
     * @return 删除用户的id
     * @throws Exception
     */
    @RequestMapping(value = "/list/{uid}", method = RequestMethod.DELETE)
    public Result deleteUser(@PathVariable Long uid) throws Exception{
        Long deletedUid = userService.deleteUser(uid);
        log.info("删除用户{}成功", deletedUid);
        return Result.success().message("删除用户成功").data(JSONUtil.createObj().putOpt("uid", deletedUid));
    }

    /**
     * 管理员更新用户
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.PUT)
    public Result updateUserAdmin(@Validated @RequestBody User user) throws Exception{
        User updatedUser = userService.updateUserAdmin(user);
        log.info("管理员更新用户{}成功: ======> {}", updatedUser.getUid(), updatedUser.toString());
        return Result.success().message("管理员更新用户信息成功").data(updatedUser);
    }
}
