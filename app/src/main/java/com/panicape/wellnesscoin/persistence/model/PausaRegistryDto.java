package com.panicape.wellnesscoin.persistence.model;

public class PausaRegistryDto {

    private String dateCreation;

    private String username;

    private String pausaType;

    private String content;


    public PausaRegistryDto() {
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPausaType() {
        return pausaType;
    }

    public void setPausaType(String pausaType) {
        this.pausaType = pausaType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
