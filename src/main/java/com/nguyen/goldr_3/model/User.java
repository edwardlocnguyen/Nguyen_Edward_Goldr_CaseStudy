package com.nguyen.goldr_3.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/*
    * user model to create an account with the app
    * the user is the only model to have a relationship with every other model
    * @JsonManagedReference annotation prevents infinite loops when displaying foreign model data related to the User obj
 */

@Entity
@Table
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @Nonnull
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String occupation;
    private LocalDate dob;
    private Integer creditScore;

    //  mappedBy creates the join column in the target table
//	cascade ALL deletes all child accounts if the parent user is deleted
//	fetch type is defaulted to LAZY, and child Accounts are not loaded along with the parent User
//		update: needed to change to LAZY to delete accounts
    @JsonManagedReference
    @OneToMany(mappedBy = "user", targetEntity = Account.class, cascade = CascadeType.ALL)
    private List<Account> accounts;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", targetEntity = Category.class, cascade = CascadeType.ALL)
    private List<Category> category;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", targetEntity = Entry.class, cascade = CascadeType.ALL)
    private List<Entry> entries;

    public User() {
        this.email = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.occupation = "";
        this.dob = null;
        this.creditScore = 0;
        this.accounts = null;
        this.category = null;
        this.entries = null;
    }

    public User(int id, String email, String password, String firstName, String lastName, String occupation, LocalDate dob, Integer creditScore, List<Account> accounts, List<Category> category, List<Entry> entries) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.occupation = occupation;
        this.dob = dob;
        this.creditScore = creditScore;
        this.accounts = accounts;
        this.category = category;
        this.entries = entries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
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
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", occupation='" + occupation + '\'' +
                ", dob=" + dob +
                ", creditScore=" + creditScore +
                ", accounts=" + accounts +
                ", category=" + category +
                ", entries=" + entries +
                '}';
    }
}
