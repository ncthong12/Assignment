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
import java.util.ArrayList;

/**
 *
 * @author Thong Nguyen
 */
public class UserManagement {

    private String U_FILE;          //file that stores the users
    private int numberOfAccount;    //total number of users
    private ArrayList<User> users;  //list of users

    /**
     * CONSTRUCTOR
     *
     * @param U_FILE
     * @throws UserException
     */
    public UserManagement(String U_FILE) throws UserException {
        if (U_FILE.equals("")) {
            throw new UserException("The URL of data file can't be empty!");
        } else {
            //initiates attributes
            this.U_FILE = U_FILE;
            this.users = new ArrayList<>();
            this.numberOfAccount = 0;
        }
    }

    /**
     * loads user from file to list
     *
     * @throws IOException
     * @throws UserException
     */
    public void loadAccounts() throws IOException, UserException {
        File uFile = new File(U_FILE);
        //Checks if file exists
        if (!uFile.exists()) {
            uFile.createNewFile(); //If not, creates new file
            System.out.println(
                    "The data file does not exist. "
                    + "Creating new data file useracc.txt... "
                    + "Done!");
            this.numberOfAccount = 0;   //New data file with 0 user
        } else {
            //If file exists, load this data file
            System.out.print(
                    "\nThe data file " + U_FILE + " is found. "
                    + "Data of accounts are being loaded...");

            //Loads text file into buffer
            try (BufferedReader br = new BufferedReader(new FileReader(U_FILE))) { //type BufferedReader
                String line, uId, uPin, uName, uPhone, uAddress, uGender, uBalance;

                //Reads number of users (first line in file)
                line = br.readLine();
                if (line != null) {
                    this.numberOfAccount = Integer.parseInt(line);
                } else {
                    this.numberOfAccount = 0;
                }

                for (int i = 0; i < this.numberOfAccount; i++) {
                    //Reads user's information
                    uId = br.readLine();
                    uPin = br.readLine();
                    uName = br.readLine();
                    uPhone = br.readLine();
                    uAddress = br.readLine();
                    uGender = br.readLine();
                    uBalance = br.readLine();
                    //Create new instance of User and adds to users list
                    this.users.add(new User(uId, uPin, uName, uPhone, uAddress, uGender, Double.parseDouble(uBalance)));
                }
            }
            System.out.println("Done! [" + this.numberOfAccount + " accounts]");
        }
    }

    /**
     * save array to file when quitting
     *
     * @throws IOException
     */
    public void saveAccounts() throws IOException {
        //Overwrite data file
        FileWriter fw = new FileWriter(new File(U_FILE), false);

        try {
            System.out.print(
                    "\nAccounts are being saved into data file " + U_FILE + "...");

            //Writes number of users
            fw.append(String.valueOf(this.numberOfAccount) + "\n");

            for (int i = 0; i < this.numberOfAccount; i++) {
                //Inits user's information
                String uId = this.users.get(i).getuId();
                String uPin = this.users.get(i).getuPin();
                String uName = this.users.get(i).getuName();
                String uPhone = this.users.get(i).getuPhone();
                String uAddress = this.users.get(i).getuAddress();
                String uGender = this.users.get(i).getuGender();
                double uBalance = this.users.get(i).getuBalance();

                //Writes user's information into data file
                fw.append(uId + "\n");
                fw.append(uPin + "\n");
                fw.append(uName + "\n");
                fw.append(uPhone + "\n");
                fw.append(uAddress + "\n");
                fw.append(uGender + "\n");
                fw.append(uBalance + "\n");
            }
        } finally {
            //Close file
            fw.close();
            System.out.println("Done! [" + this.numberOfAccount + " accounts]");
        }
    }

    /**
     * add a new user
     *
     * @param uId
     * @param uPin
     * @param uName
     * @param uPhone
     * @param uAddress
     * @param uGender
     * @param uBalance
     * @throws UserException
     */
    public void addUser(String uId, String uPin, String uName, String uPhone, String uAddress, String uGender, double uBalance) throws UserException {
        ++numberOfAccount;
        this.users.add(new User(uId, uPin, uName, uPhone, uAddress, uGender, uBalance));
    }

    /**
     * get balance of user that have the passing id
     *
     * @param uId
     * @return
     */
    public double getBalance(String uId) {
        for (User user : users) {
            if (user.getuId().equals(uId)) {
                return user.getuBalance();
            }
        }
        return 0;
    }

    /**
     * set new balance by getting the current balance minus transMoney
     *
     * @param uId
     * @param transMoney
     * @throws UserException
     */
    public void withdraw(String uId, double transMoney) throws UserException {
        for (User user : users) {
            if (user.getuId().equals(uId)) {
                user.setuBalance(user.getuBalance() - transMoney);
            }
        }
    }

    /**
     * set new balance by getting the current balance plus transMoney
     *
     * @param uId
     * @param transMoney
     * @throws UserException
     */
    public void deposit(String uId, double transMoney) throws UserException {
        for (User user : users) {
            if (user.getuId().equals(uId)) {
                user.setuBalance(user.getuBalance() + transMoney);
            }
        }
    }

    /**
     * set new balance for user uId by adding transMoney and user transId by
     * minus transMoney
     *
     * @param uId
     * @param transId
     * @param transMoney
     * @throws UserException
     */
    public void transfer(String uId, String transId, double transMoney) throws UserException {
        for (User user : users) {
            if (user.getuId().equals(uId)) {
                user.setuBalance(user.getuBalance() - transMoney);
            }
            if (user.getuId().equals(transId)) {
                user.setuBalance(user.getuBalance() + transMoney);
            }
        }
    }

    /**
     * show all user in list
     */
    public void showUsers() {
        for (User user : users) {
            user.show();
            System.out.println("");
        }
    }

    /**
     * get name of user that have the passing id
     *
     * @param uId
     * @return
     */
    public String getName(String uId) {
        for (User user : users) {
            if (user.getuId().equals(uId)) {
                return user.getuName();
            }
        }
        return "";
    }

    /**
     * check if the passing id is equal to any user id in list
     *
     * @param uId
     * @return
     */
    public boolean isDuplicate(String uId) {
        for (User user : users) {
            if (user.getuId().equals(uId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if the passing pin is correct with the pin of the user that have the
     * passing id
     *
     * @param uId
     * @param uPin
     * @return
     */
    public boolean correctPin(String uId, String uPin) {
        for (User user : users) {
            if (user.getuId().equals(uId)) {
                if (user.getuPin().equals(uPin)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * set new pin for user that have the passing id
     *
     * @param uId
     * @param newPin
     * @throws UserException
     */
    public void changePin(String uId, String newPin) throws UserException {
        for (User user : users) {
            if (user.getuId().equals(uId)) {
                user.setuPin(newPin);
            }
        }
    }
}
