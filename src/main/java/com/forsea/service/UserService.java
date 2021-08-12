package com.forsea.service;

import com.forsea.exception.UserExistException;
import com.forsea.exception.WrongPasswordException;
import com.forsea.pojo.dto.AdminUpdateDTO;
import com.forsea.pojo.dto.LoginDTO;
import com.forsea.pojo.entity.User;
import com.forsea.pojo.vo.UserVO;
import com.forsea.pojo.dto.UserUpdateDTO;

import java.util.List;

public interface UserService {
    UserVO getUserByUid(Long uid);

    UserVO getUserByUsername(LoginDTO loginDTO) throws WrongPasswordException, Exception;

    List<User> listUsers();

    User getUsername(String username);

    UserVO saveUser(User user) throws UserExistException;

    User saveUserAdmin(User user) throws UserExistException;

    Long removeUser(Long uid);

    UserVO updateUser(UserUpdateDTO user);

    User updateUserAdmin(AdminUpdateDTO user);
}
