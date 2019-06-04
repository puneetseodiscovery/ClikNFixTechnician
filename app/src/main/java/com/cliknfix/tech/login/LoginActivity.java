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
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cliknfix.tech.R;
import com.cliknfix.tech.base.BaseClass;
import com.cliknfix.tech.base.MyApp;
import com.cliknfix.tech.forgotPassword.ForgotPasswordActivity;
import com.cliknfix.tech.homeScreen.HomeScreenActivity;
import com.cliknfix.tech.responseModels.LoginResponseModel;
import com.cliknfix.tech.util.PreferenceHandler;
import com.cliknfix.tech.util.Utility;
import com.firebase.client.Firebase;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.android.gms.location.LocationServices.API;
import static com.google.android.gms.location.LocationServices.FusedLocationApi;

public class LoginActivity extends BaseClass implements ILoginActivity {

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

    Boolean passVisible = false;

    private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_PERMISSIONS_REQUEST = 1;
    private double currentLatitude;
    private double currentLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ipLoginActivity = new PLoginActivity(this);
        init();
    }

    private void init() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSIONS_REQUEST);
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            currentLatitude = location.getLatitude();
                            currentLongitude = location.getLongitude();
                            Toast.makeText(LoginActivity.this, currentLatitude + "WORKS" + currentLongitude, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        etEmail.setTypeface(Utility.typeFaceForText(this));
        /*etPassword.setTypeface(Utility.typeFaceForText(this));
        tvLoginText.setTypeface(Utility.typeFaceForBoldText(this));
        btnLogin.setTypeface(Utility.typeFaceForBoldText(this));*/

        ivPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!passVisible) {
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    etPassword.setSelection(etPassword.length());
                    passVisible = true;
                } else {
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    etPassword.setSelection(etPassword.length());
                    passVisible = false;
                }
            }
        });

    }


    public void onLoginClicked(View view) {
        if (Utility.isNetworkConnected(this)) {
            if(deviceToken == null){
                getDeviceToken();
            }
            Toast.makeText(this, "Device Token:" + deviceToken, Toast.LENGTH_SHORT).show();
           if(deviceToken!=null) {
                if (etEmail.getText().toString().length()>0 && etPassword.getText().toString().length()>0 ) {
                    if (Utility.validEmail(etEmail.getText().toString().trim())) {
                        progressDialog = Utility.showLoader(this);
                        ipLoginActivity.doLogin(etEmail.getText().toString().trim().toLowerCase()
                                ,etPassword.getText().toString().trim()
                                ,currentLatitude
                                ,currentLongitude
                                ,deviceToken);
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
               Toast.makeText(this, "deviceId:" + deviceToken, Toast.LENGTH_SHORT).show();
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
        String userId = userModelLoginResponse.getData().get(0).getId().toString().trim();
        String password = userModelLoginResponse.getData().get(0).getPassword().toString().trim();
        registerUserToFirebase(userId,password);
        loginUsertoFirebase(userId,password);
        progressDialog.dismiss();
        startActivity(new Intent(this, HomeScreenActivity.class));
    }

    private void loginUsertoFirebase(final String userId, final String password) {
        String url = "https://cliknfix-1558498832364.firebaseio.com/users.json";
        /*final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage("Loading...");
        pd.show();*/

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                if(s.equals("null")){
                    Toast.makeText(LoginActivity.this, "user not found", Toast.LENGTH_LONG).show();
                }
                else{
                    try {
                        JSONObject obj = new JSONObject(s);

                        if(!obj.has(userId)){
                            Log.e("firebase","user not found");
                            //Toast.makeText(LoginActivity.this, "user not found", Toast.LENGTH_LONG).show();
                        }
                        else if(obj.getJSONObject(userId).getString("password").equals(password)){
                            firebaseUsername = userId;
                            firebasePassword = password;
                            //startActivity(new Intent(LoginActivity.this, Users.class));
                        }
                        else {
                            Log.e("firebase","incorrect password");
                            //Toast.makeText(LoginActivity.this, "incorrect password", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                //pd.dismiss();
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
                //pd.dismiss();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(LoginActivity.this);
        rQueue.add(request);
    }

    @Override
    public void onLoginFailedFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    private void registerUserToFirebase(final String userId, final String password) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();

        String url = "https://cliknfix-1558498832364.firebaseio.com/users.json";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                Firebase reference = new Firebase("https://cliknfix-1558498832364.firebaseio.com/users");

                if(s.equals("null")) {
                    reference.child(userId).child("password").setValue(password);
                    Log.e("firebase","registration successful");
                    //Toast.makeText(LoginActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        JSONObject obj = new JSONObject(s);

                        if (!obj.has(userId)) {
                            reference.child(userId).child("password").setValue(password);
                            Toast.makeText(LoginActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                        } else {
                            Log.e("firebase","username already exists");
                            //Toast.makeText(LoginActivity.this, "username already exists", Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                pd.dismiss();
            }

        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError );
                pd.dismiss();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(LoginActivity.this);
        rQueue.add(request);
    }
}
