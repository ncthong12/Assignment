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
 * @author Admin
 */
public class AdminManagement {

    private String A_FILE;              //file that stores the admins   
    private int numberOfAccount;        //total number of admins
    private ArrayList<Admin> admins;    //list of admins

    /**
     * CONSTRUCTOR
     *
     * @param A_FILE
     * @throws AdminException
     */
    public AdminManagement(String A_FILE) throws AdminException {
        if (A_FILE.equals("")) {
            throw new AdminException("The URL of data file can't be empty!");
        } else {
            //initiates attributes
            this.A_FILE = A_FILE;
            this.admins = new ArrayList<>();
            this.numberOfAccount = 0;
        }
    }

    /**
     * loads admin from file to list
     *
     * @throws IOException
     * @throws AdminException
     */
    public void loadAccounts() throws IOException, AdminException {
        File aFile = new File(A_FILE);
        //Checks if file exists
        if (!aFile.exists()) {
            aFile.createNewFile(); //If not, creates new file
            System.out.println(
                    "The data file does not exist. "
                    + "Creating new data file adminacc.txt... "
                    + "Done!");
            this.numberOfAccount = 0;   //New data file with 0 admins
        } else {
            //If file exists, load this file data
            System.out.print(
                    "\nThe data file " + A_FILE + " is found. "
                    + "Data of accounts are being loaded...");

            //Loads text file into buffer
            try (BufferedReader br = new BufferedReader(new FileReader(A_FILE))) { //type BufferedReader
                String line, aId, aPin;

                //Reads number of employees (first line in file)
                line = br.readLine();
                if (line != null) {
                    this.numberOfAccount = Integer.parseInt(line);
                } else {
                    this.numberOfAccount = 0;
                }

                for (int i = 0; i < this.numberOfAccount; i++) {
                    //Reads admin's information
                    aId = br.readLine();
                    aPin = br.readLine();
                    //Create new instance of Admin and adds to admins list
                    this.admins.add(new Admin(aId, aPin));
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
        FileWriter fw = new FileWriter(new File(A_FILE), false);    //type FileWriter, false means allow overwriting

        try {
            System.out.print(
                    "\nAccounts are being saved into data file " + A_FILE + "...");

            //Writes number of admins
            fw.append(String.valueOf(this.numberOfAccount) + "\n");

            for (int i = 0; i < this.numberOfAccount; i++) {
                //Inits admin's information
                String aId = this.admins.get(i).getaId();
                String aPin = this.admins.get(i).getaPin();

                //Writes admin's information into data file
                fw.append(aId + "\n");
                fw.append(aPin + "\n");

            }
        } finally {
            //Close file
            fw.close();
            System.out.println("Done! [" + this.numberOfAccount + " accounts]");
        }
    }

    /**
     * add a new admin
     *
     * @param aId
     * @param aPin
     * @throws AdminException
     */
    public void addAdmin(String aId, String aPin) throws AdminException {
        ++numberOfAccount;
        this.admins.add(new Admin(aId, aPin));
    }

    /**
     * check if the id pass in is duplicate
     *
     * @param aId
     * @return
     */
    public boolean isDuplicate(String aId) {
        for (Admin admin : admins) {
            if (admin.getaId().equals(aId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if the pin following the id is correct
     *
     * @param aId
     * @param aPin
     * @return
     */
    public boolean correctPin(String aId, String aPin) {
        for (Admin admin : admins) {
            if (admin.getaId().equals(aId)) {
                if (admin.getaPin().equals(aPin)) {
                    return true;
                }
            }
        }
        return false;
    }

}
