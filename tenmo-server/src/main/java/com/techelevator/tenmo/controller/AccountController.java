package com.techelevator.tenmo.controller;

public class AccountController {
    /*
    //include our daos
    @Autowired
    AccountDao accountDao;
    @Autowired
    UserDao userDao;
    @Autowired
    TransferDao transferDao

    TransferLog transferLog = new TransferLog();

    // GET Methods

    // get all users
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userDao.findAll();
        //should we limit how much data this method displays?
    }

    // get balance from principal
    @RequestMapping(path = "/balance", method = RequestMethod.GET)
    public Balance getBalance(Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        return accountDao.getBalance(userId);
    }

    // get all transfers by userid
    @RequestMapping(path = "/transfers/{id}", method = RequestMethod.GET)
    public List<Transfer> getTransfersByUserId(@PathVariable("id") int userId) {
        return transferDao.getTransfersByUserId(userId);
    }


    // POST methods

    // create a transfer
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/transfers/{id}", method = RequestMethod.POST)
    public void addTransfer(@RequestBody Transfer transfer, @PathVariable("id") int transferId) throws InsufficientFunds{
        Account accountFrom = accountDao.getAccountByUserId(transfer.getAccountFromId());
        Account accountTo = accountDao.getAccountByUserId(transfer.getAccountToId());
        BigDecimal amount = transfer.getAmount();

        // check available amount
        if (accountFrom.getBalance() >= amount) {
            transferDao.createTransfer(transfer);
            accountFrom.subtractFromBalance(amount);
            accountTo.addToBalance(amount);
            transferLog.printTransferToLog(transfer);
        }
    }


     */
}
