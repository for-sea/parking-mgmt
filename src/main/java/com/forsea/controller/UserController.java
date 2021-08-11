package com.forsea.controller;

import com.forsea.pojo.*;
import com.forsea.pojo.dto.AdminUpdateDTO;
import com.forsea.pojo.dto.LoginDTO;
import com.forsea.pojo.vo.UserVO;
import com.forsea.pojo.dto.UserUpdateDTO;
import com.forsea.pojo.entity.User;
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
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result register(@Validated @RequestBody User user) throws Exception {
        UserVO userVO = userService.saveUser(user);
        return Result.success().message("用户注册成功").data(userVO);
    }

    /**
     * 用户登录
     * @param loginDTO
     * @return 用户常用信息
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDTO loginDTO) throws Exception {
        UserVO userVO = userService.getUserByUsername(loginDTO);
        return Result.success().message("用户登录成功").data(userVO);
    }

    /**
     * 用户修改个人信息
     * @param userUpdateDTO
     * @return
     */
    @PutMapping("/personal")
    public Result modifyUser(@Validated @RequestBody UserUpdateDTO userUpdateDTO){
        UserVO userVO = userService.updateUser(userUpdateDTO);
        return Result.success().message("用户修改个人信息成功").data(userVO);
    }

    /**
     * 管理员查询所有用户
     * @return
     */
    @GetMapping("/list")
    public Result queryUsersAdmin(){
        List<User> users = userService.listUsers();
        return Result.success().message("管理员获取所有用户成功").data(users);
    }

    /**
     * 管理员添加用户
     * @param user
     * @return
     */
    @PostMapping("/list")
    public Result addUserAdmin(@Validated @RequestBody User user){
        User savedUser = userService.saveUserAdmin(user);
        return Result.success().message("管理员添加用户成功").data(savedUser);
    }

    /**
     * 管理员删除用户
     * @param uid
     * @return
     */
    @DeleteMapping("/list")
    public Result deleteUserAdmin(Long uid){
        Long userId = userService.removeUser(uid);
        return Result.success().message("管理员删除用户成功").data(userId);
    }

    /**
     * 管理员修改用户信息
     * @param adminUpdateDTO
     * @return
     */
    @PutMapping("/list")
    public Result modifyUserAdmin(@Validated @RequestBody AdminUpdateDTO adminUpdateDTO){
        User updatedUser = userService.updateUserAdmin(adminUpdateDTO);
        return Result.success().message("管理员修改用户信息成功").data(updatedUser);
    }
}
