package com.cybercad.bankapp.module.connection.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.cybercad.bankapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import de.greenrobot.event.EventBus;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        EventBus.getDefault().register(this);
        replaceFragment(new ConnectionSearchFragment());

        //---------------------------------------------------------------------------//

        try{
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//com.cybercad.bankapp//databases//biiling_bank";
                String backupDBPath = "bank_DbBackup.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);
                MediaScannerConnection.scanFile(this, new String[]{backupDB.toString()}, null, null);
                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Log.d("DB_NAME", "Database stored int" + backupDB.toString());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        		//----------------------------------------------------------------------//
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_report:
                ReportFragment rf= new ReportFragment();
                replaceFragment(rf);
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    public void replaceFragment(Fragment newFragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.mainfrag, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onEvent(ConnectionSearchFragment.ConnectionSelectedEvent event) {
        ConnectionDetailFragment cdf = new ConnectionDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("connID", event.getConnId());
        cdf.setArguments(bundle);
        replaceFragment(cdf);
    }
}