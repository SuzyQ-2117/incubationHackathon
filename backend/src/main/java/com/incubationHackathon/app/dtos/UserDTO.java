package com.incubationHackathon.app.dtos;

import com.incubationHackathon.app.entities.User;

import java.time.LocalDate;

public class UserDTO {

    private Long userId;
    private String title;
    private String firstName;
    private String surname;
    private String preferredName;
    private LocalDate dob;
    private String address;
    private String contactNumber;
    private Boolean identityConfirmed;
    private String username;
    private Boolean marketingEmail;
    private Boolean pushNotifications;
    private Boolean marketingText;

    // Default constructor
    public UserDTO() {}

    // Full constructor
    public UserDTO(Long userId, String title, String firstName, String surname, String preferredName,
                   LocalDate dob, String address, String contactNumber, Boolean identityConfirmed,
                   String username, Boolean marketingEmail, Boolean pushNotifications, Boolean marketingText) {
        this.userId = userId;
        this.title = title;
        this.firstName = firstName;
        this.surname = surname;
        this.preferredName = preferredName;
        this.dob = dob;
        this.address = address;
        this.contactNumber = contactNumber;
        this.identityConfirmed = identityConfirmed;
        this.username = username;
        this.marketingEmail = marketingEmail;
        this.pushNotifications = pushNotifications;
        this.marketingText = marketingText;
    }

    // Constructor that maps a User entity to UserDTO
    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.title = user.getTitle();
        this.firstName = user.getFirstName();
        this.surname = user.getSurname();
        this.preferredName = user.getPreferredName();
        this.dob = user.getDob();
        this.address = user.getAddress();
        this.contactNumber = user.getContactNumber();
        this.identityConfirmed = user.getIdentityConfirmed();
        this.username = user.getUsername();
        this.marketingEmail = user.getMarketingEmail();
        this.pushNotifications = user.getPushNotifications();
        this.marketingText = user.getMarketingText();
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Boolean getIdentityConfirmed() {
        return identityConfirmed;
    }

    public void setIdentityConfirmed(Boolean identityConfirmed) {
        this.identityConfirmed = identityConfirmed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getMarketingEmail() {
        return marketingEmail;
    }

    public void setMarketingEmail(Boolean marketingEmail) {
        this.marketingEmail = marketingEmail;
    }

    public Boolean getPushNotifications() {
        return pushNotifications;
    }

    public void setPushNotifications(Boolean pushNotifications) {
        this.pushNotifications = pushNotifications;
    }

    public Boolean getMarketingText() {
        return marketingText;
    }

    public void setMarketingText(Boolean marketingText) {
        this.marketingText = marketingText;
    }
}
