package com.Backgroundservice;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.smartmobileproject.activity.R;
import com.smartmobileproject.function.GetHttpResponse;

import net.daum.mf.map.api.MapPoint;

import java.util.Timer;
import java.util.TimerTask;

public class BackgroundService extends Service {
    private final static String TAG = BackgroundService.class.getSimpleName();

    private Context context = null;
    public int counter = 0;

    public BackgroundService() {
    }

    public BackgroundService(Context applicationContext) {
        super();
        context = applicationContext;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 서비스에서 가장 먼저 호출됨(최초에 한번만)
        Log.d(TAG, "BackgroundService.onCreate");


    }
    GetHttpResponse gethttpresponse;
    LocationManager locationManager;
    LocationListener locationListener;
    private double longtitude;
    private double latitude;
    final String url = "https://phpproject-cparr.run.goorm.io/UPloadBackgroundlocation.php";
    private String username = "user";
    public static String request ;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        // 서비스가 호출될 때마다 실행
        Log.d(TAG, "BackgroundService.onStartCommand");
        //
        startTimer();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 서비스가 종료될 때 실행
        Log.d(TAG, "BackgroundService.onDestroy");
        //
        Intent broadcastIntent = new Intent("com.Backgroundservice.RestarterBroadcastReceiver");
        sendBroadcast(broadcastIntent);

    }

    public double getlongtitude(){
        return longtitude;
    }
    public void setLongtitude(double longtitude){
        this.longtitude = longtitude;
    }

    public double getLatitude(){
        return latitude;
    }
    public void setLatitude(double latitude){
        this.latitude = latitude;
    }


    private Timer timer;
    private TimerTask timerTask;
    long oldTime = 0;

    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        timer.schedule(timerTask, 1000, 36000000); //
    }

    /**
     * it sets the timer to print the counter every x seconds
     */
    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                Context context1 = getApplicationContext();
                Log.i(TAG, "in timer ++++  " + (counter++));
                locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                Handler mhandler = new Handler(Looper.getMainLooper());

                mhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gethttpresponse = new GetHttpResponse();
                        locationListener = new LocationListener() {

                        @Override
                        public void onLocationChanged(@NonNull Location location) {

                            setLatitude(location.getLatitude());
                            setLongtitude(location.getLongitude());

                        }
                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    };
                        if (ActivityCompat.checkSelfPermission(context1, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context1, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            Log.d("권한설정", "권한설정을 해주세요.");
                        }
                        if (LocationManager.GPS_PROVIDER != null) {
                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1000, locationListener);
                        } else if (LocationManager.NETWORK_PROVIDER != null) {
                            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1000, locationListener);
                        } else {
                            Log.d("위치정보", "위치정보를 불러올수가 없습니다......ㅠㅠㅠ");
                        }

                        request = url + "?longtitude="+getlongtitude()+"&latitude="+getlongtitude()+"&username="+username+"15";
                        Log.d("request",request);
                        gethttpresponse.execute(request);

                    }
                },0);


                //schedule the timer, to wake up every 1 second
            }
        };
    }

    /**
     * not needed
     */
    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void onTaskRemoved(Intent rootIntent) {
        Log.d(TAG, "BackgroundService.onTaskRemoved");
        //create an intent that you want to start again.
        Intent intent = new Intent(getApplicationContext(), BackgroundService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 1, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 5000, pendingIntent);
        super.onTaskRemoved(rootIntent);
    }


}
