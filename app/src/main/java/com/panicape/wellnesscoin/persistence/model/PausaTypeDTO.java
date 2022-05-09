package com.panicape.wellnesscoin.persistence.model;

import java.util.Date;

public class PausaTypeDTO {

    private String name;

    private Date dateCreation;

    private String userCreation;

    public PausaTypeDTO(String name, Date dateCreation, String userCreation) {
        this.name = name;
        this.dateCreation = dateCreation;
        this.userCreation = userCreation;
    }

    public PausaTypeDTO() {
    }

    // Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getUserCreation() {
        return userCreation;
    }

    public void setUserCreation(String userCreation) {
        this.userCreation = userCreation;
    }
}
