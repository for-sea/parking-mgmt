package com.forsea.service.impl;

import com.forsea.dao.UserDAO;
import com.forsea.pojo.LoginProfile;
import com.forsea.pojo.User;
import com.forsea.pojo.UserProfile;
import com.forsea.pojo.UserUpdateProfile;
import com.forsea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserProfile login(LoginProfile loginProfile) {
        User user = userDAO.login(loginProfile);
        UserProfile userProfile = new UserProfile();
        userProfile.setUid(user.getUid());
        userProfile.setUsername(user.getUsername());
        userProfile.setLicense(user.getLicense());
        userProfile.setPhone(user.getPhone());
        return userProfile;
    }

    @Override
    public Long deleteUser(Long uid) {
        userDAO.deleteUser(uid);
        return uid;
    }

    @Override
    public User updateUserAdmin(User user) {
        userDAO.updateUserAdmin(user);
        User updatedUser = userDAO.getUserByUid(user.getUid());
        return updatedUser;
    }

    @Override
    public UserProfile updateUser(UserUpdateProfile user) {
        userDAO.updateUser(user);
        User updatedUser = userDAO.getUserByUid(user.getUid());
        UserProfile userProfile = new UserProfile();
        userProfile.setUid(user.getUid());
        userProfile.setUsername(updatedUser.getUsername());
        userProfile.setLicense(updatedUser.getLicense());
        userProfile.setPhone(updatedUser.getPhone());
        return userProfile;
    }

    @Override
    public List<User> queryUsers() {
        return userDAO.queryUsers();
    }

    @Override
    public UserProfile insertUser(User user) {
        userDAO.insertUser(user);
        UserProfile userProfile = new UserProfile();
        userProfile.setUid(user.getUid());
        userProfile.setUsername(user.getUsername());
        userProfile.setLicense(user.getLicense());
        userProfile.setPhone(user.getPhone());
        return userProfile;
    }

    @Override
    public User insertAdmin(User user) {
        userDAO.insertUser(user);
        User admin = new User();
        admin.setUid(user.getUid());
        admin.setUsername(user.getUsername());
        admin.setPassword(user.getPassword());
        admin.setLicense(user.getLicense());
        admin.setPhone(user.getPhone());
        admin.setRole(user.getRole());
        admin.setPerms(user.getPerms());
        admin.setStatus(user.getStatus());
        admin.setCreated(user.getCreated());
        return admin;
    }


}
