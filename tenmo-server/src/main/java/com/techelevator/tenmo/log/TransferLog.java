package com.techelevator.tenmo.log;

public class TransferLog {
    /*
    private File log = new File("TransferLog.txt");

    TransferDao transferDao;

    PrintWriter logPrint = new PrintWriter(new FileOutputStream(log, true));

    public TransferLog() throws FileNotFoundException {
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
     */
}
