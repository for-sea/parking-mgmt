package com.forsea.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateProfile {
    private Long uid;
    private String username;
    private String oldPassword;
    private String newPassword;
    private String license;
    private String phone;
}
