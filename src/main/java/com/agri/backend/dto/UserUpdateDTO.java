package com.agri.backend.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息更新 DTO
 */
@Data
public class UserUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nickname;
    private String avatar;
    private String phone;
    private String email;
    private String region;
    private String bio;
}
