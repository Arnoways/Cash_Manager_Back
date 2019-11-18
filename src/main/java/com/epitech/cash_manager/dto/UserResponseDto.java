package com.epitech.cash_manager.dto;

public class UserResponseDto {

    private Long userID;

    private String email;

    private String login;

    public UserResponseDto() {}

    public UserResponseDto(Long userID, String email, String login) {
        this.userID = userID;
        this.email = email;
        this.login=login;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
