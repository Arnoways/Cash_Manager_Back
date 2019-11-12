package com.epitech.cash_manager.dto;

public class UserRequestDto {

    String email;

    String login;

    String pwd;

    public UserRequestDto() {}

    public UserRequestDto(String email, String login, String pwd) {
        this.email = email;
        this.login=login;
        this.pwd = pwd;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}