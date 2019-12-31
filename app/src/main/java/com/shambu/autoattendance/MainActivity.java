package com.shambu.autoattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private static final int ERROR_DIALOG_REQUEST = 9001;

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    private Boolean mLocationAccessGranted = false;

    @BindView(R.id.btm_nav_view)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        NavController navController = Navigation.findNavController(this, R.id.navhost_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        iSServicesOK();

        getLocationPermissions();
    }

    private void getLocationPermissions() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION)!=
        PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this.getApplicationContext(), COARSE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED ){
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationAccessGranted = false;
        switch (requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE : {
                if(grantResults.length > 0){
                    for(int i = 0; i < grantResults.length; i++){
                        if(grantResults[i]==PackageManager.PERMISSION_GRANTED){
                            mLocationAccessGranted = true;
                            return;
                        }
                    }
                    mLocationAccessGranted = false;
                }
            }
        }
    }

    public boolean iSServicesOK(){
        Log.d(TAG, "Checking google services");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available== ConnectionResult.SUCCESS){
            Log.d(TAG, "Google play services is working");

            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d(TAG, "Resolvable error in google play services");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,
                    available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else {
            Log.d(TAG, "Map requests cannot be made");
        }
        return false;
    }
}
