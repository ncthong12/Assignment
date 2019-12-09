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
public class Admin {

    private String aId;         //admin's id
    private String aPin;        //admin's pin

    /**
     * CONSTRUCTOR
     *
     * @param aId
     * @param aPin
     * @throws AdminException
     */
    public Admin(String aId, String aPin) throws AdminException {
        this.setaId(aId);
        this.setaPin(aPin);
    }
//GET-SET METHODS START HERE

    /**
     *
     * @return aId
     */
    public String getaId() {
        return aId;
    }

    /**
     *
     * @return aPin
     */
    public String getaPin() {
        return aPin;
    }

    /**
     *
     * @param aId
     * @throws AdminException
     */
    public void setaId(String aId) throws AdminException {
        if (aId.length() != 14) {
            throw new AdminException("ID must contain 14 digits!");
        } else {
            this.aId = aId;
        }
    }

    /**
     *
     * @param aPin
     * @throws AdminException
     */
    public void setaPin(String aPin) throws AdminException {
        if (aId.equals("")) {
            throw new AdminException("PIN must contain 4 digits!");
        } else {
            this.aPin = aPin;
        }
    }
//GET-SET METHODS END HERE
}
