package com.plantasapi.plantas.dtos;

public class UserChangePasswordDTO {
    private String password;
    private String newpassword;

    public UserChangePasswordDTO() {
    }

    public UserChangePasswordDTO(String password, String newpassword) {
        this.password = password;
        this.newpassword = newpassword;
    }

    public String getPassword() {
        return password;
    }

    public String getNewpassword() {
        return newpassword;
    }
}
