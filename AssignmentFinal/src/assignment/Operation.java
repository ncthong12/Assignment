/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Operation {

    public static Scanner input = new Scanner(System.in);
    private static AdminManagement am;
    private static UserManagement um;
    private static TransactionManagement tm;

    public static void FirstMenu() {
        System.out.println("****ATM****");
        System.out.println("1. Admin Login");
        System.out.println("2. User Login");
        System.out.println("3. Quit");
        System.out.println("Choose an option:");
    }

    public static void AdminMenu() {
        System.out.println("****ATM****");
        System.out.println("1. Creating new user account");
        System.out.println("2. Withdrawal report");
        System.out.println("3. Deposit report");
        System.out.println("4. Transfer report");
        System.out.println("5. Account report");
        System.out.println("6. Creating new admin account");
        System.out.println("7. Log out");
        System.out.println("Choose an option:");
    }

    public static void UserMenu() {
        System.out.println("****ATM****");
        System.out.println("1. Deposit");
        System.out.println("2. Withdrawal");
        System.out.println("3. Transfer money");
        System.out.println("4. Balance Enquiry");
        System.out.println("5. Change Password");
        System.out.println("6. Log out");
        System.out.println("Choose an option:");
    }

    /**
     *
     * @param args
     * @throws AdminException
     * @throws UserException
     */
    public static void main(String[] args) throws AdminException, UserException {
        try {
            am = new AdminManagement("C:\\Users\\Thong Nguyen\\Documents\\NetBeansProjects\\Assignment\\AssignmentFinal\\src\\data\\adminacc.txt"); //initialize AdminMan
            am.loadAccounts();  //load Admins
            um = new UserManagement("C:\\Users\\Thong Nguyen\\Documents\\NetBeansProjects\\Assignment\\AssignmentFinal\\src\\data\\useracc.txt"); //initialize UserMan
            um.loadAccounts();  //load Users
            tm = new TransactionManagement("C:\\Users\\Thong Nguyen\\Documents\\NetBeansProjects\\Assignment\\AssignmentFinal\\src\\data\\transactionacc.txt"); //initialize TransactionMan
            tm.loadTransactiotns(); //load Transactiotns
            String dateString = "";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            String choice;
            boolean exit = false, exit1, exit2;
            while (exit == false) {
                //display main menu
                FirstMenu();
                //allow user to choose a feature
                choice = input.nextLine();
                switch (choice) {
//----------1_ADMIN--------------------                
                    case "1": //func1
                        exit1 = false;
                        String id;
                        String pin;
                        boolean existed;
                        boolean correct;
                        //read admin id
                        do {
                            existed = false;
                            System.out.print("Please enter ID to login: ");
                            id = input.nextLine();
                            if (id.equals("")) {
                                System.out.println("Error: Admin's ID can't be empty!");
                            } else if (!id.matches("[0-9]{14}")) { //check for valid id
                                System.out.println("Error: Admin's ID can only be a 14 digits number!");
                            } else {
                                existed = am.isDuplicate(id);   //check for existence
                                if (!existed) {
                                    System.out.println("Sorry, this ID is not existed!");
                                }
                            }
                        } while (id.equals("") || !id.matches("[0-9]{14}") || !existed);
                        //read admin password
                        do {
                            correct = false;
                            System.out.print("Please enter Pin: ");
                            pin = input.nextLine();
                            if (pin.equals("")) {
                                System.out.println("Error: Pin can't be empty!");
                            } else if (!pin.matches("[0-9]{4}")) { //check for valid pin
                                System.out.println("Error: Pin can only be a 4 digits number!");
                            } else {
                                correct = am.correctPin(id, pin);   //check for correct pin base on id
                                if (!correct) {
                                    System.out.println("Wrong Pin nuber!");
                                }
                            }
                        } while (pin.equals("") || !pin.matches("[0-9]{4}") || !correct);
                        //going to sub-menu
                        while (exit1 == false) {
                            AdminMenu();
                            String choiceIn;
                            choiceIn = input.nextLine();
                            switch (choiceIn) {
//----------1.1_ADD USER---------------------                            
                                case "1":
                                    String uName,
                                     uPhone,
                                     uAddress,
                                     uGender;
                                    boolean duplicate,
                                     isvaliddate;
                                    System.out.println("****ATM [ADD NEW USER]****");
                                    //read user id
                                    do {
                                        duplicate = false;
                                        System.out.print("Please enter the ID of the new user: ");
                                        id = input.nextLine();
                                        if (id.equals("")) {
                                            System.out.println("Error: User's ID can't be empty!");
                                        } else if (!id.matches("[0-9]{14}")) { //check for valid id
                                            System.out.println("Error: User's ID can only be a 14 digits number!");
                                        } else {
                                            duplicate = um.isDuplicate(id); //check  for duplicated id
                                            if (duplicate) {
                                                System.out.println("Sorry, this ID is already existed!");
                                            }
                                        }
                                    } while (id.equals("") || !id.matches("[0-9]{14}") || duplicate);
                                    //read user password
                                    do {
                                        System.out.print("Please enter Pin for this user: ");
                                        pin = input.nextLine();
                                        if (pin.equals("")) {
                                            System.out.println("Error: Pin can't be empty!");
                                        } else if (!pin.matches("[0-9]{4}")) { //check for valid pin
                                            System.out.println("Error: Pin can only be a 4 digits number!");
                                        }
                                    } while (pin.equals("") || !pin.matches("[0-9]{4}"));
                                    //read user name
                                    do {
                                        System.out.print("Please enter the name of the user: ");
                                        uName = input.nextLine();
                                        if (uName.equals("")) {
                                            System.out.println("Error: User's name can't be empty!");
                                        } else if (!uName.matches("[a-zA-Z][a-zA-Z ,\\.]*")) { //check for valid name
                                            System.out.println("Error: User's name can only contain a letter at the beginning, followed by more letters, whitespace characters, commas, or dots!");
                                        }
                                    } while (uName.equals("") || !uName.matches("[a-zA-Z][a-zA-Z ,\\.]*"));
                                    //read user phone
                                    do {
                                        System.out.print("Please enter the phone number of the user: ");
                                        uPhone = input.nextLine();
                                        if (uPhone.length() != 10) { //check for length of phone
                                            System.out.println("Error: User's phone number must contain 10 digits!");
                                        } else if (!uPhone.matches("[0-9]+")) { //phone number should contain digits only
                                            System.out.println("Error: User's phone number must contain digits only!");
                                        }
                                    } while (uPhone.length() != 10 || !uPhone.matches("[0-9]+"));
                                    //read user address
                                    do {
                                        System.out.print("Please enter address: ");
                                        uAddress = input.nextLine();
                                        if (uAddress.equals("")) {
                                            System.out.println("Error: User's address can't be empty!");
                                        } else if (!uAddress.matches(".*[a-zA-Z].*")) {
                                            System.out.println("Error: User's address should contain at least one letter!");
                                        }
                                    } while (uAddress.equals("") || !uAddress.matches(".*[a-zA-Z].*"));
                                    //read user gender
                                    do {
                                        System.out.print("Please enter gender of the user: ");
                                        uGender = input.nextLine();
                                        if (uGender.equals("")) {
                                            System.out.println("Error: User's gender can't be empty!");
                                        } else if (!uGender.equalsIgnoreCase("male") && !uGender.equalsIgnoreCase("female")) {
                                            System.out.println("Error: User's gender must be male or female!");
                                        }
                                    } while ((!uGender.equalsIgnoreCase("male") && !uGender.equalsIgnoreCase("female")) || uGender.equals(""));
                                    um.addUser(id, pin, uName, uPhone, uAddress, uGender, 5.0); //add new user into list
                                    System.out.println("New user added!");
                                    break;
//---------1.2 WITHDRAWAL REPORT---------------                                
                                case "2":
                                    isvaliddate = false;
                                    System.out.println("****ATM [WITHDRAWAL REPORT]****");
                                    System.out.println("Enter date you want to search for(dd/MM/yyyy): ");
                                    do {
                                        try {   //try to read date
                                            dateString = input.nextLine();
                                            sdf.parse(dateString); // parse dateString into Date
                                            isvaliddate = true;
                                        } catch (ParseException ex) { //  invalid dateString
                                            System.out.println("Invalid date! Please try again");
                                        }
                                    } while (isvaliddate == false);
                                    tm.reportWithdrawal(dateString, um);    //show withdraw report
                                    break;
//---------1.3 DEPOSIT REPORT---------------                                
                                case "3":
                                    isvaliddate = false;
                                    System.out.println("****ATM [DEPOSIT REPORT]****");
                                    System.out.println("Enter date you want to search for(dd/MM/yyyy): ");
                                    do {
                                        try {   //try to read date
                                            dateString = input.nextLine();
                                            sdf.parse(dateString); // parse dateString into Date
                                            isvaliddate = true;
                                        } catch (ParseException ex) { //  invalid dateString
                                            System.out.println("Invalid date! Please try again");
                                        }
                                    } while (isvaliddate == false);
                                    tm.reportDeposit(dateString, um);    //show deposit report
                                    break;
//---------1.4 TRANSFER REPORT---------------                                 
                                case "4":
                                    isvaliddate = false;
                                    System.out.println("****ATM [TRANSFER REPORT]****");
                                    System.out.println("Enter date you want to search for(dd/MM/yyyy): ");
                                    do {
                                        try {   //try to read date
                                            dateString = input.nextLine();
                                            sdf.parse(dateString); // parse dateString into Date
                                            isvaliddate = true;
                                        } catch (ParseException ex) { // invalid dateString
                                            System.out.println("Invalid date! Please try again");
                                        }
                                    } while (isvaliddate == false);
                                    tm.reportTransfer(dateString, um);  //show transfer report
                                    break;
//---------1.5 ACCOUNT REPORT---------------                               
                                case "5":
                                    System.out.println("****ATM [ACCOUNT REPORT]****");
                                    um.showUsers(); //show all users
                                    break;
//---------1.6 ADD ADMIN---------------                                 
                                case "6":
                                    System.out.println("****ATM [ADD NEW ADMIN]****");
                                    //read admin id
                                    do {
                                        duplicate = false;
                                        System.out.print("Please enter the ID of the new admin: ");
                                        id = input.nextLine();
                                        if (id.equals("")) {
                                            System.out.println("Error: Admin's ID can't be empty!");
                                        } else if (!id.matches("[0-9]{14}")) { //check for valid id
                                            System.out.println("Error: Admin's ID can only be a 14 digits number!");
                                        } else {
                                            duplicate = am.isDuplicate(id); //check  for duplicated id
                                            if (duplicate) {
                                                System.out.println("Sorry, this ID is already existed!");
                                            }
                                        }
                                    } while (id.equals("") || !id.matches("[0-9]{14}") || duplicate);
                                    //read admin password
                                    do {
                                        System.out.print("Please enter Pin for this admin: ");
                                        pin = input.nextLine();
                                        if (pin.equals("")) {
                                            System.out.println("Error: Pin can't be empty!");
                                        } else if (!pin.matches("[0-9]{4}")) { //check for valid pin
                                            System.out.println("Error: Pin can only be a 4 digits number!");
                                        }
                                    } while (pin.equals("") || !pin.matches("[0-9]{4}"));
                                    am.addAdmin(id, pin);   //add new admin into list
                                    System.out.println("New admin added!");
                                    break;
//---------1.7 Go back---------------                                 
                                case "7":
                                    exit1 = true;   //exit from admin menu
                                    System.out.println("\nGoing back to the main menu...");
                                    break;
                                default:    //avoid wrong input
                                    System.out.println("Please choose the right option!");
                            }
                        }
                        break;
//----------2_USER--------------------                    
                    case "2":
                        String tempTrans;
                        double transMoney;
                        String transId;
                        exit2 = false;
                        //read user id
                        do {
                            existed = false;
                            System.out.print("Enter your card number: ");
                            id = input.nextLine();
                            if (id.equals("")) {
                                System.out.println("Error: User's ID can't be empty!");
                            } else if (!id.matches("[0-9]{14}")) { //check for valid id
                                System.out.println("Error: User's ID can only be a 14 digits number!");
                            } else {
                                existed = um.isDuplicate(id);   //check for existence
                                if (!existed) {
                                    System.out.println("Sorry, this ID is not existed!");
                                }
                            }
                        } while (id.equals("") || !id.matches("[0-9]{14}") || !existed);
                        //read user password
                        do {
                            correct = false;
                            System.out.print("Please enter Pin: ");
                            pin = input.nextLine();
                            if (pin.equals("")) {
                                System.out.println("Error: Pin can't be empty!");
                            } else if (!pin.matches("[0-9]{4}")) { //check for valid pin
                                System.out.println("Error: Pin can only be a 4 digits number!");
                            } else {
                                correct = um.correctPin(id, pin);   //check for correct pin base on id
                                if (!correct) {
                                    System.out.println("Wrong Pin number!");
                                }
                            }
                        } while (pin.equals("") || !pin.matches("[0-9]{4}") || !correct);
                        //going to sub-menu
                        while (exit2 == false) {
                            UserMenu();
                            String choiceIn;
                            choiceIn = input.nextLine();
                            switch (choiceIn) {
//----------2.1 DEPOSIT-----------                            
                                case "1":
                                    System.out.println("***ATM [DEPOSIT]***");
                                    //read amount
                                    do {
                                        System.out.println("How much do you want to deposit?");
                                        tempTrans = input.nextLine();
                                        if (tempTrans.equals("")) {
                                            System.out.println("Error: deposit amount can't be empty");
                                        } else if (!(tempTrans.matches("[0-9]+(\\.[0-9]+)?"))) {    //check for valid amount
                                            System.out.println("Error: deposit amount must be number");
                                        } else if (Double.parseDouble(tempTrans) < 5) {     //at least 5 for each transaction
                                            System.out.println("Error: deposit amount must at least 5$");
                                        }
                                    } while (tempTrans.equals("") || !(tempTrans.matches("[0-9]+(\\.[0-9]+)?")) || Double.parseDouble(tempTrans) < 5);
                                    transMoney = Double.parseDouble(tempTrans); //convert the amount into double
                                    if (tm.blockTransactionTimes(id, "deposit")) {  //check for maximum number of transactions
                                        System.out.println("You have reach maximum number of deposits. Transfer fail!");
                                        break;
                                    } else if (tm.blockTransactionAmount(id, "deposit", transMoney)) {  //check for maximum amount of transactions
                                        System.out.println("You have reach maximum amount of deposit. Transfer fail!");
                                        break;
                                    }
                                    System.out.println("Done deposit " + transMoney + "$. Please check your balance!");
                                    um.deposit(id, transMoney);     //perform deposit
                                    tm.addTransaction("Account ID " + id + " has deposit " + transMoney + "$", um.getBalance(id), 0);   //add transaction record to list
                                    break;
//----------2.2 WITHDRAWAL-----------                                 
                                case "2":
                                    System.out.println("***ATM [WITHDRAWAL]***");
                                    //read amount
                                    do {
                                        System.out.println("How much do you want to withdraw?");
                                        tempTrans = input.nextLine();
                                        if (tempTrans.equals("")) {
                                            System.out.println("Error: withdraw amount can't be empty");
                                        } else if (!(tempTrans.matches("[0-9]+(\\.[0-9]+)?"))) {    //check for valid amount
                                            System.out.println("Error: withdraw amount must be number");
                                        } else if (Double.parseDouble(tempTrans) < 5) {     //at least 5 for each transaction
                                            System.out.println("Error: withdraw amount must at least 5$");
                                        }
                                    } while (tempTrans.equals("") || !(tempTrans.matches("[0-9]+(\\.[0-9]+)?")) || Double.parseDouble(tempTrans) < 5);
                                    transMoney = Double.parseDouble(tempTrans); //convert the amount into double
                                    if (transMoney > um.getBalance(id)) {   //check for balance left
                                        System.out.println("Balance is not enough. Transfer fail!");
                                        break;
                                    } else if (tm.blockTransactionTimes(id, "withdraw")) {  //check for maximum number of transactions
                                        System.out.println("You have reach maximum number of withdrawals. Transfer fail!");
                                        break;
                                    } else if (tm.blockTransactionAmount(id, "withdraw", transMoney)) { //check for maximum amount of transactions
                                        System.out.println("You have reach maximum amount of withdrawal. Transfer fail!");
                                        break;
                                    }
                                    System.out.println("Done withdraw " + transMoney + "$. Please check your balance!");
                                    um.withdraw(id, transMoney);    //perform withdraw
                                    tm.addTransaction("Account ID " + id + " has withdraw " + transMoney + "$", um.getBalance(id), 0);  //add transaction record to list
                                    break;
//----------2.3 TRANSFER------------                                
                                case "3":
                                    System.out.println("****ATM [TRANSFER]****");
                                    //read amount
                                    do {
                                        System.out.println("How much do you want to transfer?");
                                        tempTrans = input.nextLine();
                                        if (tempTrans.equals("")) {
                                            System.out.println("Error: Pin can't be empty!");
                                        } else if (!tempTrans.matches("[0-9]+(\\.[0-9]+)?")) { //check for valid amount
                                            System.out.println("Error: Money must be a number!");
                                        } else if (Double.parseDouble(tempTrans) < 5) {     //at least 5 for each transaction
                                            System.out.println("Error: Transfer amount must at least 5$!");
                                        }
                                    } while (tempTrans.equals("") || !tempTrans.matches("[0-9]+(\\.[0-9]+)?") || Double.parseDouble(tempTrans) < 5);
                                    transMoney = Double.parseDouble(tempTrans);     //convert the amount into double
                                    if (transMoney > um.getBalance(id)) {   //check for balance left
                                        System.out.println("Not enough money. Transfer fail!");
                                        break;
                                    }
                                    //read transfer id
                                    do {
                                        existed = false;
                                        System.out.println("Enter ID of user you want to transfer to: ");
                                        transId = input.nextLine();
                                        if (transId.equals("")) {
                                            System.out.println("Error: User's ID can't be empty!");
                                        } else if (!transId.matches("[0-9]{14}")) { //check for valid id
                                            System.out.println("Error: User's ID can only be a 14 digits number!");
                                        } else {    //check id not exist or equals current account
                                            if (transId.equals(id)) {
                                                System.out.println("Cannot transfer to the current account!");
                                            } else {
                                                existed = um.isDuplicate(transId);
                                                if (!existed) {
                                                    System.out.println("Sorry, this ID is not existed!");
                                                }
                                            }
                                        }
                                    } while (transId.equals("") || !transId.matches("[0-9]{14}"));
                                    if (existed) {
                                        um.transfer(id, transId, transMoney);   //perform transfer
                                        System.out.println("Done transfer " + transMoney + "$ to id " + transId);
                                        //add transaction record to list
                                        tm.addTransaction("Account ID " + id + " has transfer " + transMoney + "$ to account ID " + transId, um.getBalance(id), um.getBalance(transId));
                                    } else {
                                        System.out.println("Transfer fail!");
                                    }
                                    break;
//----------2.4 BALANCE ENQUIRY------------                                
                                case "4":
                                    System.out.println("****ATM [BALANCE ENQUIRY]****");
                                    System.out.println("\nHere is your account balance: " + um.getBalance(id) + "$");
                                    System.out.println("Transaction history:");
                                    tm.showByAccount(id);   //show transactions of current account
                                    break;
//----------2.5 CHANGE PASSWORD------------                                 
                                case "5":
                                    String newPin1,
                                     newPin2;
                                    System.out.println("****ATM [CHANGE PASSWORD]****");
                                    //read old password
                                    do {
                                        correct = false;
                                        System.out.print("Please enter the old password(current pin): ");
                                        pin = input.nextLine();
                                        if (pin.equals("")) {
                                            System.out.println("Error: Pin can't be empty!");
                                        } else if (!pin.matches("[0-9]{4}")) { //check for valid pin
                                            System.out.println("Error: Pin can only be a 4 digits number!");
                                        } else {    //check for correct pin base on id
                                            correct = um.correctPin(id, pin);
                                            if (!correct) {
                                                System.out.println("Wrong Pin number!");
                                            }
                                        }
                                    } while (pin.equals("") || !pin.matches("[0-9]{4}") || !correct);
                                    //read new password
                                    do {
                                        System.out.print("Please enter the new password: ");
                                        newPin1 = input.nextLine();
                                        if (newPin1.equals("")) {
                                            System.out.println("Error: Pin can't be empty!");
                                        } else if (!newPin1.matches("[0-9]{4}")) { //check for valid pin
                                            System.out.println("Error: Pin can only be a 4 digits number!");
                                        } else if (newPin1.equals(pin)) {   //check for diffrence
                                            System.out.println("Error: The new password must diffrent from the old one!");
                                        }
                                    } while (newPin1.equals("") || !newPin1.matches("[0-9]{4}") || newPin1.equals(pin));
                                    //read confirm password
                                    do {
                                        System.out.print("Please confirm the new password: ");
                                        newPin2 = input.nextLine();
                                        if (newPin2.equals("")) {
                                            System.out.println("Error: Pin can't be empty!");
                                        } else if (!newPin2.matches("[0-9]{4}")) { //check for valid pin
                                            System.out.println("Error: Pin can only be a 4 digits number!");
                                        } else if (!newPin2.equals(newPin1)) {  //check equal
                                            System.out.println("Error: The confirmation password does not match!");
                                        }
                                    } while (newPin2.equals("") || !newPin2.matches("[0-9]{4}") || !newPin2.equals(newPin1));
                                    um.changePin(id, newPin2);  //perform change pin
                                    exit2 = true;
                                    System.out.println("Password changed\nGoing back to the main menu...");
                                    break;
//----------2.6 Go back------------                                
                                case "6":
                                    exit2 = true;   //exit from user menu
                                    System.out.println("\nGoing back to the main menu...");
                                    break;
                                default:    //avoid wrong input
                                    System.out.println("Please choose the right option!");
                            }
                        }
                        break;
//----------3_QUIT--------------------                     
                    case "3":
                        System.out.println("Exited. Thanks for using!");
                        exit = true;
                        break;
                    default:
                        System.out.println("Please choose the right option!");
                }
            }
        } catch (Exception ex) {    //any other exception while running the program
            System.out.println(ex.getMessage());
        } finally {  //save the file no matter what happens
            try {
                am.saveAccounts();
            } catch (Exception ex) {
                System.out.println("Can't save!");
            }
            try {
                um.saveAccounts();
            } catch (Exception ex) {
                System.out.println("Can't save!");
            }
            try {
                tm.saveTransactions();
            } catch (Exception ex) {
                System.out.println("Can't save!");
            }
        }
    }
}
