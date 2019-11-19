package com.epitech.cash_manager.element;

public class TokenData {

    private String userID;

    private String xsrfToken;

    private long expireDate;

    public TokenData() {}

    public TokenData(String userID, String xsrfToken, long expireDate) {
        this.userID = userID;
        this.xsrfToken = xsrfToken;
        this.expireDate = expireDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getXsrfToken() {
        return xsrfToken;
    }

    public void setXsrfToken(String xsrfToken) {
        this.xsrfToken = xsrfToken;
    }

    public long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(long expireDate) {
        this.expireDate = expireDate;
    }

}