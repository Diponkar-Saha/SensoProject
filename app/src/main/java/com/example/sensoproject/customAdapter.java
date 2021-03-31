package com.example.sensoproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customAdapter extends RecyclerView.Adapter<customAdapter.MyViewHolder> {

    private  Context context;
    private ArrayList id,time,accelerometer,proximity,lightSensor,gyroscope;

    customAdapter(Context context,ArrayList id,ArrayList time,
                  ArrayList accelerometer,ArrayList proximity,
                  ArrayList lightSensor, ArrayList gyroscope){
        this.context=context;
        this.id=id;
        this.time=time;
        this.accelerometer=accelerometer;
        this.proximity=proximity;
        this.lightSensor=lightSensor;
        this.gyroscope=gyroscope;

    }

    @NonNull
    @Override
    public customAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull customAdapter.MyViewHolder holder, int position) {

        holder.id.setText(String.valueOf(id.get(position)));
        holder.time.setText(String.valueOf(time.get(position)));
        holder.accelerometer.setText(String.valueOf(accelerometer.get(position)));
        holder.proximity.setText(String.valueOf(proximity.get(position)));
        holder.gyroscope.setText(String.valueOf(gyroscope.get(position)));
//        holder.lightSensor.setText(String.valueOf(lightSensor.get(position)));

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       TextView id,time,accelerometer,proximity,lightSensor,gyroscope;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.Item_sensor_id);
            time=itemView.findViewById(R.id.Item_time);
            accelerometer=itemView.findViewById(R.id.Item_accelerometerSensor);
            proximity=itemView.findViewById(R.id.Item_proximitySensor);
            gyroscope=itemView.findViewById(R.id.Item_gyroscopeSensor);
            lightSensor=itemView.findViewById(R.id.Item_lightSensor);
        }
    }
}
