package com.example.jwt_restapi.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bd-clima")
public class ClimaEntity {

    private String pais;
    private String date;
    private String text;

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
