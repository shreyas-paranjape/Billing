package com.cybercad.bankapp.module.connection.app;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.cybercad.bankapp.R;
import com.cybercad.bankapp.module.connection.model.Connection;
import com.cybercad.bankapp.module.connection.model.ConnectionPayment;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ConnectionDetailFragment extends Fragment {
    private TextView conId, cc, fn, ln, curBal, addrs, curDue, dialogId, dialogName, dialogAmt;
    private EditText other;
    private String connID;
    private Button btnDisplay;
    private Date paidDate;
    private RadioGroup rg;
    RadioButton rb1,rb2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        Bundle data = getArguments();
        connID = data.getString("connID");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_conn_detail, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        conId = (TextView) getActivity().findViewById(R.id.conId);
        conId.setText(connID);
        cc = (TextView) getActivity().findViewById(R.id.cc);
        fn = (TextView) getActivity().findViewById(R.id.fn);
        ln = (TextView) getActivity().findViewById(R.id.ln);
        addrs = (TextView) getActivity().findViewById(R.id.add);
        curBal = (TextView) getActivity().findViewById(R.id.curBal);
        curDue = (TextView) getActivity().findViewById(R.id.curDue);
        other = (EditText) getActivity().findViewById(R.id.other);
        btnDisplay = (Button) getActivity().findViewById(R.id.confrmPay);
        rg = (RadioGroup) getActivity().findViewById(R.id.radioGroup);
        String val = connID;
        List<Connection> selectedConnectionList = Connection.find(Connection.class, "con_id = ?", val);
        final Connection selectedConnection = selectedConnectionList.get(0);
        if(selectedConnection != null){
            cc.setText(selectedConnection.getConCode());
            fn.setText(selectedConnection.getFirstName());
            ln.setText(selectedConnection.getLastName());
            addrs.setText(selectedConnection.getAddress());
            String balance= String.valueOf(selectedConnection.getCurBal());
            curBal.setText(balance);
            curDue.setText(balance);
        }
        rb1= (RadioButton) rg.findViewById(R.id.radioButton1);
        rb2= (RadioButton) rg.findViewById(R.id.radioButton2);
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final AlertDialog.Builder alertBox = new AlertDialog.Builder(v.getContext());
                final View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialogue_payment_confirm, null);
                alertBox.setView(dialogView);
                alertBox.setCancelable(false);
                final AlertDialog test = alertBox.create();
                alertBox.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String paid = dialogAmt.getText().toString();
                        double paidAmt = Double.parseDouble(paid);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy", Locale.US);
                        String date = dateFormat.format(new Date());
                        try {
                           paidDate = dateFormat.parse(date);
                        } catch (ParseException e) {
                           e.printStackTrace();
                       }
                        ConnectionPayment cp = new ConnectionPayment(selectedConnection, paidDate, paidAmt);
                        cp.save();
                        Toast.makeText(getActivity(), "data has been saved ", Toast.LENGTH_SHORT).show();
                    }
                });
                alertBox.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        test.cancel();
                    }
                });
                dialogId = (TextView) dialogView.findViewById(R.id.idText);
                dialogName = (TextView) dialogView.findViewById(R.id.nameText);
                dialogAmt = (TextView) dialogView.findViewById(R.id.amtText);
                dialogId.setText(conId.getText().toString());
                dialogName.setText(fn.getText().toString() + " " + ln.getText().toString());

               int radioBtnId = rg.getCheckedRadioButtonId();
                switch (radioBtnId) {
                    case R.id.radioButton1:
                        //other.setInputType(InputType.TYPE_NULL);
                        dialogAmt.setText(curDue.getText().toString());
                        alertBox.show();
                        break;
                    case R.id.radioButton2:
                        //other.setInputType(InputType.TYPE_CLASS_NUMBER);
                        String otherVal = other.getText().toString();
                        if ( !("".equals(otherVal))) {
                            dialogAmt.setText(otherVal);
                            alertBox.show();
                        } else {
                            Toast.makeText(v.getContext(), "Please enter an amount", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
    }
    }
