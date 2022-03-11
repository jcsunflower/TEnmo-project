package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import java.math.BigDecimal;

public interface AccountDao {

    Account getAccountByUserId(int userId);

    BigDecimal getBalance(int userId);

    void addToBalance(BigDecimal deposit, int userId);

    void subtractFromBalance(BigDecimal withdrawal, int userId);

}
