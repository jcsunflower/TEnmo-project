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

    /* should add a method to getAccountByUserId
    public Account getAccountByUserId(int userId) {
        String sql = "SELECT account_id, user_id, balance FROM accounts WHERE user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        Account account = null;
        if(result.next()) {
            account = mapRowTo account(result);
        }
        return account;

     */
    /* need a mapRowToAccount
      private Account mapRowToAccount(SqlRowSet result) {
        int accountId = result.getInt("account_id");
        int userId = result.getInt("user_id");
        String balanceString = result.getString("balance");
        BigDecimal balance = BigDecimal.valueOf(Double.valueOf(balanceString));
        return new Account(accountId, userId, balance);

     */

// might not need principal for these

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
