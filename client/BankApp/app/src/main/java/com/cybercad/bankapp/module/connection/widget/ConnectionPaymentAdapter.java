package com.cybercad.bankapp.module.connection.widget;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.cybercad.bankapp.R;
import com.cybercad.bankapp.module.connection.model.Connection;
import com.cybercad.bankapp.module.connection.model.ConnectionPayment;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ConnectionPaymentAdapter extends ArrayAdapter<ConnectionPayment> {
    public ConnectionPaymentAdapter(Activity context, List<ConnectionPayment> conPayment) {
       super(context, R.layout.fragment_report_detail_list, conPayment);
   }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.fragment_report_detail_list, parent, false);
        }

        TextView conId= (TextView) convertView.findViewById(R.id.textView1);
        TextView conCode= (TextView) convertView.findViewById(R.id.textView2);
        TextView paidAmt= (TextView) convertView.findViewById(R.id.textView3);
        TextView paidDate= (TextView) convertView.findViewById(R.id.textView4);

        ConnectionPayment cp=getItem(position);
        Connection connection=cp.getConnection();
        conId.setText(connection.getConId());
        conCode.setText(connection.getConCode());
        double amount=cp.getAmount();
        paidAmt.setText(String.valueOf(amount));
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yy", Locale.US);
        String date=dateFormat.format(cp.getDate());
        paidDate.setText(date);
        return convertView;
    }
}
