package com.example.SajiloyatraImplementation.model;

import jakarta.persistence.*;

@Entity
@Table(name = "profiles") // matches your database table
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio;
    private String skills;

    @Column(name = "profile_photo_url")
    private String profilePhotoUrl;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    // ===================== Getters and Setters =====================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}