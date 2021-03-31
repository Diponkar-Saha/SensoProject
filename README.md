# SensoProject
 This project has taught me a lot. Taught to think about things related to sensors. Not much has been done properly because this project has been done very quickly. This project will be made more beautiful in the future.
 
  ***proximity sensor is a sensor able to detect the presence of nearby objects without any physical contact.
    if (checkSensorAvailability(Sensor.TYPE_PROXIMITY)) {
            currentSensor = Sensor.TYPE_PROXIMITY;
        }
        
     float distance = event.values[0];
     
  ***accelerometer is an in-built comment of a smartphone to measure its acceleration. It tracks the different motion like shaking, tilting, swinging, and rotating and accordingly change the orientation of your app. To calculate and detect the motion, the accelerometer uses the value of XYZ
  
    
        
      if (currentSensor == Sensor.TYPE_ACCELEROMETER) {
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
                
                
    ***Gyro sensors, also known as angular rate sensors or angular velocity sensors, are devices that sense angular velocity. Angular velocity. In simple terms, angular velocity is the change in rotational angle per unit of time.
    
    
     if (event.values[0] > 0.5f) {
                    b3.setText("Anti Clock");
                } else if (event.values[0] < -0.5f) {
                    b3.setText("Clock");
                }
                
                
  ***Light sensors are electronic devices that indicate the intensity of daylight or artificial light. They convert light energy to an electrical signal output.
     if (currentSensor == Sensor.TYPE_LIGHT) {
                float valueZ = event.values[0];
                b4.setText("Brightness " + valueZ);
            }
            
  The challenge here was to add data every 5 minutes.
