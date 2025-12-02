package com.samir.librarymanagement.dto.Member;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class MemberRequest {
    @NotBlank(message = "Member name is required")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "invalid email format")
    @Column(unique = true)
    private String email;
}
