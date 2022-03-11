package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.log.TransferLog;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@PreAuthorize("isAuthenticated()")
public class AccountController {

    //include our daos

    private AccountDao accountDao;
    private UserDao userDao;
    private TransferDao transferDao;

    public AccountController(AccountDao accountDao, UserDao userDao, TransferDao transferDao) {
        this.transferDao = transferDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
    }


    // GET Methods

    // get all users
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        try {
            return userDao.findAll();
        } catch (NullPointerException e) {
            System.out.println("No users found.");
            return null;
        }
        //should we limit how much data this method displays?
    }

    // get balance from principal
    @RequestMapping(path = "/balance", method = RequestMethod.GET)
    public BigDecimal getBalance(@Valid Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        return accountDao.getBalance(userId);
    }

    // get all transfers by principal
    @RequestMapping(path = "/transfers}", method = RequestMethod.GET)
    public List<Transfer> getTransfersByUserId(@Valid Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        return transferDao.getTransfersByUserId(userId);
    }


    // POST methods

    // create a transfer
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/transfers/{id}", method = RequestMethod.POST)
    public void addTransfer(@Valid @RequestBody Transfer transfer, @PathVariable("id") int transferId, Principal principal) {
        TransferLog transferLog = new TransferLog();
        Account accountFrom = accountDao.getAccountByUserId(transfer.getAccountFromId());
        Account accountTo = accountDao.getAccountByUserId(transfer.getAccountToId());
        BigDecimal amount = transfer.getAmount();

        // check available amount
        if (accountFrom.getBalance().compareTo(amount) >= 0) {
            transferDao.createTransfer(transfer);
            accountDao.subtractFromBalance(amount, accountFrom.getUserId());
            accountDao.addToBalance(amount, accountTo.getUserId());
            transferLog.printTransferToLog(transfer);
        }
    }





}
