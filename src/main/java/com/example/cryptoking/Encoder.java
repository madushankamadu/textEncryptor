package com.example.cryptoking;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
public class Encoder extends AppCompatActivity {

    EditText etenc;
    TextView enctv;
    private Spinner spinner1;
    private Button btnSubmit;
    ClipboardManager cpb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encoder);

        addItemsOnSpinner2();


        etenc = findViewById(R.id.etenc);
        enctv = findViewById(R.id.enctv);
        cpb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    public void addItemsOnSpinner2() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        List<String> list = new ArrayList<String>();
        list.add("Encode");
        list.add("TripleDES");
        list.add("AES");
        list.add("Blowfish");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }

    public void enc(View view) {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        btnSubmit = (Button) findViewById(R.id.btnenc);
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String x=String.valueOf(spinner1.getSelectedItem());
                if(x=="Encode") {
                    String temp = etenc.getText().toString();
                    String rv = encode.enc(temp);
                    enctv.setText(rv);
                }else if(x=="AES"){

                    String temp = etenc.getText().toString();
                    String rv = TDESalgo.encode(temp);
                    enctv.setText(rv);


                }else if(x=="AES"){
                    final String secretKey = "ssshhhhhhhhhhh!!!!";
                    String temp = etenc.getText().toString();
                    String rv = AESalgo.encrypt(temp,secretKey);
                    enctv.setText(rv);


                }
            }
        });


}

//    public void enc(View view) {
//        String temp = etenc.getText().toString();
//        String rv = encode.enc(temp);
//        enctv.setText(rv);
//    }


    public void cp2(View view) {

        String data = enctv.getText().toString().trim();
        if (!data.isEmpty()) {
            ClipData temp = ClipData.newPlainText("text", data);
            cpb.setPrimaryClip(temp);
            Toast.makeText(this, "Copied", Toast.LENGTH_LONG).show();
        }
    }

}
