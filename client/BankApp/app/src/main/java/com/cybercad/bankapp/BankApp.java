package com.cybercad.bankapp;

import com.cybercad.bankapp.module.connection.model.Connection;
import com.orm.SugarApp;
public class BankApp extends SugarApp {

    @Override
    public void onCreate() {
        super.onCreate();
        initDemoData();
    }

    private void initDemoData() {
        Connection c1 = new Connection("123", "Francis", "Mascarenhas", "001", "Verna-Goa", 450);
        Connection c2 = new Connection("456", "Varsha", "Sahakari", "002", "Near xyz temple,Ponda-Goa", 1000);
        Connection c3 = new Connection("789", "Priya", "Bhat", "003", "Ferns-Ville colony,Miramar-Goa", 350);
        Connection c4 = new Connection("135", "Ryan", "Dias", "004", "Near community hall,Taleigo,Goa", 700);
        Connection c5 = new Connection("666", "Priyanka", "Nayak", "005", "Merces appts,Firguem Bhat,Merces-Wadi,Goa", 350);
        Connection c6 = new Connection("246", "Alisha", "Elekar", "006", "Journalist colony,near Sai service,Porvorim,Goa", 786);
        Connection c7 = new Connection("345", "Ruchita", "Elekar", "007", "Journalist colony,near Sai service,Porvorim,Goa", 786);
        Connection c8 = new Connection("111", "Priya", "Bhat", "008", "Ferns-Ville colony,Miramar-Goa", 350);
        Connection c9 = new Connection("222", "Sneha", "Nayak", "009", "Ferns-Ville colony,Miramar-Goa", 350);
        Connection c10 = new Connection("333", "Ryan", "Dias", "010", "opposite Bank of Maharashtra,Dada Vaidya road,Taleigo,Goa", 800);
        Connection c11 = new Connection("444", "Nadia", "DominiqueGouveia", "011", "opposite Alfran Plaza,Gouveia chambers,Panaji market,Panaji,Goa", 800);
        Connection c12 = new Connection("555", "Varsha", "Naik", "012", "Journalist colony,near Sai service,Porvorim,Goa", 567);
        Connection c13 = new Connection("777", "Alisha", "Kalangutkar", "013", "Journalist colony,near Sai service,Porvorim,Goa", 900);
        Connection c14 = new Connection("888", "Alisha", "Fernandes", "014", "Journalist colony,near Sai service,Porvorim,Goa", 450);
        Connection c15 = new Connection("999", "Alisha", "Kavlekar", "015", "Journalist colony,near Sai service,Porvorim,Goa", 250);
        Connection c16 = new Connection("121", "Alisha", "Dsouza", "016", "Journalist colony,near Sai service,Porvorim,Goa", 277);
        Connection c17 = new Connection("131", "Alisha", "Alfonso", "017", "Journalist colony,near Sai service,Porvorim,Goa", 343);
        c1.save();
        c2.save();
        c3.save();
        c4.save();
        c5.save();
        c6.save();
        c7.save();
        c8.save();
        c9.save();
        c10.save();
        c11.save();
        c12.save();
        c13.save();
        c14.save();
        c15.save();
        c16.save();
        c17.save();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
