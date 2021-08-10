package com.forsea.service.impl;

import com.forsea.dao.UserDAO;
import com.forsea.enums.ResultCode;
import com.forsea.exception.UserExistException;
import com.forsea.exception.UserNotExistException;
import com.forsea.exception.WrongPasswordException;
import com.forsea.pojo.dto.AdminUpdateDTO;
import com.forsea.pojo.dto.LoginDTO;
import com.forsea.pojo.entity.User;
import com.forsea.pojo.vo.UserVO;
import com.forsea.pojo.dto.UserUpdateDTO;
import com.forsea.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;


    /**
     * 通过用户id查询用户
     * 用于用户个人中心
     * @param uid
     * @return 用户常用信息
     */
    @Override
    public UserVO getUserByUid(Long uid) {
        User user = userDAO.selectUserByUid(uid);
        UserVO userVO = partOfUser(user);
        return userVO;
    }

    /**
     * 通过用户名和密码查询用户，用于登录
     * @param loginDTO
     * @return 用户常用信息
     */
    @Override
    public UserVO getUserByUsername(LoginDTO loginDTO) throws Exception {
        User existUser = getUsername(loginDTO.getUsername());
        // 用户不存在
        if (existUser == null){
            throw new UserNotExistException(ResultCode.USER_NOT_EXIST.getCode(), ResultCode.USER_NOT_EXIST.getMessage());
        }
        // 密码错误
        if (!loginDTO.getPassword().equals(existUser.getPassword())){
            throw new WrongPasswordException(ResultCode.WRONG_USERNAME_OR_PASSWORD.getCode(),
                    ResultCode.WRONG_USERNAME_OR_PASSWORD.getMessage());
        }
        User user = userDAO.selectUserLogin(loginDTO);
        UserVO userVO = partOfUser(user);
        return userVO;
    }

    /**
     * 通过用户名查询用户
     * @param username
     * @return User对象
     */
    @Override
    public User getUsername(String username) {
        User user = userDAO.selectUserByUsername(username);
        return user;
    }

    /**
     * 查询用户列表
     * @return 用户列表
     */
    @Override
    public List<User> listUsers() {
        List<User> users = userDAO.selectUsers();
        return users;
    }

    /**
     * 保存用户，用于注册
     * @param user
     * @return 用户常用信息
     */
    @Override
    public UserVO saveUser(User user) throws UserExistException {
        User existUser = getUsername(user.getUsername());
        if (existUser != null){
            throw new UserExistException(ResultCode.USER_EXISTED.getCode(), ResultCode.USER_EXISTED.getMessage());
        }
        userDAO.insertUser(user);
        User savedUser = userDAO.selectUserByUid(user.getUid());
        UserVO userVO = partOfUser(savedUser);
        return userVO;
    }

    /**
     * 管理员保存用户
     * @param user
     * @return User实例
     */
    @Override
    public User saveUserAdmin(User user) {
        userDAO.insertUser(user);
        User savedUser = userDAO.selectUserByUid(user.getUid());
        return savedUser;
    }

    /**
     * 删除用户
     * @param uid
     * @return 被删除用户的id
     */
    @Override
    public Long removeUser(Long uid) {
        userDAO.deleteUser(uid);
        return uid;
    }

    /**
     * 用户更新个人信息
     * @param userUpdateDTO
     * @return 更新后的用户常用信息
     */
    @Override
    public UserVO updateUser(UserUpdateDTO userUpdateDTO) {
        userDAO.updateUser(userUpdateDTO);
        User updatedUser = userDAO.selectUserByUid(userUpdateDTO.getUid());
        UserVO userVO = partOfUser(updatedUser);
        return userVO;
    }

    /**
     * 管理员更新个人信息
     * @param adminUpdateDTO
     * @return 更新后的User对象
     */
    @Override
    public User updateUserAdmin(AdminUpdateDTO adminUpdateDTO) {
        userDAO.updateUserAdmin(adminUpdateDTO);
        User updatedUser = userDAO.selectUserByUid(adminUpdateDTO.getUid());
        return updatedUser;
    }

    public UserVO partOfUser(User user){
        UserVO userVO = new UserVO();
        userVO.setUid(user.getUid());
        userVO.setUsername(user.getUsername());
        userVO.setLicense(user.getLicense());
        userVO.setPhone(user.getPhone());
        userVO.setStatus(user.getStatus());
        return userVO;
    }
}
