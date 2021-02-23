package com.example.linj.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class GPSActivity extends AppCompatActivity {
    public static final int LOCATION_CODE = 301;
    private static final String TAG = "GPSActivity";
    private LocationManager locationManager;
    private String locationProvider = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_s);
        getLocation();
    }

    private void getLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            Log.d(TAG, "getLocation: ----------------1111111111111111111111");
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else if (providers.contains(LocationManager.GPS_PROVIDER)) {
            Log.d(TAG, "getLocation: ----------------2222222222222222222222");
            //如果是Network
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.PASSIVE_PROVIDER)) {
            Log.d(TAG, "getLocation: ----------------3333333333333333333333");
            //如果是Network
            locationProvider = LocationManager.PASSIVE_PROVIDER;
        } else {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d(TAG, "getLocation: -------- 44444444444444444444444444444");
            //获取权限（如果没有开启权限，会弹出对话框，询问是否开启权限）
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                //请求权限
                Log.d(TAG, "getLocation: -------- 5555555555555555555555555555");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_CODE);
            } else {
                Log.d(TAG, "getLocation: -------- 666666666666666666666666666666");

                //监视地理位置变化
                locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
                Location location = locationManager.getLastKnownLocation(locationProvider);
                if (location != null) {
                    Log.d(TAG, "getLocation: -------- 777777777777777777777777777");

                    //输入经纬度
                    Toast.makeText(this, location.getLongitude() + " " + location.getLatitude() + "",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Log.d(TAG, "getLocation: -------- 888888888888888888888888888888888");

            //监视地理位置变化
            locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
            Location location = locationManager.getLastKnownLocation(locationProvider);
            if (location != null) {
                Log.d(TAG, "getLocation: -------- 99999999999999999999999");
                //不为空,显示地理位置经纬度
                Toast.makeText(this, location.getLongitude() + " "
                        + location.getLatitude() + "", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public LocationListener locationListener = new LocationListener() {
        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d(TAG, "onStatusChanged: ---------------------");
        }

        // Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {
            Log.d(TAG, "onProviderEnabled: -----------------");
        }

        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {
            Log.d(TAG, "onProviderDisabled: ---------------");
        }

        //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        @Override
        public void onLocationChanged(Location location) {
            Log.d(TAG, "onLocationChanged: ---------------------");
            if (location != null) {
                //不为空,显示地理位置经纬度
                Log.d(TAG, "onLocationChanged: " + location.getLongitude() + " " + location.getLatitude() + "");
            } else {
                Log.d(TAG, "onLocationChanged: -----------------------------------");
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == getPackageManager().PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "申请权限", Toast.LENGTH_LONG).show();
                try {
                    List<String> providers = locationManager.getProviders(true);
                    if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
                        //如果是Network
                        locationProvider = LocationManager.NETWORK_PROVIDER;
                    } else if (providers.contains(LocationManager.GPS_PROVIDER)) {
                        //如果是GPS
                        locationProvider = LocationManager.GPS_PROVIDER;
                    }
                    //监视地理位置变化
                    locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
                    Location location = locationManager.getLastKnownLocation(locationProvider);
                    if (location != null) {
                        //不为空,显示地理位置经纬度
                        Toast.makeText(GPSActivity.this, location.getLongitude() + " " + location.getLatitude() + "", Toast.LENGTH_SHORT).show();
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "缺少权限", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(locationListener);
    }
}