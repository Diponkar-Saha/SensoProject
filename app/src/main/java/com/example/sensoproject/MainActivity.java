package com.example.sensoproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView b4;
    private SensorManager sensorManager;
    private TextView b1,b2,b3;
    private Sensor accelerometerSensor;
    private Sensor proximitySensor;
    private Sensor lightSensor;
    private Sensor gyroscopeSensor;
    private LinearLayout linearLayout;

    private int currentSensor;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    MyDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b4 = findViewById(R.id.btnLightSensor);

        b1 = findViewById(R.id.btnAccelerometer);
        b2 = findViewById(R.id.btnProximity);
        b3 = findViewById(R.id.btnGyro);
        linearLayout=findViewById(R.id.lll);
      //  b4 = findViewById(R.id.btnLightSensor);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        helper = new MyDatabaseHelper(this);


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RecyclerActivity.class);
                startActivity(i);
            }
        });



    }


    public boolean checkSensorAvailability(int sensorType) {
        boolean isSensor = false;
        if (sensorManager.getDefaultSensor(sensorType) != null) {
            isSensor = true;
        }
        return isSensor;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == currentSensor) {

            if (currentSensor == Sensor.TYPE_LIGHT) {
                float valueZ = event.values[0];
                b4.setText("Brightness " + valueZ);
            } else if (currentSensor == Sensor.TYPE_PROXIMITY) {
                float distance = event.values[0];
                b2.setText("Proximity " + distance);
            }else if (currentSensor == Sensor.TYPE_ACCELEROMETER) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                long curTime = System.currentTimeMillis();

                if ((curTime - lastUpdate) > 100) {
                    long diffTime = (curTime - lastUpdate);
                    lastUpdate = curTime;

                    float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                    if (speed > SHAKE_THRESHOLD) {
                        Toast.makeText(getApplicationContext(), "just shook", Toast.LENGTH_LONG).show();
                    }

                    last_x = x;
                    last_y = y;
                    last_z = z;
                    b1.setText("x: "+last_x+", y: "+last_y+",z: "+last_z);
                }
            } else if (currentSensor == Sensor.TYPE_GYROSCOPE) {
                if (event.values[0] > 0.5f) {
                    b3.setText("Anti Clock");
                } else if (event.values[0] < -0.5f) {
                    b3.setText("Clock");
                }
            }

        }
       // String t1 = textView.getText().toString();
        String a1 = b1.getText().toString();
        String p1 = b2.getText().toString();
        String g1 = b3.getText().toString();
        String l1 = b4.getText().toString();

        Intent serviceIntent = new Intent(this, ExampleService.class);
        serviceIntent.putExtra("inputExtra", l1);
        serviceIntent.putExtra("inputExtra1", g1);
        serviceIntent.putExtra("inputExtra2", p1);
        serviceIntent.putExtra("inputExtra3", a1);
        ContextCompat.startForegroundService(this, serviceIntent);
        // helper.insertData(t1);
        helper.insertData(p1,a1,g1,l1);

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void accelerometerSensorOnClick(View view) {
        if (checkSensorAvailability(Sensor.TYPE_ACCELEROMETER)) {
            currentSensor = Sensor.TYPE_ACCELEROMETER;
        }
        b1.setText("Accelerometer not available");
    }

    public void proximitySensorOnClick(View view) {
        if (checkSensorAvailability(Sensor.TYPE_PROXIMITY)) {
            currentSensor = Sensor.TYPE_PROXIMITY;
        }
        b2.setText("Proximity Sensor not available");
    }

    public void gyroscopeSensorOnClick(View view) {
        if (checkSensorAvailability(Sensor.TYPE_GYROSCOPE)) {
            currentSensor = Sensor.TYPE_GYROSCOPE;
        } else {
            b3.setText("Gyroscope Sensor not available");
        }
    }

    public void lightSensorOnClick(View view) {
        if (checkSensorAvailability(Sensor.TYPE_LIGHT)) {
            currentSensor = Sensor.TYPE_LIGHT;
        } else {
            b4.setText("Light Sensor not available");
        }
    }




    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(this, accelerometerSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, lightSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, proximitySensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscopeSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


}