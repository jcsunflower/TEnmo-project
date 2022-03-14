package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {

    Transfer[] getTransfersByUserId(int userId);

    int createTransfer(Transfer transfer);

    String getTransferType(int transferId);

    String getTransferStatus(int transferId);


}
