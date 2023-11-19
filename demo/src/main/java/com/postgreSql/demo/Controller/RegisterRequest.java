package com.postgreSql.demo.Controller;

import com.postgreSql.demo.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String nom;
    private String username;
    private long age ;
    private String club;
    private String password;
    private Role role;


}
