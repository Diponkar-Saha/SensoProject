package com.example.sensoproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyDatabaseHelper myDB;

    ArrayList<String> id,time,accelerometer,proximity,lightSensor,gyroscope;
    customAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        recyclerView=findViewById(R.id.recyclerView);
        myDB=new MyDatabaseHelper(this);
        id=new ArrayList<>();
        time=new ArrayList<>();
        accelerometer=new ArrayList<>();
        proximity=new ArrayList<>();
        lightSensor=new ArrayList<>();
        gyroscope=new ArrayList<>();

        storeDataArray();

        customAdapter=new customAdapter(this,id,time,accelerometer,proximity,lightSensor,gyroscope);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    void storeDataArray(){
        Cursor cursor=myDB.readAllData();
        if(cursor.getCount()==0){
            Toast.makeText(this,"No Data",Toast.LENGTH_LONG);
        }else{
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                time.add(cursor.getString(1));
                accelerometer.add(cursor.getString(2));
                proximity.add(cursor.getString(3));
                gyroscope.add(cursor.getString(4));
                lightSensor.add(cursor.getString(5));
            }
        }
    }
}