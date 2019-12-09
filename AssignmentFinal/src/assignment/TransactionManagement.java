/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Thong Nguyen
 */
public class TransactionManagement {

    private String T_FILE;                                              //file that stores the employees
    private ArrayList<String> transactions;                             //list of transactions
    Calendar cal;                              //gets a calendar using the default time zone and locale
    Date date;                                          //Date object representing this Calendar's time value
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); //date format
    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");         //date format

    /**
     * CONSTRUCTOR
     *
     * @param T_FILE
     */
    public TransactionManagement(String T_FILE) {
        this.T_FILE = T_FILE;
        this.transactions = new ArrayList<>();
    }

    /**
     * add new transaction
     *
     * @param content
     * @param balance1
     * @param balance2
     */
    public void addTransaction(String content, double balance1, double balance2) {
        cal = Calendar.getInstance();
        date = cal.getTime();
        this.transactions.add(content + "  " + sdf.format(date) + "\t" + balance1 + "\t" + balance2);
    }

    /**
     * show 5 nearest transactions of particular id
     *
     * @param uId
     */
    public void showByAccount(String uId) {
        int count = 0;
        for (int i = transactions.size() - 1; i >= 0; i--) {
            if (transactions.get(i).contains(uId) && count < 5) {
                String str = transactions.get(i);
                str = str.substring(0, str.lastIndexOf('\t'));
                str = str.substring(0, str.lastIndexOf('\t'));
                System.out.print(str);
                str = str.substring(0, str.length() - 20);
                str = str.replaceAll("[^(\\d\\.\\d$)]", "");
                if (transactions.get(i).contains("transfer")) {
                    if (str.substring(str.length() - 14, str.length()).equals(uId)) {
                        System.out.print("  +");
                    } else {
                        System.out.print("  -");
                    }
                    str = str.substring(14, str.length() - 14);
                } else if (transactions.get(i).contains("withdraw")) {
                    System.out.print("  -");
                    str = str.substring(14, str.length());
                } else if (transactions.get(i).contains("deposit")) {
                    System.out.print("  +");
                    str = str.substring(14, str.length());
                }
                System.out.println(str);
                ++count;
            }
        }
    }

    /**
     * check if the id have reach 5 transaction on a single day
     *
     * @param uId
     * @param key
     * @return
     */
    public boolean blockTransactionTimes(String uId, String key) {
        cal = Calendar.getInstance();
        date = cal.getTime();
        int times = 0;
        for (String string : transactions) {
            if (string.contains(key) && string.contains(uId) && string.contains(sdf2.format(date))) {
                ++times;
            }
            if (times >= 5) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if the id have reach total of 25000$ of transactions on a single
     * day
     *
     * @param uId
     * @param key
     * @param transMoney
     * @return
     */
    public boolean blockTransactionAmount(String uId, String key, double transMoney) {
        cal = Calendar.getInstance();
        date = cal.getTime();
        double amount = 0;
        for (String string : transactions) {
            if (string.contains(key) && string.contains(uId) && string.contains(sdf2.format(date))) {
                string = string.substring(0, string.lastIndexOf('\t'));
                string = string.substring(0, string.lastIndexOf('\t'));
                string = string.substring(0, string.length() - 20);
                string = string.replaceAll("[^(\\d\\.\\d)]", "");
                string = string.substring(14, string.length());
                amount += Double.parseDouble(string);
            }
        }
        return amount + transMoney > 25000;
    }

    /**
     * show all transfers performed on the passing day
     *
     * @param date
     * @param um
     */
    public void reportTransfer(String date, UserManagement um) {
        String debitName, debitID, debitBalance, creditName, creditID, creditBalance, moneyTransfer;
        System.out.println("Debitor Name\t\tDebitor ID\t\tDebitor Balance\t\tMoney transfer\t\tCreditor Name\t\tCreditor ID\t\tCreditor Balance");
        for (String string : transactions) {
            if (string.contains("transfer") && string.contains(date)) {
                creditBalance = string.substring(string.lastIndexOf('\t') + 1, string.length()) + "$";
                string = string.substring(0, string.lastIndexOf('\t'));
                debitBalance = string.substring(string.lastIndexOf('\t') + 1, string.length()) + "$";
                string = string.substring(0, string.lastIndexOf('\t'));
                string = string.substring(0, string.length() - 20);
                string = string.replaceAll("[^(\\d\\.\\d$)]", "");
                debitID = string.substring(0, 14);
                creditID = string.substring(string.length() - 14, string.length());
                debitName = um.getName(debitID);
                creditName = um.getName(creditID);
                moneyTransfer = string.substring(14, string.length() - 14);
                System.out.printf("%-24s%-24s%-24s%-24s%-24s%-24s%-24s\n", debitName, debitID, debitBalance, moneyTransfer, creditName, creditID, creditBalance);
            }
        }
    }

    /**
     * show all deposits performed on the passing day
     *
     * @param date
     * @param um
     */
    public void reportDeposit(String date, UserManagement um) {
        String name, id, amount, balance;
        System.out.println("Name                    ID                      Deposited amount        Balance                 ");
        for (String string : transactions) {
            if (string.contains("deposit") && string.contains(date)) {
                string = string.substring(0, string.lastIndexOf('\t'));
                balance = string.substring(string.lastIndexOf('\t') + 1, string.length()) + "$";
                string = string.substring(0, string.lastIndexOf('\t'));
                string = string.substring(0, string.length() - 20);
                string = string.replaceAll("[^(\\d\\.\\d$)]", "");
                id = string.substring(0, 14);
                name = um.getName(id);
                amount = string.substring(14, string.length());
                System.out.printf("%-24s%-24s%-24s%-24s\n", name, id, amount, balance);
            }
        }
    }

    /**
     * show all withdrawals performed on the passing day
     *
     * @param date
     * @param um
     */
    public void reportWithdrawal(String date, UserManagement um) {
        String name, id, amount, balance;
        System.out.println("Name                    ID                      Withdraw amount         Balance                 ");
        for (String string : transactions) {
            if (string.contains("withdraw") && string.contains(date)) {
                string = string.substring(0, string.lastIndexOf('\t'));
                balance = string.substring(string.lastIndexOf('\t') + 1, string.length()) + "$";
                string = string.substring(0, string.lastIndexOf('\t'));
                string = string.substring(0, string.length() - 20);
                string = string.replaceAll("[^(\\d\\.\\d$)]", "");
                id = string.substring(0, 14);
                name = um.getName(id);
                amount = string.substring(14, string.length());
                System.out.printf("%-24s%-24s%-24s%-24s\n", name, id, amount, balance);
            }
        }
    }

    /**
     * load transaction from file to list
     *
     * @throws IOException
     */
    public void loadTransactiotns() throws IOException {
        File tFile = new File(T_FILE);
        if (!tFile.exists()) {
            tFile.createNewFile(); //If not, creates new file
            System.out.println(
                    "The data file does not exist. "
                    + "Creating new data file transactionacc.txt... "
                    + "Done!");
        } else {
            //If file exists, load this data file
            System.out.print(
                    "\nThe data file " + T_FILE + " is found. "
                    + "Data of transactions are being loaded...");

            //Loads text file into buffer
            try (BufferedReader br = new BufferedReader(new FileReader(T_FILE))) { //type BufferedReader
                String line;
                //Reads transaction (line by file)
                line = br.readLine();
                while (line != null) {
                    this.transactions.add(line);
                    line = br.readLine();
                }
            }
            System.out.println("Done! Loaded all transactions");
        }
    }

    /**
     * save array to file when quitting
     *
     * @throws IOException
     */
    public void saveTransactions() throws IOException {
        FileWriter fw = new FileWriter(new File(T_FILE), false);
        try {
            System.out.print(
                    "\nTransactions are being saved into data file " + T_FILE + "...");
            for (int i = 0; i < this.transactions.size(); i++) {
                fw.append(transactions.get(i) + "\n");
            }
        } finally {
            fw.close();
            System.out.println("Done! Saved all transactions");
        }
    }
}
