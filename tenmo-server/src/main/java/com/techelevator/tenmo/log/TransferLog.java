package com.techelevator.tenmo.log;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TransferLog {

    private File log = new File("C:\\Users\\Student\\workspace\\capstone-2-team-5\\tenmo-server\\src\\main\\java\\com\\techelevator\\tenmo\\log\\TransferLog.txt");

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

    public void printTransferToLog(Transfer transfer, int senderId, int receiverId) {
            String lineToPrint = dateTime() +
                    " | TRANSFER ID: " + transfer.getTransferId() +
                    " | USER FROM: " + senderId +
                    " | USER TO: " + receiverId +
                    " | AMOUNT: " + transfer.getAmount() +
                    " | TYPE: " + typeToString(transfer.getTransferTypeId()) +
                    " | STATUS: " + statusToString(transfer.getTransferStatusId());
            logPrint.println(lineToPrint);
            logPrint.flush();
    }

    private String typeToString(int typeId) {
        switch (typeId) {
            case 1:
                return "Request";
            case 2:
                return "Send";
        }
        return null;
    }

    private String statusToString(int statusId) {
        switch (statusId) {
            case 1:
                return "Pending";
            case 2:
                return "Approved";
            case 3:
                return "Rejected";
        }
        return null;
    }

}
