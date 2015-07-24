package io.foogoi.github.stroke_ball;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.graphics.Canvas;
import android.graphics.BitmapFactory;

public class AndroidAccelerometerActivity extends Activity 
	implements SensorEventListener{
	
	private SensorManager sensorManager;
	private Sensor sensorAccelerometer;
	public float valueAzimuth;
	public float valuePitch;
	public float valueRoll;
	/*TextView readingAzimuth, readingPitch, readingRoll;*/
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(
        		Sensor.TYPE_ACCELEROMETER);
        
        /*readingAzimuth = (TextView)findViewById(R.id.azimuth);
        readingPitch = (TextView)findViewById(R.id.pitch);
        readingRoll = (TextView)findViewById(R.id.roll);*/
        
    }

	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(this,
				sensorAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		/*
		 * event.values[0]: azimuth, rotation around the Z axis.
		 * event.values[1]: pitch, rotation around the X axis.
		 * event.values[2]: roll, rotation around the Y axis.
		 */
		
		valueAzimuth = event.values[0];
		valuePitch = event.values[1];
		valueRoll = event.values[2];
		
		/*readingAzimuth.setText("Azimuth: " + String.valueOf(valueAzimuth));
        readingPitch.setText("Pitch: " + String.valueOf(valuePitch));
        readingRoll.setText("Roll: " + String.valueOf(valueRoll));*/

		
	}



	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ball), 10, 10, null);
	}



}