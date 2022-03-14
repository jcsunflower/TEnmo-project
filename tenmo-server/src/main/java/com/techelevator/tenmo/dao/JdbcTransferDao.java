package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createTransfer(Transfer transfer) {
        String sql = "INSERT INTO transfer(transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                     "VALUES (?, ?, ?, ?, ?) RETURNING transfer_id;";
        int transferId = jdbcTemplate.queryForObject(sql, int.class, transfer.getTransferTypeId(), transfer.getTransferStatusId(), transfer.getAccountFromId(), transfer.getAccountToId(), transfer.getAmount());
        return transferId;
    }

    public Transfer[] getTransfersByUserId(int userId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                     "FROM transfer " +
                     "JOIN account AS f ON transfer.account_from = f.account_id " +
                     "JOIN account AS t ON transfer.account_to = t.account_id " +
                     "WHERE t.user_id = ? OR f.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId);
        while(results.next()) {
            transfers.add(mapRowToTransfer(results));
        }
        Transfer[] array = new Transfer[transfers.size()];
        return transfers.toArray(array);
    }

    public String getTransferType(int transferId) {
        String sql = "SELECT transfer_type_desc FROM transfer_type " +
                     "JOIN transfer ON transfer_type.transfer_type_id = transfer.transfer_type_id " +
                     "WHERE transfer_id = ?;";
        return jdbcTemplate.queryForObject(sql, String.class, transferId);
    }

    public String getTransferStatus(int transferId) {
        String sql = "SELECT transfer_status_desc FROM transfer_status " +
                     "JOIN transfer ON transfer_status.transfer_status_id = transfer.transfer_status_id " +
                     "WHERE transfer_id = ?;";
        return jdbcTemplate.queryForObject(sql, String.class, transferId);
    }

    private Transfer mapRowToTransfer(SqlRowSet results) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(results.getInt("transfer_id"));
        transfer.setTransferTypeId(results.getInt("transfer_type_id"));
        transfer.setTransferStatusId(results.getInt("transfer_status_id"));
        transfer.setAccountFromId(results.getInt("account_from"));
        transfer.setAccountToId(results.getInt("account_to"));
        transfer.setAmount(BigDecimal.valueOf(Double.valueOf(results.getString("amount"))));
        return transfer;
    }


}
