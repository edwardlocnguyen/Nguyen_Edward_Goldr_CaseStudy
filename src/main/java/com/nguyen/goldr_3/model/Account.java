package com.nguyen.goldr_3.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @JsonBackReference
    @ManyToOne(targetEntity = User.class)
    private User user;

    @JsonBackReference
    @ManyToOne(targetEntity = Category.class)
    private Category category;

    @JsonManagedReference
    @OneToMany(mappedBy = "account", targetEntity = Entry.class, cascade = CascadeType.ALL)
    private List<Entry> entries;

    public Account() {
        this.name = "";
        this.user = null;
        this.category = null;
        this.entries = null;
    }

    public Account(int id, String name, User user, Category category, List<Entry> entries) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.category = category;
        this.entries = entries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                ", category=" + category +
                ", entries=" + entries +
                '}';
    }
}
