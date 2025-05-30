package com.speedroller.speed_roller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Admin {

    private long id;
    private String username;
    private String password;
    private String email;
    private String role;

}