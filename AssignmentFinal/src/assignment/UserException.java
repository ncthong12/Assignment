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
public class UserException extends Exception {

    /**
     * CONSTRUCTOR create new UserException
     *
     * @param message
     */
    public UserException(String message) {
        super("UserException" + message);
    }
}
