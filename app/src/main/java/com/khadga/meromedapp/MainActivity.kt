//package com.khadga.meromedapp
//
//import android.content.Intent
//import android.hardware.Sensor
//import android.hardware.SensorEvent
//import android.hardware.SensorEventListener
//import android.hardware.SensorManager
//import android.os.Bundle
//import android.view.WindowManager
//import androidx.appcompat.app.AppCompatActivity
//import com.khadga.meromedapp.notification.NotificationSender
//
//class MainActivity : AppCompatActivity(), SensorEventListener {
//    private lateinit var sensorManager: SensorManager
//    private var sensor: Sensor? = null
//    private var sensor1: Sensor? = null
//    private var sensor2: Sensor? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        sensorManager = this.getSystemService(SENSOR_SERVICE) as SensorManager
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )
//        if (checkSensor()) {
//            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
//            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
//            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
//            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
//            sensorManager.registerListener(this, sensor1, SensorManager.SENSOR_DELAY_NORMAL)
//            sensorManager.registerListener(this, sensor2, SensorManager.SENSOR_DELAY_NORMAL)
//        }
//        //setContentView(R.layout.activity_main)
//
//
//        val intent = Intent(
//            this@MainActivity,
//            SplashActivity::class.java
//        )
//        startActivity(intent)
//    }
//
//    private fun checkSensor(): Boolean {
//        var flag = true
//        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) == null) {
//            flag = false
//        }
//
//        if(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) == null)
//        {
//            flag = false
//        }
//        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null)
//        {
//            flag = false
//        }
//        return flag
//    }
//
//    override fun onSensorChanged(event: SensorEvent?) {
//
//
//       if(event!!.sensor.type == Sensor.TYPE_PROXIMITY)
//       {
//           val value = event!!.values[0]
//           if(value <= 4)
//           {
//               var intent = Intent(this@MainActivity,LoginActivity::class.java)
//               startActivity(intent)
//               finish()
//           }
//       }
//
//
//        if(event!!.sensor.type == Sensor.TYPE_LIGHT)
//        {
//            val values = event!!.values[0]
//            if(values > 40)
//            {
//                NotificationSender(this,"WARNING: High Light","").createHighPriority()
//            }
//        }
//
//
//        if(event!!.sensor.type == Sensor.TYPE_ACCELEROMETER)
//        {
//            val values = event!!.values[0]
//            if(values > 7)
//            {
//                val intent = Intent(this@MainActivity,LoginActivity::class.java)
//                startActivity(intent)
//            }
//            else if(values > 7 && values < 10)
//            {
//                val intent = Intent(this@MainActivity,RegisterActivity::class.java)
//                startActivity(intent)
//            }
//        }
//    }
//
//    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//    }
//}