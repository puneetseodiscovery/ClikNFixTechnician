package com.cliknfix.tech.homeScreen.bottomFragments;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.tech.R;
import com.cliknfix.tech.base.MyApp;
import com.cliknfix.tech.homeScreen.HomeScreenActivity;
import com.cliknfix.tech.homeScreen.bottomFragments.presenter.IPUserProfileFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.presenter.PUserProfileFragment;
import com.cliknfix.tech.responseModels.SaveUserProfileResponseModel;
import com.cliknfix.tech.responseModels.UserProfileResponseModel;
import com.cliknfix.tech.util.PreferenceHandler;
import com.cliknfix.tech.util.Utility;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment implements View.OnClickListener,IUserProfileFragment {

    public static String TAG_USER_PROFILE_FRAGMENT = "UserProfileFragment";

    @BindView(R.id.iv_edit)
    ImageView ivEdit;
    @BindView(R.id.iv_save)
    ImageView ivSave;
    @BindView(R.id.cam)
    CircleImageView CIcam;
    @BindView(R.id.ll_user_profile)
    LinearLayout llUserProfile;
    @BindView(R.id.iv_cam)
    RelativeLayout rlCam;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_username_text)
    TextView tvUserNameText;
    @BindView(R.id.et_username)
    EditText etUserName;
    @BindView(R.id.tv_email_text)
    TextView tvEmailText;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.tv_labour_text)
    TextView tvLabourText;
    @BindView(R.id.et_labour)
    EditText etLabour;
    @BindView(R.id.tv_age_text)
    TextView tvAgeText;
    @BindView(R.id.et_age)
    EditText etAge;
    @BindView(R.id.tv_address_text)
    TextView tvAddressText;
    @BindView(R.id.et_address)
    TextView etAddress;
    @BindView(R.id.tv_verfied_doc_text)
    TextView tvVerifiedDocText;
    @BindView(R.id.iv_verfied_doc)
    ImageView ivVerifiedDoc;
    @BindView(R.id.profilePic)
    CircleImageView ivProfilePic;

    IPUserProfileFragment ipUserProfileFragment;
    ProgressDialog progressDialog;

    MultipartBody.Part body;
    String encodedImg,getEncodedImg;
    Bitmap userImgBitmap;

    public static final int PLACE_PICKER_REQUEST = 2;
    public static final int CAMERA_REQUEST_CODE = 3;
    public static final int GALLERY_REQUEST_CODE = 4;



    public UserProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        ButterKnife.bind(this, view);
        ipUserProfileFragment = new PUserProfileFragment(this);
        init();
        return view;
    }

    public void init(){
        tvTitle.setTypeface(Utility.typeFaceForBoldText(getContext()));
        tvUserNameText.setTypeface(Utility.typeFaceForText(getContext()));
        etUserName.setTypeface(Utility.typeFaceForText(getContext()));
        tvEmailText.setTypeface(Utility.typeFaceForText(getContext()));
        etEmail.setTypeface(Utility.typeFaceForText(getContext()));
        tvLabourText.setTypeface(Utility.typeFaceForText(getContext()));
        etLabour.setTypeface(Utility.typeFaceForText(getContext()));
        tvAgeText.setTypeface(Utility.typeFaceForText(getContext()));
        etAge.setTypeface(Utility.typeFaceForText(getContext()));
        tvVerifiedDocText.setTypeface(Utility.typeFaceForText(getContext()));
        tvAddressText.setTypeface(Utility.typeFaceForText(getContext()));
        etAddress.setTypeface(Utility.typeFaceForText(getContext()));

        getEncodedImg = new PreferenceHandler().readSaveImgString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_UPLOAD_IMG, "");
        userImgBitmap = decodeBase64(getEncodedImg);

        progressDialog = Utility.showLoader(getContext());
        ipUserProfileFragment.getUserProfile(Utility.getUserId(),Utility.getToken());

        ivEdit.setOnClickListener(this);
        ivSave.setOnClickListener(this);
        ivVerifiedDoc.setOnClickListener(this);
        etAddress.setOnClickListener(this);
        etAddress.setClickable(false);
        rlCam.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        switch (id){
            case R.id.iv_edit:
                ivEdit.setVisibility(View.GONE);
                ivSave.setVisibility(View.VISIBLE);
                rlCam.setVisibility(View.VISIBLE);
                etUserName.setFocusableInTouchMode(true);
                //etEmail.setFocusableInTouchMode(true);
                etLabour.setFocusableInTouchMode(true);
                etAge.setFocusableInTouchMode(true);
                etAddress.setFocusableInTouchMode(true);
                etUserName.requestFocus();
                etAddress.setClickable(true);
                ivVerifiedDoc.setEnabled(true);
                mgr.showSoftInput(etUserName, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.iv_save:
                if(new Integer(etAge.getText().toString()).intValue() <=150) {
                    ivEdit.setVisibility(View.VISIBLE);
                    ivSave.setVisibility(View.GONE);
                    rlCam.setVisibility(View.GONE);
                /*focusedView.setFocusable(false);
                focusedView.setFocusableInTouchMode(false);
                focusedView.setFocusable(true);
                focusedView.setFocusableInTouchMode(true);*/
                    etUserName.setFocusable(false);
                    etUserName.setFocusableInTouchMode(false);
                    //etEmail.setFocusable(false);
                    //etEmail.setFocusableInTouchMode(false);
                    etLabour.setFocusable(false);
                    etLabour.setFocusableInTouchMode(false);
                    etAge.setFocusable(false);
                    etAge.setFocusableInTouchMode(false);
                    etAddress.setFocusable(false);
                    etAddress.setFocusableInTouchMode(false);
                    etAddress.setClickable(false);
                    ivVerifiedDoc.setEnabled(false);
                    mgr.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    new PreferenceHandler().writeSaveImgString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_UPLOAD_IMG, encodedImg);
                    progressDialog = Utility.showLoader(getContext());
                    ipUserProfileFragment.saveUserProfile(etUserName.getText().toString().trim(),
                            etAge.getText().toString().trim(),
                            etAddress.getText().toString().trim(),
                            Utility.getToken());
                } else {
                    etAge.setError("Maximum age limit should be 150.");
                    etAge.requestFocus();
                }
                break;
            case R.id.et_address:
                PlacePicker.IntentBuilder builder1=new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder1.build((HomeScreenActivity)getContext()), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.iv_cam:
                final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Add Photo!");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo"))
                        {
                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                        }
                        else if (options[item].equals("Choose from Gallery"))
                        {
                            Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent, GALLERY_REQUEST_CODE);
                        }
                        else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
                break;
            case R.id.iv_verfied_doc:
                Toast.makeText(getActivity(), "Coming Soon!!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cam:
                Toast.makeText(getActivity(), "Coming Soon!!!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void getUserProfileSuccessFromPresenter(UserProfileResponseModel userProfileResponseModel) {
        progressDialog.dismiss();
        etUserName.setText(userProfileResponseModel.getData().get(0).getName());
        etEmail.setText(userProfileResponseModel.getData().get(0).getEmail());
        etLabour.setText(userProfileResponseModel.getData().get(0).getServiceCategory());
        etAge.setText(userProfileResponseModel.getData().get(0).getAge());
        etAddress.setText(userProfileResponseModel.getData().get(0).getAddress());

        if(userImgBitmap != null){
            ivProfilePic.setImageBitmap(userImgBitmap);
        }
    }

    @Override
    public void getUserProfileFailureFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(getContext(), "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveUserProfileSuccessFromPresenter(SaveUserProfileResponseModel saveUserProfileResponseModel) {
        progressDialog.dismiss();
        etUserName.setText(saveUserProfileResponseModel.getData().get(0).getName());
        etEmail.setText(saveUserProfileResponseModel.getData().get(0).getEmail());
        etLabour.setText(saveUserProfileResponseModel.getData().get(0).getPhone());
        etAge.setText(saveUserProfileResponseModel.getData().get(0).getAge());
        etAddress.setText(saveUserProfileResponseModel.getData().get(0).getAddress());
    }

    @Override
    public void saveUserProfileFailureFromPresenter(String msgg) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST){
            if (resultCode == RESULT_OK) {
                //etAddress.setEnabled(true);
                Place place = PlaceAutocomplete.getPlace((HomeScreenActivity)getContext(), data);
                Log.e("Tag", "Place: "
                        + place.getAddress()
                        + place.getPhoneNumber()
                        + place.getLatLng().latitude);
                /*select_latitude = String.valueOf(place.getLatLng().latitude);
                select_longitude = String.valueOf(place.getLatLng().longitude);*/

                etAddress.setText(place.getAddress().toString());
            }
        } else if (requestCode == CAMERA_REQUEST_CODE) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ivProfilePic.setImageBitmap(photo);
            try {
                body = sendImageFileToserver(photo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            encodedImg = encodeTobase64(photo);
        } else if (requestCode == GALLERY_REQUEST_CODE) {
            Uri selectedImage = data.getData();
            ivProfilePic.setImageURI(selectedImage);

            /*String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor c = getActivity().getContentResolver().query(selectedImage,filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));*/


            //Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                body = sendImageFileToserver(bitmap);
                encodedImg = encodeTobase64(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public MultipartBody.Part sendImageFileToserver(Bitmap bitMap) throws IOException {
        File filesDir = getActivity().getFilesDir();
        File file = new File(filesDir, "pictures[]" + ".png");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitMap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
        byte[] bitmapdata = bos.toByteArray();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bitmapdata);
        fos.flush();
        fos.close();

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("pictures[]", file.getName(), reqFile);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), "pictures[]");

        Log.e("multipart body","" + fileToUpload);
        Log.e("multipart name","" + filename);
        /*new PreferenceHandler().writeSaveImgString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_UPLOAD_IMG, String.valueOf(fileToUpload));
        new PreferenceHandler().writeSaveImgString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_IMG_NAME, String.valueOf(filename));*/

        return body;


       /* File file = new File(uri.getPath());
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        Log.e("Multipart Url ","" + filePart);*/
    }

    // method for bitmap to base64
    public String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

    // method for base64 to bitmap
    public Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
