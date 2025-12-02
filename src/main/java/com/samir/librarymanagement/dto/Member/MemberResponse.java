package com.samir.librarymanagement.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberResponse {

    private Long id;
    private String name;
    private String email;
}
