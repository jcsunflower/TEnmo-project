package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.log.TransferLog;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public String getUsernameFromID(int id) {
        try {
            return userDao.findUsernameById(id);
        } catch (IllegalArgumentException e) {
            System.out.println("ID does not match any users in the system");
        }
        return null;
    }

    // get balance from principal
    @RequestMapping(path = "/balance", method = RequestMethod.GET)
    public BigDecimal getBalance(@Valid Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        return accountDao.getBalance(userId);
    }







}
