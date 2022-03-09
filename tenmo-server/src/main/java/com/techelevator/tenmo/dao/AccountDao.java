package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.security.Principal;

public interface AccountDao {

    BigDecimal getBalance(int userID, Principal principal);

    void addToBalance(BigDecimal deposit, int userID, Principal principal);

    void subtractFromBalance(BigDecimal withdrawal, int userID, Principal principal);

}
