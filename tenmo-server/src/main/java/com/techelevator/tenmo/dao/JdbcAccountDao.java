package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Principal;

@Component
public class JdbcAccountDao implements AccountDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Account getAccountByUserId(int userId) {
        String sql = "SELECT account_id, user_id, balance FROM accounts WHERE user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        Account account = null;
        if (result.next()) {
            account = mapRowToAccount(result);
        }
        return account;
    }

    @Override
    public BigDecimal getBalance(int userId) {
        String sql = "SELECT balance " +
                "FROM account " +
                "WHERE user_id = ?;";
        BigDecimal balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, userId);

        return balance;
    }

    @Override
    public void addToBalance(BigDecimal deposit, int userId) {
        String sql = "UPDATE account " +
                "SET balance = ? " +
                "WHERE user_id = ?;";
        BigDecimal newBalance = getBalance(userId).add(deposit);
        jdbcTemplate.update(sql, newBalance, userId);
    }

    @Override
    public void subtractFromBalance(BigDecimal withdrawal, int userId) {
        String sql = "UPDATE account " +
                "SET balance = ? " +
                "WHERE user_id = ?;";
        BigDecimal newBalance = getBalance(userId).subtract(withdrawal);
        jdbcTemplate.update(sql, newBalance, userId);
    }

    private Account mapRowToAccount(SqlRowSet result) {
        Account account = new Account();
        account.setAccountId(result.getInt("account_id"));
        account.setUserId(result.getInt("user_id"));
        String balanceString = result.getString("balance");
        account.setBalance(BigDecimal.valueOf(Double.valueOf(balanceString)));
        return account;
    }
}
