package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {

    List<Transfer> getTransfersByUserId(int userId);

    void createTransfer(Transfer transfer);

    String getTransferType(int transferId);

    String getTransferStatus(int transferId);


}
