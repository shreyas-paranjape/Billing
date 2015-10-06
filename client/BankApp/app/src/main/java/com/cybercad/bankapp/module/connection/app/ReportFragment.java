package com.cybercad.bankapp.module.connection.app;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import com.cybercad.bankapp.R;
import com.cybercad.bankapp.module.connection.model.Connection;
import com.cybercad.bankapp.module.connection.model.ConnectionPayment;
import com.cybercad.bankapp.module.connection.widget.ConnectionPaymentAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReportFragment extends Fragment {
    TextView fromDateView;
    TextView toDateView;
    Calendar fromDate,toDate;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         v=inflater.inflate(R.layout.fragment_report,container,false);

        final ConnectionPaymentAdapter cpa=new ConnectionPaymentAdapter(getActivity(),new ArrayList<ConnectionPayment>());
        final ListView conPayList= (ListView) v.findViewById(R.id.listReport);
        conPayList.setAdapter(cpa);

        Button b= (Button) v.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // SimpleDateFormat newFormat=new SimpleDateFormat("dd/MM/yy",Locale.US);
                //String newFromDate=newFormat.format(fromDate.getTime());
                //String newToDate=newFormat.format(toDate.getTime());
               // List<ConnectionPayment> cp1=ConnectionPayment.find
                        //(ConnectionPayment.class," date>=? and date<=?",newFromDate,newToDate);
                List<ConnectionPayment> conP = ConnectionPayment.listAll(ConnectionPayment.class);
                cpa.addAll(conP);
                ArrayList<Double> amtList = new ArrayList<>();
                for (int i = 0; i < conPayList.getCount(); i++) {
                    Adapter adapter= conPayList.getAdapter();
                    ConnectionPayment cp= (ConnectionPayment) adapter.getItem(i);
                    double amt= cp.getAmount();
                    amtList.add(amt);
                }
                double sum = 0;
                for(Double d : amtList)
                    sum += d;
                TextView total= (TextView) v.findViewById(R.id.totalCost);
                total.setText(String.valueOf(sum));
            }
        });

        fromDateView = (TextView) v.findViewById(R.id.from);
        toDateView = (TextView) v.findViewById(R.id.to);

        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yy", Locale.US);
        String date = dateFormat.format(new Date());
        fromDateView.setText(date);
        toDateView.setText(date);

        fromDate = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener fromdate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                fromDate.set(Calendar.YEAR, year);
                fromDate.set(Calendar.MONTH, monthOfYear);
                fromDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateFromDate();
            }
        };

        fromDateView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), fromdate, fromDate
                        .get(Calendar.YEAR), fromDate.get(Calendar.MONTH),
                        fromDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        toDate = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener todate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                toDate.set(Calendar.YEAR, year);
                toDate.set(Calendar.MONTH, monthOfYear);
                toDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateToDate();
            }
        };
        toDateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new DatePickerDialog(getActivity(), todate, fromDate
                        .get(Calendar.YEAR), fromDate.get(Calendar.MONTH),
                        toDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        return v;
    }

    private void updateFromDate() {
        String dateFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
            fromDateView.setText(sdf.format(fromDate.getTime()));
        }

    private void updateToDate() {
        String dateFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        if(fromDate.compareTo(toDate)<=0){
            toDateView.setText(sdf.format(toDate.getTime()));
        }
    }
 }


