package com.cliknfix.tech.login;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.tech.R;
import com.cliknfix.tech.base.BaseClass;
import com.cliknfix.tech.base.MyApp;
import com.cliknfix.tech.forgotPassword.ForgotPasswordActivity;
import com.cliknfix.tech.homeScreen.HomeScreenActivity;
import com.cliknfix.tech.responseModels.LoginResponseModel;
import com.cliknfix.tech.util.PreferenceHandler;
import com.cliknfix.tech.util.Utility;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.android.gms.location.LocationServices.API;
import static com.google.android.gms.location.LocationServices.FusedLocationApi;

public class LoginActivity extends BaseClass implements ILoginActivity
        , GoogleApiClient.ConnectionCallbacks
        , GoogleApiClient.OnConnectionFailedListener
        , LocationListener {

    @BindView(R.id.login_text)
    TextView tvLoginText;
    @BindView(R.id.et_email_login)
    EditText etEmail;
    @BindView(R.id.et_password_login)
    EditText etPassword;
    @BindView(R.id.iv_password)
    ImageView ivPassword;
    @BindView(R.id.cb_remember)
    CheckBox cbRemember;
    @BindView(R.id.btn_signin)
    Button btnLogin;
    String deviceId;

    ProgressDialog progressDialog;
    IPLoginActivity ipLoginActivity;

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;

    private static final int LOCATION_PERMISSIONS_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ipLoginActivity = new PLoginActivity(this);
        init();
    }

    private void init() {
        etEmail.setTypeface(Utility.typeFaceForText(this));
        /*etPassword.setTypeface(Utility.typeFaceForText(this));
        tvLoginText.setTypeface(Utility.typeFaceForBoldText(this));
        btnLogin.setTypeface(Utility.typeFaceForBoldText(this));*/

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                // The next two lines tell the new client that “this” current class will handle connection stuff
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                //fourth line adds the LocationServices API endpoint from GooglePlayServices
                .addApi(API)
                .build();

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        if(Utility.isNetworkConnected(this))
            enableGPS();
        else
            Toast.makeText(this, getResources().getString(R.string.no_network_connection), Toast.LENGTH_SHORT).show();
    }

    private void enableGPS() {
        // Check GPS is enabled
        LocationManager lm = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "Please enable location services", Toast.LENGTH_SHORT).show();
        } else {
            checkPermissions();
        }
    }


    private void checkPermissions() {
        // Check location permission is granted - if it is, start the service, otherwise request the permission
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int permission1 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        if (permission == PackageManager.PERMISSION_GRANTED && permission1 == PackageManager.PERMISSION_GRANTED) {

            startService(new Intent(this, LoginFirebaseMessagingService.class));
            deviceId = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_FIREBASE_TOKEN, "");
            Log.e("Login deviceId",deviceId);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSIONS_REQUEST);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Now lets connect to the API
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(this.getClass().getSimpleName(), "onPause()");

        //Disconnect from API onPause()
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.e("onLocationChanged","working");
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.e("onConnected","working");
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            Location location = FusedLocationApi.getLastLocation(mGoogleApiClient);

            if (location == null) {
                FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

            } else {
                //If everything went fine lets get latitude and longitude
                currentLatitude = location.getLatitude();
                currentLongitude = location.getLongitude();
                Log.e("Lat","" + currentLatitude);
                Log.e("Lon","" + currentLongitude);
                Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            Log.e("Error", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[]
            grantResults) {
        if (requestCode == LOCATION_PERMISSIONS_REQUEST && grantResults.length == 2
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            // Start the service when the permission is granted
            startService(new Intent(this, LoginFirebaseMessagingService.class));
            deviceId = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_FIREBASE_TOKEN, "");
        }
    }

    public void onLoginClicked(View view) {
        if (Utility.isNetworkConnected(this)) {
           if(deviceId!=null) {
                if (etEmail.getText().toString().length()>0 && etPassword.getText().toString().length()>0 ) {
                    if (Utility.validEmail(etEmail.getText().toString().trim())) {
                        progressDialog = Utility.showLoader(this);
                        ipLoginActivity.doLogin(etEmail.getText().toString().trim().toLowerCase()
                                ,etPassword.getText().toString().trim()
                                ,currentLatitude
                                ,currentLongitude
                                ,deviceId);
                    } else {
                        etEmail.setError("Enter a valid email.");
                        etEmail.requestFocus();
                    }
                } else {
                    if (etEmail.getText().toString().length()==0 && etPassword.getText().toString().length()==0)
                    {
                        etEmail.setError("Enter email.");
                        etPassword.setError("Enter password");
                        etEmail.requestFocus();
                    }
                    else if (etPassword.getText().toString().length()==0)
                    {
                        etPassword.setError("Enter password");
                        etPassword.requestFocus();
                    }
                    else if (etEmail.getText().toString().length()==0)
                    {
                        etEmail.setError("Enter email.");
                        etEmail.requestFocus();
                    }
                }
           } else {
               enableGPS();
           }
        } else {
            Toast.makeText(this, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void onForgotPasswordClicked(View view) {
        startActivity(new Intent(this, ForgotPasswordActivity.class));
    }

    @Override
    public void onLoginSuccessFromPresenter(LoginResponseModel userModelLoginResponse) {
        progressDialog.dismiss();
        startActivity(new Intent(this, HomeScreenActivity.class));
    }

    @Override
    public void onLoginFailedFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }
}
