package com.example.cryptoking;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Decoder extends AppCompatActivity {

    EditText etdec;
    TextView dectv;
    private Spinner spinner2;
    private Button btnSubmit;
    ClipboardManager cplboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoder);

        addItemsOnSpinner2();

        etdec=findViewById(R.id.etdec);
        dectv=findViewById(R.id.dectv);

        cplboard=(ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

    }

    public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add("Encode");
        list.add("RSA");
        list.add("AES");
        list.add("Blowfish");
        list.add("AES");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }
    public void dec(View view){

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        btnSubmit = (Button) findViewById(R.id.btndec);
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String x=String.valueOf(spinner2.getSelectedItem());
                if(x=="Encode") {
                    String temp = etdec.getText().toString();
                    String rv = decode.dec(temp);
                    dectv.setText(rv);
                }else if(x=="AES") {
                    final String secretKey = "ssshhhhhhhhhhh!!!!";
                    String temp = etdec.getText().toString();
                    String rv = decode.decrypt(temp,secretKey);
                    dectv.setText(rv);
                }
            }
        });
//        String temp=etdec.getText().toString();
//        String rv=decode.dec(temp);
//        dectv.setText(rv);
    }
    public void cp1(View view){
        String data=dectv.getText().toString().trim();
        if (!data.isEmpty()){
            ClipData temp=ClipData.newPlainText("text",data);
            cplboard.setPrimaryClip(temp);
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show();
        }
    }
}
