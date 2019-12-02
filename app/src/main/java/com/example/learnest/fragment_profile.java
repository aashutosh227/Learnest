package com.example.learnest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.net.Uri;
import android.app.Activity.*;

public class fragment_profile extends Fragment {
    private RelativeLayout cam_rellay;
    private ImageView profile_iv;
    private Context context;
    private String pathFile;
    private RoundImage roundImage;
    private Button update_btn;
    private Button delete_btn;
    private Button logout_btn;
    private Button map_btn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        context = getActivity();

        cam_rellay = view.findViewById(R.id.cam_rellay);
        profile_iv  = view.findViewById(R.id.profile_iv);
        update_btn = view.findViewById(R.id.update_pwd_btn);
        delete_btn = view.findViewById(R.id.delete_btn);
        logout_btn = view.findViewById(R.id.logout_btn);
        map_btn = view.findViewById(R.id.map_btn);

        cam_rellay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= 23){
                    requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
                }
                dispatchPictureTakeAction();
            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),updatePassword.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),deleteAccount.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MapsActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 1){
//                Bitmap bitmap  = BitmapFactory.decodeFile(pathFile);
//                profile_iv.setImageBitmap(bitmap);
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");

                profile_iv.setImageBitmap(imageBitmap);
            }
        }
    }

    private void dispatchPictureTakeAction(){

        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePic.resolveActivity(context.getPackageManager()) != null){

            startActivityForResult(takePic, 1);
//            File photofile = null;
//            photofile = null;
//            if(photofile!=null){
//                pathFile = photofile.getAbsolutePath();
//                Uri photoUri = FileProvider.getUriForFile(context,"com.example.learnest.fileprovider",photofile);
//                takePic.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
//                startActivityForResult(takePic,1);
//            }
        }
    }

    private void startUpdateAct(View v){
        Intent intent = new Intent(getActivity(),updatePassword.class);
        startActivity(intent);
    }

    private File createPhotoFile(){
        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),name);
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(name, "jpg", storageDir);
        }
        catch (IOException e){
            Log.d("mylog","excep"+e.toString());
        }
        return image;
    }


}
