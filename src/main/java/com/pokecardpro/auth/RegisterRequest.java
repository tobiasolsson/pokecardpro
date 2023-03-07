package com.pokecardpro.auth;

import com.pokecardpro.models.Wishlist;

public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int phone;
    private String street;
    private int streetNr;
    private String city;
    private int zipCode;
    /*private Wishlist wishlist;*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNr() {
        return streetNr;
    }

    public void setStreetNr(int streetNr) {
        this.streetNr = streetNr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    /*public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }*/

    public RegisterRequest(String firstName, String lastName, String email, String password, int phone, String street,
                           int streetNr, String city, int zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.street = street;
        this.streetNr = streetNr;
        this.city = city;
        this.zipCode = zipCode;
    }

    public RegisterRequest() {
    }
}
