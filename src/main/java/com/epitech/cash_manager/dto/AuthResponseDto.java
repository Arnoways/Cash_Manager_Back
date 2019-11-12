package com.epitech.cash_manager.dto;

public class AuthResponseDto {

    String  token;

    Long userID;

    public AuthResponseDto() {}

    public AuthResponseDto(String token, Long userID) {
        this.userID = userID;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}

