package com.incubationHackathon.app.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String title;
    private String firstName;
    private String surname;
    private String preferredName;
    private LocalDate dob;
    private String address;
    private String contactNumber;
    private Boolean identityConfirmed;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private Boolean marketingEmail;
    private Boolean pushNotifications;
    private Boolean marketingText;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts;

    // Default constructor
    public User() {}

    // Full constructor
    public User(String title, String firstName, String surname, String preferredName, LocalDate dob,
                String address, String contactNumber, Boolean identityConfirmed, String username,
                String password, Boolean marketingEmail, Boolean pushNotifications, Boolean marketingText) {
        this.title = title;
        this.firstName = firstName;
        this.surname = surname;
        this.preferredName = preferredName;
        this.dob = dob;
        this.address = address;
        this.contactNumber = contactNumber;
        this.identityConfirmed = identityConfirmed;
        this.username = username;
        this.password = password;
        this.marketingEmail = marketingEmail;
        this.pushNotifications = pushNotifications;
        this.marketingText = marketingText;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
