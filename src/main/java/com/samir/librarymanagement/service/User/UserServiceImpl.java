package com.samir.librarymanagement.service.User;

import com.samir.librarymanagement.JwtService;
import com.samir.librarymanagement.dto.User.UserRequest;
import com.samir.librarymanagement.dto.User.UserResponse;
import com.samir.librarymanagement.entity.User;
import com.samir.librarymanagement.execption.ResourceNotFoundException;
import com.samir.librarymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.webauthn.management.RelyingPartyPublicKey;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final JwtService jwtService;
    public final AuthenticationManager authenticationManager;
    public final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(JwtService jwtService, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
    }
    private final UserResponse mapToDto(User user)
    {
        return new UserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail()
        );
    }

    @Override
    public UserResponse register(UserRequest userRequest) {
        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
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

    @Override
    public String verify(UserRequest userRequest) {
        User user =new User();
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user);
        return "falied";
    }
}
