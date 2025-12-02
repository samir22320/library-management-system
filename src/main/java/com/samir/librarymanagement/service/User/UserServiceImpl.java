package com.samir.librarymanagement.service.User;

import com.samir.librarymanagement.dto.Member.MemberResponse;
import com.samir.librarymanagement.dto.User.UserRequest;
import com.samir.librarymanagement.dto.User.UserResponse;
import com.samir.librarymanagement.entity.Member;
import com.samir.librarymanagement.entity.User;
import com.samir.librarymanagement.execption.ResourceNotFoundException;
import com.samir.librarymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    public final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    private final UserResponse mapToDto(User user)
    {
        return new UserResponse(
                user.getId(),
                user.getUserName()
        );
    }

    @Override
    public UserResponse register(UserRequest userRequest) {
        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        user.setRole("ROLE_" + userRequest.getRole());
        User savedUser = userRepository.save(user);
        return mapToDto(savedUser);
    }

    @Override
    public void login(UserRequest userRequest) {

        User user = userRepository.findByUserName(userRequest.getUserName()).orElseThrow(
                () -> new ResourceNotFoundException("this UserName Not founded")
        );


    }
}
