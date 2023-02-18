package com.nguyen.goldr_3.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table
public class Entry implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double amount;
    private LocalDate date;

    @JsonBackReference
    @ManyToOne(targetEntity = Account.class)
    private Account account;

    public Entry() {
        this.amount = 0.0;
        this.date = null;
        this.account = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", account=" + account +
                '}';
    }


}
