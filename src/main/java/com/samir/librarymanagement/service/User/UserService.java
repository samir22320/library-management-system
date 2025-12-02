package com.samir.librarymanagement.service.User;

import com.samir.librarymanagement.dto.User.UserRequest;
import com.samir.librarymanagement.dto.User.UserResponse;

public interface UserService {
    UserResponse register(UserRequest userRequest);

    void login(UserRequest userRequest);
}
