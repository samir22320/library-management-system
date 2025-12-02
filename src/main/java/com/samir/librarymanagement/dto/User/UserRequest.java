package com.samir.librarymanagement.dto.User;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class UserRequest {
        @NotBlank
        private String userName;
        @NotBlank
        private String password;
        @NotBlank
        private String role;

}
