package com.nguyen.goldr_3.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @JsonBackReference
    @ManyToOne(targetEntity = User.class)
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "category", targetEntity = Account.class, cascade = CascadeType.ALL)
    private List<Account> accounts;

    public Category() {
        this.name = "";
        this.user = null;
        this.accounts = null;
//        this.txns = null;
    }

    public Category(int id, String name, User user, List<Account> accounts) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.accounts = accounts;
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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                ", accounts=" + accounts +
                '}';
    }
}
