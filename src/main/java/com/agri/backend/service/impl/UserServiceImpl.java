package com.agri.backend.service.impl;

import com.agri.backend.common.BusinessException;
import com.agri.backend.dto.LoginDTO;
import com.agri.backend.dto.RegisterDTO;
import com.agri.backend.dto.UserUpdateDTO;
import com.agri.backend.entity.User;
import com.agri.backend.mapper.UserMapper;
import com.agri.backend.service.UserService;
import com.agri.backend.utils.JwtUtil;
import com.agri.backend.vo.LoginVO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 用户 Service 实现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginVO register(RegisterDTO dto) {
        // 参数校验
        if (dto.getUsername() == null || dto.getUsername().trim().isEmpty()) {
            throw new BusinessException("账号不能为空");
        }
        if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
            throw new BusinessException("密码不能为空");
        }
        if (dto.getNickname() == null || dto.getNickname().trim().isEmpty()) {
            throw new BusinessException("昵称不能为空");
        }

        // 账号唯一性
        long usernameCount = lambdaQuery().eq(User::getUsername, dto.getUsername()).count();
        if (usernameCount > 0) {
            throw new BusinessException("账号已存在");
        }

        // 昵称唯一性
        long nicknameCount = lambdaQuery().eq(User::getNickname, dto.getNickname()).count();
        if (nicknameCount > 0) {
            throw new BusinessException("昵称已被使用");
        }

        // 构建 User
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setNickname(dto.getNickname());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setAvatar("");
        user.setStatus(1);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        save(user);

        // 生成 token 并封装 VO
        return buildLoginVO(user);
    }

    @Override
    public LoginVO login(LoginDTO dto) {
        // 参数校验
        if (dto.getUsername() == null || dto.getUsername().trim().isEmpty()) {
            throw new BusinessException("账号不能为空");
        }
        if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
            throw new BusinessException("密码不能为空");
        }

        // 按账号查询
        User user = lambdaQuery().eq(User::getUsername, dto.getUsername()).one();

        if (user == null) {
            throw new BusinessException("账号或密码错误");
        }

        // 校验密码
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BusinessException("账号或密码错误");
        }

        // 校验状态
        if (user.getStatus() != null && user.getStatus() == 0) {
            throw new BusinessException("账号已被禁用");
        }

        // 生成 token 并封装 VO
        return buildLoginVO(user);
    }

    @Override
    public void updateUser(UserUpdateDTO dto, Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        // 昵称变更时校验唯一性
        if (dto.getNickname() != null && !dto.getNickname().trim().isEmpty()
                && !dto.getNickname().equals(user.getNickname())) {
            long count = lambdaQuery().eq(User::getNickname, dto.getNickname()).count();
            if (count > 0) {
                throw new BusinessException("昵称已被使用");
            }
            user.setNickname(dto.getNickname());
        }

        if (dto.getAvatar() != null) {
            user.setAvatar(dto.getAvatar());
        }
        if (dto.getPhone() != null) {
            user.setPhone(dto.getPhone());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getRegion() != null) {
            user.setRegion(dto.getRegion());
        }
        if (dto.getBio() != null) {
            user.setBio(dto.getBio());
        }
        user.setUpdatedAt(LocalDateTime.now());
        userMapper.updateById(user);
    }

    /**
     * 生成 token 并封装 LoginVO
     */
    private LoginVO buildLoginVO(User user) {
        String token = jwtUtil.generateToken(user.getId(), user.getUsername());
        LoginVO vo = new LoginVO();
        vo.setToken(token);
        vo.setUserId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setNickname(user.getNickname());
        vo.setAvatar(user.getAvatar());
        return vo;
    }
}
