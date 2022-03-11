package com.techelevator.tenmo.log;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TransferLog {

    private File log = new File("TransferLog.txt");

    TransferDao transferDao;

    PrintWriter logPrint;

    {
        try {
            logPrint = new PrintWriter(new FileOutputStream(log, true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public TransferLog() {
    }

    public String dateTime() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return date + " " + time;
    }

    public void printTransferToLog(Transfer transfer) {
        String lineToPrint = dateTime() +
                            " TRANSFER ID: " + transfer.getTransferId() +
                            " ACCOUNT FROM: " + transfer.getAccountFromId() +
                            " ACCOUNT TO: " + transfer.getAccountToId() +
                            " AMOUNT: " + transfer.getAmount() +
                            " TYPE: " + transferDao.getTransferType(transfer.getTransferId()) +
                            " STATUS: " + transferDao.getTransferStatus(transfer.getTransferId());
       logPrint.println(lineToPrint);
       logPrint.flush();
    }

}
