package com.rodrigoramos.tech_challenge.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String login;
    private String password;
    private LocalDateTime lastModifiedAt;
    @OneToMany(mappedBy = "user")
    private List<Address> addresses = new ArrayList<>();

    public User() {
    }

    public User(Long id, String name, String login, String password, List<Address> addresses) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.lastModifiedAt = LocalDateTime.now();
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public List<Address> getAddresses() {
        return addresses;
    }
}
