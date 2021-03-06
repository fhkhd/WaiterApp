package com.example.waiterapp.activity.file;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.waiterapp.R;
import com.example.waiterapp.helper.Tools;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;


public class FileActivity extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_CODE = 101;
//    private static final int REQUEST_PERMISSION_SETTING = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

//        if (checkPermission()){
//            File file = new File(Environment.getExternalStorageDirectory() + "/" + File.separator + "barista");
//            file.exists();
//            if(file.exists()){
//                Log.e("aaa" , "create" + "/barista");
//            }else{
//                Log.e("aaa" , "not existed");
//            }
//        }

        File file = new File(getFilesDir() , "amin33");
        file.mkdir();

        if (file.exists()) Log.e("qqqq", "onCreate: exists" );
        else Log.e("qqqq", "onCreate: not exists " );

        Log.e("qqqq", "onCreate: " + file.getPath() );

        if (checkPermission()){

        }

        File file1 = new File(Environment.getExternalStorageDirectory()+"/test.png");

        ImageView imageView = findViewById(R.id.imageview);

        Glide.with(this).load(file1).into(imageView);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                String save = Tools.saveFile(Tools.getBytes(resultUri), Environment.getExternalStorageDirectory(),"test.png");
                Log.e("qqqqfile", "onActivityResult: " + save );
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);


        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("desc_need_permission");
                builder.setPositiveButton("ok_permission", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, STORAGE_PERMISSION_CODE);
                    }
                });
                builder.setNegativeButton("exit_app", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        finish();
                    }
                });
                builder.show();
            }
        }
    }


    public void show(View view) {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
    }

    public Boolean checkPermission() {
        String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int requestCode = STORAGE_PERMISSION_CODE;
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] { permission }, requestCode);
        }
        else {
            return true;
        }
        return false;
    }
}