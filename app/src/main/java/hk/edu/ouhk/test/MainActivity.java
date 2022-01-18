package hk.edu.ouhk.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static SensorManager sensorManager;
    private Sensor sensor;
    TextView text ;
    View tilting ;
    boolean pass = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.textView);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensorManager.registerListener(MainActivity.this,sensor,sensorManager.SENSOR_DELAY_NORMAL);
        tilting = findViewById(R.id.tilting);
        //Intent intent = new Intent(this, LivePreviewActivity.class);
        //startActivity(intent);
        //finish();

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[2] ;
        text.setText(""+x);
        if( 0.57<= x && x <= 0.62){
            tilting.setBackgroundColor(Color.GREEN);
            long nowMillis = System.currentTimeMillis();
            pass = true;
        }
        else{
            pass = false;
            tilting.setBackgroundColor(Color.RED);
        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}