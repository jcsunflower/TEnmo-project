package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;
import java.security.Principal;

public class JdbcAccountDao implements AccountDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public BigDecimal getBalance(int userID, Principal principal) {
        String sql = "SELECT balance " +
                "FROM account " +
                "WHERE user_id = ?;";
        BigDecimal balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, userID);

        return balance;
    }

    @Override
    public void addToBalance(BigDecimal deposit, int userID, Principal principal) {
        String sql = "UPDATE account " +
                "SET balance = ? " +
                "WHERE user_id = ?;";
        BigDecimal newBalance = getBalance(userID, principal).add(deposit);
        jdbcTemplate.update(sql, newBalance, userID);
    }

    @Override
    public void subtractFromBalance(BigDecimal withdrawal, int userID, Principal principal) {
        String sql = "UPDATE account " +
                "SET balance = ? " +
                "WHERE user_id = ?;";
        BigDecimal newBalance = getBalance(userID, principal).subtract(withdrawal);
        jdbcTemplate.update(sql, newBalance, userID);
    }
}
