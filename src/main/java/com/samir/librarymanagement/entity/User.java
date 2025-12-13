package com.samir.librarymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "userId")
    @SequenceGenerator(name = "userId",sequenceName = "userId",allocationSize = 1)
    private Long id;
    private String userName;
    private String email;
    private String password;
    private String role;
}
