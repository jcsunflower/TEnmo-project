package com.techelevator.tenmo.exception;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException() {
        super("Account not found.");
    }
}
