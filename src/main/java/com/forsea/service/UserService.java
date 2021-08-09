package com.forsea.service;

import com.forsea.pojo.LoginProfile;
import com.forsea.pojo.User;
import com.forsea.pojo.UserProfile;
import com.forsea.pojo.UserUpdateProfile;

import java.util.List;

public interface UserService {
    List<User> queryUsers();

    UserProfile insertUser(User user);

    User insertAdmin(User user);

    UserProfile login(LoginProfile loginProfile);

    Long deleteUser(Long uid);

    User updateUserAdmin(User user);

    UserProfile updateUser(UserUpdateProfile user);
}
