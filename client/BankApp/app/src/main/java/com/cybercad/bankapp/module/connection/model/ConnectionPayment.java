package com.cybercad.bankapp.module.connection.model;

import com.orm.SugarRecord;
import java.util.Date;

public class ConnectionPayment extends SugarRecord<ConnectionPayment> {

    private Connection connection;
    private Date date;
    private double amount;

    public ConnectionPayment(){
    }
    public ConnectionPayment(Connection connection,Date date, double amount){
        this.connection=connection;
        this.date=date;
        this.amount=amount;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
