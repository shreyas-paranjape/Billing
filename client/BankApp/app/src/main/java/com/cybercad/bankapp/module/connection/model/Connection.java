//class to store connection fields
package com.cybercad.bankapp.module.connection.model;

import com.orm.SugarRecord;

public class Connection extends SugarRecord<Connection> {

    private String conId;
    private String firstName;
    private String conCode;
    private String lastName;
    private String address;
    private double curBal;

    public Connection() {

    }

    public Connection(String id, String fn, String ln, String code, String add, double cb) {
        this.conId = id;
        this.firstName = fn;
        this.conCode = code;
        this.lastName = ln;
        this.address = add;
        this.curBal = cb;
    }

    public String getConId() {
        return conId;
    }

    public void setConId(String conId) {
        this.conId = conId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getConCode() {
        return conCode;
    }

    public void setConCode(String conCode) {
        this.conCode = conCode;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getCurBal() {
        return curBal;
    }

    public void setCurBal(double curBal) {
        this.curBal = curBal;
    }
}
