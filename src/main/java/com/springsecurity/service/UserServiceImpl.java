package com.springsecurity.service;

import com.springsecurity.dto.request.UserCreationDto;
import com.springsecurity.dto.request.UserUpdatingDto;
import com.springsecurity.dto.response.UserResponseDto;
import com.springsecurity.entity.Role;
import com.springsecurity.entity.User;
import com.springsecurity.exception.AppException;
import com.springsecurity.exception.ErrorCode;
import com.springsecurity.mapper.UserMapper;
import com.springsecurity.repository.RoleRepository;
import com.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto createUser(UserCreationDto user) {

        if (userRepository.existsByUserName(user.getUserName()))
            throw new AppException(ErrorCode.USER_EXISTED);

        HashSet<Role> roles = new HashSet<>();
        Optional<Role> optionalRole = roleRepository.findByRoleName("USER");

        if(optionalRole.isEmpty()){
            roles.add(Role.builder()
                    .roleName(com.springsecurity.enums.Role.USER.name())
                    .build());
        }else{
            roles.add(optionalRole.get());
        }

        User newUser = User.builder()
                .userName(user.getUserName())
                .passWord(passwordEncoder.encode(user.getPassWord()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dob(user.getDob())
                .roles(new LinkedHashSet<>())
                .build();

        newUser.assignRoles(roles);

        userRepository.save(newUser);
        return userMapper.toUserResponse(newUser);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Set<User> getAllUsers() {
        return new HashSet<>(userRepository.findAll());
    }

    @Override
    @PostAuthorize("returnObject.userName == authentication.name")
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOTFOUND)
        );
    }

    @Override
    public User updateUser(Long userId, UserUpdatingDto updatedUser) {
        User user = getUser(userId);

        userMapper.updateUser(user, updatedUser);

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        getUser(userId);
        userRepository.deleteById(userId);
    }

    @Override
    public UserResponseDto getMyInfo() {
        SecurityContext context = SecurityContextHolder.getContext();

        Optional<User> user = userRepository.findByUserName(context.getAuthentication().getName());
        if(user.isEmpty()) throw new AppException(ErrorCode.FAIL_TO_SEE_MY_INFO);

        return userMapper.toUserResponse(user.get());
    }
}
