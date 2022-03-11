package com.techelevator.tenmo.model;

import org.springframework.data.relational.core.sql.In;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class Account {

    //Instance variables
    private final BigDecimal INITIAL_BALANCE = BigDecimal.valueOf(1000);

    @Min(0)
    private int accountId;
    @Min(0)
    private int userId;
    @Min(0)
    private BigDecimal balance = INITIAL_BALANCE;

// add a constructor that takes all three inputs, and a blank constructor

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
