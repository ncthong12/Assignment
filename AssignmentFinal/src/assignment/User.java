/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

/**
 *
 * @author Thong Nguyen
 */
public class User {

    private String uId;         //user's id
    private String uPin;        //user's pin
    private String uName;       //user's name
    private String uPhone;      //user's phone
    private String uAddress;    //user's address
    private String uGender;     //user's gender
    private double uBalance;    //user's balance

    /**
     * CONSTRUCTOR
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
    public User(String uId, String uPin, String uName, String uPhone, String uAddress, String uGender, double uBalance) throws UserException {
        this.setuId(uId);
        this.setuPin(uPin);
        this.setuName(uName);
        this.setuPhone(uPhone);
        this.setuAddress(uAddress);
        this.setuGender(uGender);
        this.setuBalance(uBalance);
    }
//GET-SET METHODS START HERE

    /**
     *
     * @return uBalanc
     */
    public double getuBalance() {
        return uBalance;
    }

    /**
     *
     * @param uBalance
     * @throws UserException
     */
    public void setuBalance(double uBalance) throws UserException {
        if (uBalance < 0) {
            throw new UserException("Balance must not less than 0$!");
        } else {
            this.uBalance = uBalance;
        }
    }

    /**
     *
     * @param uId
     * @throws UserException
     */
    public void setuId(String uId) throws UserException {
        if (uId.length() != 14) {
            throw new UserException("ID must contain 14 digits!");
        } else {
            this.uId = uId;
        }
    }

    /**
     *
     * @param uPin
     * @throws UserException
     */
    public void setuPin(String uPin) throws UserException {
        if (uPin.length() != 4) {
            throw new UserException("PIN must contain 4 digits!");
        } else {
            this.uPin = uPin;
        }
    }

    /**
     *
     * @param uName
     * @throws UserException
     */
    public void setuName(String uName) throws UserException {
        if (uName.equals("")) {
            throw new UserException("User's name must not be empty!");
        } else {
            this.uName = uName;
        }
    }

    /**
     *
     * @param uPhone
     * @throws UserException
     */
    public void setuPhone(String uPhone) throws UserException {
        if (uPhone.length() != 10) {
            throw new UserException("User's phone number must contain 10 numbers!");
        } else {
            this.uPhone = uPhone;
        }
    }

    /**
     *
     * @param uAddress
     * @throws UserException
     */
    public void setuAddress(String uAddress) throws UserException {
        if (uAddress.equals("")) {
            throw new UserException("User's address must not be empty!");
        } else {
            this.uAddress = uAddress;
        }
    }

    /**
     *
     * @param uGender
     * @throws UserException
     */
    public void setuGender(String uGender) throws UserException {
        if (!uGender.equalsIgnoreCase("male") && !uGender.equalsIgnoreCase("female")) {
            throw new UserException("User's gender must be male or female!");
        } else {
            this.uGender = uGender;
        }
    }

    /**
     *
     * @return
     */
    public String getuId() {
        return uId;
    }

    /**
     *
     * @return
     */
    public String getuName() {
        return uName;
    }

    /**
     *
     * @return
     */
    public String getuPhone() {
        return uPhone;
    }

    /**
     *
     * @return
     */
    public String getuAddress() {
        return uAddress;
    }

    /**
     *
     * @return
     */
    public String getuGender() {
        return uGender;
    }

    /**
     *
     * @return
     */
    public String getuPin() {
        return uPin;
    }
//GET-SET METHODS END HERE

    /**
     * show user's information
     */
    public void show() {
        System.out.println("ID number: " + this.uId);
        System.out.println("Password: " + this.uPin);
        System.out.println("Owner: " + this.uName);
        System.out.println("Gender: " + this.uGender);
        System.out.println("Phone: " + this.uPhone);
        System.out.println("Address: " + this.uAddress);
        System.out.println("Current Balance: " + this.uBalance + "$");
    }

}
