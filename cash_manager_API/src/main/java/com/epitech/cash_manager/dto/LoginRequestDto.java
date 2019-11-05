package com.epitech.cash_manager.dto;

public class LoginRequestDto {

    public LoginRequestDto() {
    }

    String email;

    String pwd;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public LoginRequestDto(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

}