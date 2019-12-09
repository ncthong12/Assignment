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
public class AdminException extends Exception {

    /**
     * CONSTRUCTOR create new AdminException
     *
     * @param message
     */
    public AdminException(String message) {
        super("AdminException" + message);
    }
}
