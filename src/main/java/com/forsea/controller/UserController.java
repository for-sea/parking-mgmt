package com.forsea.controller;

import com.forsea.exception.UserExistException;
import com.forsea.pojo.*;
import com.forsea.pojo.dto.AdminUpdateDTO;
import com.forsea.pojo.dto.LoginDTO;
import com.forsea.pojo.vo.UserVO;
import com.forsea.pojo.dto.UserUpdateDTO;
import com.forsea.pojo.entity.User;
import com.forsea.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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


    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result register(@Validated @RequestBody @ApiParam("用户实例") User user) throws Exception {
        UserVO userVO = userService.saveUser(user);
        return Result.success().message("用户注册成功").data(userVO);
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result login(@Validated @RequestBody @ApiParam("登录DTO") LoginDTO loginDTO) throws Exception {
        UserVO userVO = userService.getUserByUsername(loginDTO);
        return Result.success().message("用户登录成功").data(userVO);
    }

    @PutMapping("/personal")
    @ApiOperation("用户修改个人信息")
    public Result modifyUser(@Validated @RequestBody @ApiParam("用户更新个人信息DTO") UserUpdateDTO userUpdateDTO){
        UserVO userVO = userService.updateUser(userUpdateDTO);
        return Result.success().message("用户修改个人信息成功").data(userVO);
    }

    @GetMapping("/list")
    @ApiOperation("管理员查询所有用户")
    public Result queryUsersAdmin(){
        List<User> users = userService.listUsers();
        return Result.success().message("管理员获取所有用户成功").data(users);
    }

    @PostMapping("/list")
    @ApiOperation("管理员添加用户")
    public Result addUserAdmin(@Validated @RequestBody @ApiParam("用户实例") User user) throws Exception {
        User savedUser = userService.saveUserAdmin(user);
        return Result.success().message("管理员添加用户成功").data(savedUser);
    }

    @DeleteMapping("/list/{uid}")
    @ApiOperation("管理员删除用户")
    public Result deleteUserAdmin(@PathVariable("uid") @ApiParam("要删除的用户id") Long uid){
        Long userId = userService.removeUser(uid);
        return Result.success().message("管理员删除用户成功").data(userId);
    }

    @PutMapping("/list")
    @ApiOperation("管理员修改用户信息")
    public Result modifyUserAdmin(@Validated @RequestBody @ApiParam("管理员更新用户信息DTO") AdminUpdateDTO adminUpdateDTO){
        User updatedUser = userService.updateUserAdmin(adminUpdateDTO);
        return Result.success().message("管理员修改用户信息成功").data(updatedUser);
    }
}
