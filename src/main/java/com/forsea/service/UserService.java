package com.forsea.service;

import com.forsea.pojo.dto.AdminUpdateDTO;
import com.forsea.pojo.dto.LoginDTO;
import com.forsea.pojo.entity.User;
import com.forsea.pojo.vo.UserVO;
import com.forsea.pojo.dto.UserUpdateDTO;

import java.util.List;

public interface UserService {
    UserVO getUserByUid(Long uid);

    UserVO getUserByUsername(LoginDTO loginDTO) throws Exception;

    List<User> listUsers();

    User getUsername(String username);

    UserVO saveUser(User user) throws Exception;

    User saveUserAdmin(User user) throws Exception;

    Long removeUser(Long uid);

    UserVO updateUser(UserUpdateDTO user) throws  Exception;

    User updateUserAdmin(AdminUpdateDTO user) throws Exception;
}
