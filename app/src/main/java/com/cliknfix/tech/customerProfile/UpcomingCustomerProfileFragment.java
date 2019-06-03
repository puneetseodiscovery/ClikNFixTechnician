package com.cliknfix.tech.customerProfile;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.cliknfix.tech.chat.ChatActivity;
import com.cliknfix.tech.customerProfile.presenter.IPUpcomingCustomerProfileFragment;
import com.cliknfix.tech.customerProfile.presenter.PUpcomingCustomerProfileFragment;
import com.cliknfix.tech.homeScreen.HomeScreenActivity;
import com.cliknfix.tech.otp.OTPFragment;
import com.cliknfix.tech.util.AppConstants;
import com.cliknfix.tech.util.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.LOCATION_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingCustomerProfileFragment extends Fragment implements View.OnClickListener,IUpcomingCustomerProfileFragment {

    private static final int LOCATION_PERMISSIONS_REQUEST = 1;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    RelativeLayout ivBack;
    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    @BindView(R.id.iv_phone)
    ImageView ivPhone;
    @BindView(R.id.tv_username_text)
    TextView tvUserNameText;
    @BindView(R.id.et_username)
    EditText etUserName;
    @BindView(R.id.tv_email_text)
    TextView tvEmailText;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.tv_phone_text)
    TextView tvPhoneText;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_age_text)
    TextView tvAgeText;
    @BindView(R.id.et_age)
    EditText etAge;
    @BindView(R.id.tv_bld_grp_text)
    TextView tvBldGrpText;
    @BindView(R.id.et_bld_grp)
    EditText etBldGrp;
    @BindView(R.id.tv_address_text)
    TextView tvAddressText;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.btn_track)
    Button btnTrack;
    @BindView(R.id.btn_start_job)
    Button btnStartJob;

    Context context;
    IPUpcomingCustomerProfileFragment ipUpcomingCustomerProfileFragment;
    ProgressDialog progressDialog;
    int totalUsers = 0;
    ArrayList<String> al = new ArrayList<>();
    String lat,lng,labourRate;

    public UpcomingCustomerProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming_customer_profile, container, false);
        ButterKnife.bind(this,view);
        context = getContext();
        ipUpcomingCustomerProfileFragment = new PUpcomingCustomerProfileFragment(this);
        init();
        return view;
    }

    public void init(){
        tvTitle.setTypeface(Utility.typeFaceForBoldText(getContext()));
        tvUserNameText.setTypeface(Utility.typeFaceForText(getContext()));
        etUserName.setTypeface(Utility.typeFaceForSemiBoldText(getContext()));
        tvEmailText.setTypeface(Utility.typeFaceForText(getContext()));
        etEmail.setTypeface(Utility.typeFaceForText(getContext()));
        tvPhoneText.setTypeface(Utility.typeFaceForText(getContext()));
        etPhone.setTypeface(Utility.typeFaceForText(getContext()));
        tvAgeText.setTypeface(Utility.typeFaceForText(getContext()));
        etAge.setTypeface(Utility.typeFaceForText(getContext()));
        tvBldGrpText.setTypeface(Utility.typeFaceForText(getContext()));
        etBldGrp.setTypeface(Utility.typeFaceForText(getContext()));
        tvAddressText.setTypeface(Utility.typeFaceForText(getContext()));
        etAddress.setTypeface(Utility.typeFaceForText(getContext()));
        btnTrack.setTypeface(Utility.typeFaceForBoldText(getContext()));
        btnStartJob.setTypeface(Utility.typeFaceForBoldText(getContext()));

        Log.e("customer userId","" + getArguments().getInt("id"));
        Log.e("customer userId name","" + getArguments().getString("name"));
        etUserName.setText(getArguments().getString("name"));
        etEmail.setText(getArguments().getString("email"));
        etPhone.setText(getArguments().getString("phone"));
        etAge.setText(getArguments().getString("age"));
        etBldGrp.setText(getArguments().getString("blood_group"));
        etAddress.setText(getArguments().getString("address"));
        lat = getArguments().getString("latitude");
        lng = getArguments().getString("longitude");
        labourRate = getArguments().getString("labour_rate");

        ivBack.setOnClickListener(this);
        ivMsg.setOnClickListener(this);
        ivPhone.setOnClickListener(this);
        btnTrack.setOnClickListener(this);
        btnStartJob.setOnClickListener(this);

        if(Utility.isNetworkConnected(context))
            enableGPS();
        else
            Toast.makeText(context, getResources().getString(R.string.no_network_connection), Toast.LENGTH_SHORT).show();

        /*progressDialog = Utility.showLoader(getContext());
        ipUpcomingCustomerProfileFragment.getUserProfile(Utility.getUserId(),Utility.getToken());*/
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.iv_msg:
                String url = "https://cliknfix-1558498832364.firebaseio.com/users.json";

                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {
                        Log.e("chat response","" + s);
                        doOnSuccess(s);
                        BaseClass.firebaseChatWith = String.valueOf(getArguments().getInt("id"));//String.valueOf(getArguments().getInt("id"));
                        startActivity(new Intent(context, ChatActivity.class));
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        System.out.println("" + volleyError);
                    }
                });

                RequestQueue rQueue = Volley.newRequestQueue(context);
                rQueue.add(request);



                //startActivity(new Intent((HomeScreenActivity)context, ChatActivity.class));
                break;
            case R.id.iv_phone:
                break;
            case R.id.btn_track:
                //Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");
                Uri gmmIntentUri = Uri.parse("geo:" + lat + "," + lng);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
                break;
            case R.id.btn_start_job:
                Bundle args = new Bundle();
                args.putString("labour_rate", labourRate);
                FragmentTransaction transaction = ((HomeScreenActivity)context).getSupportFragmentManager().beginTransaction();
                OTPFragment fragment = new OTPFragment();
                transaction.replace(R.id.frame_container, fragment);
                fragment.setArguments(args);
                transaction.addToBackStack(null);
                transaction.commit();
                //startActivity(new Intent((HomeScreenActivity)context, CompleteJobActivity.class));
                break;
            case R.id.iv_back:
                ((HomeScreenActivity) getActivity()).getSupportFragmentManager().popBackStack();
                break;
        }
    }

    public void doOnSuccess(String s){
        try {
            JSONObject obj = new JSONObject(s);

            Iterator i = obj.keys();
            String key = "";

            while(i.hasNext()){
                key = i.next().toString();

                if(!key.equals(BaseClass.firebaseUsername)) {
                    al.add(key);
                }

                totalUsers++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void enableGPS() {
        // Check GPS is enabled
        LocationManager lm = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(context, "Please enable location services", Toast.LENGTH_SHORT).show();
        } else {
            checkPermissions();
        }
    }

    private void checkPermissions() {
        // Check location permission is granted - if it is, start the service, otherwise request the permission
        int permission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            startTrackerService();
        } else {
            ActivityCompat.requestPermissions((HomeScreenActivity)context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSIONS_REQUEST);
        }
    }

    private void startTrackerService() {
        //context.startService(new Intent(context, TrackerLocationService.class));
        //finish();
        /*Intent serviceIntent = new Intent(TrackerLocationService.class.getName());
        serviceIntent.putExtra("customerId",String.valueOf(getArguments().getInt("id")));
        context.startService(serviceIntent);*/
        String id = String.valueOf(getArguments().getInt("id"));
        Log.e("starting service userId","" + id);
        Intent intent = new Intent(context,
                TrackerLocationService.class);
        intent.putExtra("customerId", id);
        context.startService(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[]
            grantResults) {
        if (requestCode == LOCATION_PERMISSIONS_REQUEST && grantResults.length == 1
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Start the service when the permission is granted
            Intent serviceIntent = new Intent(TrackerLocationService.class.getName());
            serviceIntent.putExtra("customerId", String.valueOf(getArguments().getInt("id")));
            context.startService(serviceIntent);
        }
    }
}
