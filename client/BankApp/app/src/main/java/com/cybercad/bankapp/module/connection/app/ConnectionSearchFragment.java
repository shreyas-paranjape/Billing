package com.cybercad.bankapp.module.connection.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.cybercad.bankapp.R;
import com.cybercad.bankapp.module.connection.model.Connection;
import com.cybercad.bankapp.module.connection.widget.ConnectionAdapter;
import java.util.ArrayList;
import de.greenrobot.event.EventBus;

public class ConnectionSearchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_conn_search, container, false);
        final Button b = (Button) v.findViewById(R.id.button);
        final EditText et = (EditText) v.findViewById(R.id.edittext);
        final ConnectionAdapter la = new ConnectionAdapter(getActivity(), new ArrayList<Connection>());
        final ListView lv = (ListView) v.findViewById(R.id.lv_conn_search);
        lv.setAdapter(la);

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String val = et.getText().toString();
                //String[] split = val.split("\\s+");
                if (!("".equals(val))) {
                    String whereClause = "con_id = ? or con_code = ? or first_name = ?  " ;
                    la.clear();
                    la.addAll(
                            Connection.find(Connection.class, whereClause, val,val,val));
                }
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final TextView connIDView = (TextView) view.findViewById(R.id.tview1);
                String connID = connIDView.getText().toString();
                EventBus.getDefault().post(new ConnectionSelectedEvent(connID));
            }
        });
        return v;
    }

    public static class ConnectionSelectedEvent {
        private final String connId;

        public ConnectionSelectedEvent(String connId) {
            this.connId = connId;
        }

        public String getConnId() {
            return connId;
        }
    }
}







/* Connection c1 = new Connection("123", "Francis","Mascarenhas","001","Verna-Goa", 450);
        Connection c2 = new Connection("456", "Varsha","Sahakari","002","Near xyz temple,Ponda-Goa", 1000);
        Connection c3 = new Connection("789", "Priya","Bhat","003","Ferns-Ville colony,Miramar-Goa", 350);
        Connection c4 = new Connection("135", "Ryan","Dias","004","Near community hall,Taleigo,Goa", 700);
        Connection c5 = new Connection("666", "Priyanka","Nayak","005","Merces appts,Firguem Bhat,Merces-Wadi,Goa", 350);
        Connection c6 = new Connection("246", "Alisha","Elekar","006","Journalist colony,near Sai service,Porvorim,Goa", 786);
        Connection c7 = new Connection("345", "Ruchita","Elekar","007","Journalist colony,near Sai service,Porvorim,Goa", 786);
        Connection c8 = new Connection("111", "Priya","Bhat","008","Ferns-Ville colony,Miramar-Goa", 350);
        Connection c9 = new Connection("222", "Sneha","Nayak","009","Ferns-Ville colony,Miramar-Goa", 350);
        Connection c10 = new Connection("333", "Ryan","Dias","010","opposite Bank of Maharashtra,Dada Vaidya road,Taleigo,Goa", 800);
        Connection c11 = new Connection("444", "Nadia","DominiqueGouveia","011","opposite Alfran Plaza,Gouveia chambers,Panaji market,Panaji,Goa", 800);
        Connection c12 = new Connection("555", "Varsha","Naik","012","Journalist colony,near Sai service,Porvorim,Goa", 567);
        Connection c13 = new Connection("777", "Alisha","Kalangutkar","013","Journalist colony,near Sai service,Porvorim,Goa", 900);
        Connection c14 = new Connection("888", "Alisha","Fernandes","014","Journalist colony,near Sai service,Porvorim,Goa", 450);
        Connection c15 = new Connection("999", "Alisha","Kavlekar","015","Journalist colony,near Sai service,Porvorim,Goa", 250);
        Connection c16 = new Connection("121", "Alisha","Dsouza","016","Journalist colony,near Sai service,Porvorim,Goa", 277);
        Connection c17 = new Connection("131", "Alisha","Alfonso","017","Journalist colony,near Sai service,Porvorim,Goa",343);
        con=new ArrayList<>();
        con.add(c1);
        con.add(c2);
        con.add(c3);
        con.add(c4);
        con.add(c5);
        con.add(c6);
        con.add(c7);
        con.add(c8);
        con.add(c9);
        con.add(c10);
        con.add(c11);
        con.add(c12);
        con.add(c13);
        con.add(c14);
        con.add(c15);
        con.add(c16);
        con.add(c17);*/

/*for (Connection i : con) {

                        la.addAll(connSearchResultList);
                        if (val.equals(i.getConId()) || val.equalsIgnoreCase(i.getFirstName())
                                || val.equals(i.getConCode())
                                || val.equalsIgnoreCase(i.getLastName())
                                || (split[0].equalsIgnoreCase(
                                i.getFirstName()) && split[1].equalsIgnoreCase(
                                i.getLastName()))) {
                            la.add(i);
                            et.setText("");
                        }
                    }*/