package ru.mirea.paintball.entity;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String fio;
}
