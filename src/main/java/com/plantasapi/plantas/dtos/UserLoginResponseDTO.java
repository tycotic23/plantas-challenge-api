package com.plantasapi.plantas.dtos;

public class UserLoginResponseDTO {
    private String username;
    private String token;

    public UserLoginResponseDTO() {
    }

    public UserLoginResponseDTO(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }
}
