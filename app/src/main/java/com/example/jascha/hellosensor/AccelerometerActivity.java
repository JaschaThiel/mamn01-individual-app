package com.example.jascha.hellosensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAcc;
    private TextView x, y, z, lr;
    private DecimalFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        x = findViewById(R.id.x);
        y = findViewById(R.id.y);
        z = findViewById(R.id.z);
        lr = findViewById(R.id.lr);
        x.setText("X: ");
        y.setText("Y: ");
        z.setText("Z: ");
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.HALF_UP);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        System.out.println("test");
        if (sensorEvent.sensor.getType() != Sensor.TYPE_ACCELEROMETER) {
            return;
        }
        float[] accValues = sensorEvent.values;

        x.setText("X: " + df.format(accValues[0]));
        y.setText("Y: " + df.format(accValues[1]));
        z.setText("Z: " + df.format(accValues[2]));

        if(accValues[0] > 0){
            lr.setText("Vänster");
        } else {
            lr.setText("Höger");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAcc, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
