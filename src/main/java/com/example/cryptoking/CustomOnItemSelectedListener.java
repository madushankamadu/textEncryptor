package com.example.cryptoking;

import android.view.View;
import android.widget.AdapterView;

public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
       String x =  parent.getItemAtPosition(pos).toString();

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }
}
