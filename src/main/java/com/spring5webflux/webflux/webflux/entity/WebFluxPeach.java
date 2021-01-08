package com.spring5webflux.webflux.webflux.entity;

public class WebFluxPeach {
    private String name;
    private Integer gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public WebFluxPeach(String name, Integer gender) {
        this.name = name;
        this.gender = gender;
    }

    public WebFluxPeach() {
    }
}
