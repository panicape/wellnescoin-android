package com.panicape.wellnesscoin.persistence.model;

public class UserDto {

    private String username;

    private String name;

    private String mail;

    private String phone;

    private String password;


    public UserDto(String username, String name, String mail, String phone, String password) {
        this.username = username;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.password = password;
    }

    public UserDto(String username, String name, String mail, String phone) {
        this.username = username;
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
}
