package com.techelevator.tenmo.dao;

public class JdbcTransferDao {
    /*

    public void createTransfer(Transfer transfer) {
        String sql = "INSERT INTO transfer(transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                     "VALUES (?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, transfer.getTransferId(), transfer.getTransferTypeId(), transfer.getTransferStatusId(), transfer.getAccountFromId(), transfer.getAccountToId(), transfer.getAmount());
    }

    public List<Transfer> getTransfersByUserId(int userId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer.transfer_id, transfer.transfer_type_id, transfer.transfer_status_id, transfer.account_from,transfer. account_to, transfer.amount " +
                     "FROM transfer " +
                     "JOIN account f ON transfer.account_from = account.account_id " +
                     "JOIN account t ON transfer.account_to = account.account_id " +
                     "WHERE t.user_id = ? OR f.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId);
        while(results.next()) {
            transfers.add(mapRowToTransfer(results));
        }
        return transfers;
    }

    public String getTransferType(int transferId) {
        String sql = "SELECT transfer_type_desc FROM transfer_type " +
                     "JOIN transfer ON transfer_type.transfer_type_id = transfer.transfer_type_id " +
                     "WHERE transfer_id = ?;";
        return jdbcTemplate.queryForObject(sql, transferId, String.class);
    }

    public String getTransferStatus(int transferId) {
        String sql = "SELECT transfer_status_desc FROM transfer_status " +
                     "JOIN transfer ON transfer_status.transfer_status_id = transfer.transfer_status_id " +
                     "WHERE transfer_id = ?;";
        return jdbcTemplate.queryForObject(sql, transferId, String.class);
    }

    private Transfer mapRowToTransfer(SqlRowSet results) {
        Transfer transfer = new Transfer();
        //set all params
        return transfer;
    }

     */
}
