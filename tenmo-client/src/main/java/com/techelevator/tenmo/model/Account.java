package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {

    //Instance variables
    private final BigDecimal INITIAL_BALANCE = BigDecimal.valueOf(1000);

    private int accountId;

    private int userId;

    private BigDecimal balance = INITIAL_BALANCE;

// add a constructor that takes all three inputs, and a blank constructor

    public Account() {

    }

    //Getters and Setters
    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
