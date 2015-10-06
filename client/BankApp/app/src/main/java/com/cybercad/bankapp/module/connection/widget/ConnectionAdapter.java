//custom list to display connection fields
package com.cybercad.bankapp.module.connection.widget;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.cybercad.bankapp.R;
import com.cybercad.bankapp.module.connection.model.Connection;
import java.util.List;

public class ConnectionAdapter extends ArrayAdapter<Connection> {

    public ConnectionAdapter(Activity context, List<Connection> conn) {
        super(context, R.layout.item_conn_search, conn);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_conn_search, parent, false);
        }

        TextView conId = (TextView) convertView.findViewById(R.id.tview1);
        TextView name = (TextView) convertView.findViewById(R.id.tview2);
        TextView amt = (TextView) convertView.findViewById(R.id.tview3);

        Connection connection = getItem(position);
        conId.setText(connection.getConId());
        name.setText(connection.getFirstName() + " " + connection.getLastName());
        amt.setText(connection.getConCode());
        return convertView;
    }
}




        /*int count = getCount();
        if (count > 5) {
            conId.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            amt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        } else {
            conId.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            amt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        }*/

