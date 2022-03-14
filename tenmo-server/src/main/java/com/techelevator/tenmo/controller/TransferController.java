package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.log.TransferLog;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/transfers")
@PreAuthorize("isAuthenticated()")
public class TransferController {

    private TransferDao transferDao;
    private UserDao userDao;
    private AccountDao accountDao;

    public TransferController(AccountDao accountDao, UserDao userDao, TransferDao transferDao) {
        this.transferDao = transferDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    // get all transfers by principal
    @RequestMapping(method = RequestMethod.GET)
    public Transfer[] getTransfersByPrincipal(@Valid Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        return transferDao.getTransfersByUserId(userId);
    }

    //get all transfers by user id
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Transfer[] getTransfersByUserId(@Valid @PathVariable int id) {
        return transferDao.getTransfersByUserId(id);
    }

    @RequestMapping(path = "/send", method = RequestMethod.POST)
    public void transferFromAccount(@Valid @RequestParam BigDecimal amount, @RequestParam int id, Principal principal) {
        TransferLog transferLog = new TransferLog();
        int receiverId = id;
        int senderId = userDao.findIdByUsername(principal.getName());
        if (amount.compareTo(BigDecimal.ZERO) >= 0) {
            if (receiverId != senderId) {
                if (amount.compareTo(accountDao.getBalance(senderId)) <= 0) {
                    accountDao.subtractFromBalance(amount, senderId);
                    accountDao.addToBalance(amount, receiverId);
                    Transfer transfer = new Transfer();
                    transfer.setAmount(amount);
                    transfer.setTransferTypeId(2);
                    transfer.setTransferStatusId(2);
                    transfer.setAccountFromId(accountDao.getAccountByUserId(userDao.findIdByUsername(principal.getName())).getAccountId());
                    transfer.setAccountToId(accountDao.getAccountByUserId(receiverId).getAccountId());
                    int newId = transferDao.createTransfer(transfer);
                    transfer.setTransferId(newId);
                    transferLog.printTransferToLog(transfer, userDao.findIdByUsername(principal.getName()), receiverId);
                } else {
                    Transfer failedTransfer = new Transfer();
                    failedTransfer.setAmount(amount);
                    failedTransfer.setTransferTypeId(2);
                    failedTransfer.setTransferStatusId(3);
                    failedTransfer.setAccountFromId(accountDao.getAccountByUserId(userDao.findIdByUsername(principal.getName())).getAccountId());
                    failedTransfer.setAccountToId(accountDao.getAccountByUserId(receiverId).getAccountId());
                    int newId = transferDao.createTransfer(failedTransfer);
                    failedTransfer.setTransferId(newId);
                    transferLog.printTransferToLog(failedTransfer, userDao.findIdByUsername(principal.getName()), receiverId);
                }
            } else {
                System.out.println("Cannot send money to self.");
            }
        } else {
            System.out.println("Cannot send negative amount.");
        }

    }
}
