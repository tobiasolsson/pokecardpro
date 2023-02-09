package com.pokecardpro.models;


public enum Shipping {

    POSTNORD("PostNord"),
    SCHENKER("Schenker"),
    DHL("DHL");

    private final String value;

    Shipping(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
