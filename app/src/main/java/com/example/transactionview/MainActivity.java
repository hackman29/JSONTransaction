package com.example.transactionview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> TxnData,Amount,Status,Reference,Cardtype;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TxnData = new ArrayList<>();
        Amount = new ArrayList<>();
        Status = new ArrayList<>();
        Reference = new ArrayList<>();
        Cardtype = new ArrayList<>();

        recyclerView = findViewById(R.id.recycleview);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        try{
            JSONObject trans = new JSONObject(Transactionloadjson());
            JSONArray transary = trans.getJSONArray("transactions");

            for(int i =0; i < transary.length(); i++){

                JSONObject TransDetail = transary.getJSONObject(i);

                TxnData.add(TransDetail.getString("txnDate"));
                Amount.add(TransDetail.getString("amount"));
                Status.add(TransDetail.getString("status"));
                Reference.add(TransDetail.getString("reference"));
                Cardtype.add(TransDetail.getString("cardType"));

            }
        }catch (Exception e){

            e.printStackTrace();
        }

        List_Adapter list_adapter = new List_Adapter(this,TxnData,Amount,Status,Reference,Cardtype);
        recyclerView.setAdapter(list_adapter);

    }

    private String Transactionloadjson() {
        String json = null;
        try{
            InputStream ipts = getAssets().open("transactions.json");
            int size = ipts.available();
            byte[] buffer = new byte[size];
            ipts.read(buffer);
            ipts.close();
            json = new String(buffer,"UTF-8");
        }catch (Exception e){

            e.printStackTrace();
            return null;
        }
        return json;
    }
}
