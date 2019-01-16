package nl.codepanda.quidcoach;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LocationManager locationManager;
    Location currentLocation = null;

    Location firstLocation = null;
    Location secondLocation = null;

    TextView firstLocationTextView = null;
    TextView secondLocationTextView = null;

    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            currentLocation = location;

            System.out.println("===========================================");
            System.out.println("Lat: " + currentLocation.getLatitude() + " Long: " + currentLocation.getLongitude());
            System.out.println("===========================================");
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.firstLocationTextView = findViewById(R.id.firstLocation);
        this.secondLocationTextView = findViewById(R.id.secondLocation);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("test");
            //return;
        }

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("test");
            //return;
        }

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, locationListener);
        } catch (Exception e) {
            throw e;
        }
    }

    public void storeLocation(View view) {
        if (currentLocation == null) {
            firstLocationTextView.setText("Location is null");
            return;
        }

        if(firstLocation == null) {
            firstLocation = currentLocation;

            firstLocationTextView.setText("Lat: " + firstLocation.getLatitude() + " Long: " + firstLocation.getLongitude());
        } else if (secondLocation == null) {
            secondLocation = currentLocation;

            secondLocationTextView.setText("Lat: " + secondLocation.getLatitude() + " Long: " + secondLocation.getLongitude());
        } else {
            Intent intent = new Intent(this, CanvasActivity.class);
            startActivity(intent);
        }
    }
}
