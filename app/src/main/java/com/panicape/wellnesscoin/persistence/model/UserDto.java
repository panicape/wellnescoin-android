package com.panicape.wellnesscoin.persistence.model;

public class UserDto {

    private String username;

    private String email;

    private String userType;

    private String name;

    private String mail;

    private String phone;

    public UserDto(String username, String email, String userType, String name, String mail, String phone) {
        this.username = username;
        this.email = email;
        this.userType = userType;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
    }

    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
