package cnetric.mypractice.ClassFiles;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cnetric.mypractice.R;

public class RunTimePermissionActivity extends AppCompatActivity {
    //Our button
    private Button buttonRequestPermission;

    //Permision code that will be checked in the method onRequestPermissionsResult
    private int STORAGE_PERMISSION_CODE = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_time_permission);

        //Initializing button
        buttonRequestPermission = (Button) findViewById(R.id.buttonRequestPermission);

        //Adding a click listener
        buttonRequestPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //First checking if the app is already having the permission
                if (Build.VERSION.SDK_INT >= 23) {
                    if (isReadStorageAllowed()) {
                        //If permission is already having then showing the toast
                        Toast.makeText(RunTimePermissionActivity.this, "You already have the permission", Toast.LENGTH_LONG).show();
                        //Existing the method with return
                        return;
                    }

                    //If the app has not the permission then asking for the permission
                    requestStoragePermission();
                } else { //permission is automatically granted on sdk<23 upon installation
                    Log.v("PERMISSION_TAG", "Permission is granted");

                }
            }
        });
    }

    //We are calling this method to check the permission status
    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        int result2 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int result3 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;
        if (result1 == PackageManager.PERMISSION_GRANTED)
            return true;
        if (result2 == PackageManager.PERMISSION_GRANTED)
            return true;
        if (result3 == PackageManager.PERMISSION_GRANTED)
            return true;
        //If permission is not granted returning false
        return false;
    }

    //Requesting permission
    private void requestStoragePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat
                .requestPermissions(this
                        , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                                , Manifest.permission.READ_CONTACTS
                                , Manifest.permission.ACCESS_FINE_LOCATION
                                , Manifest.permission.CAMERA}, STORAGE_PERMISSION_CODE);
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {
            //If permission is granted
            if (grantResults.length > 0) {
                boolean externalStorageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean contactsAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                boolean cameraAccepted = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                boolean locationAccepted = grantResults[3] == PackageManager.PERMISSION_GRANTED;

                if (externalStorageAccepted && contactsAccepted && cameraAccepted && locationAccepted) {
                    Log.v("grantResults.length", "" + grantResults.length);
                    //Displaying a toast
                    Toast.makeText(this, "Permission granted.", Toast.LENGTH_LONG).show();
                } else {
                    //Displaying another toast if permission is not granted
                    Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
                }
            }
        }


    }
}

  /*
   //Run Time permission in another way...
   private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(MainActivity.this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MULTIPLE_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permissions granted.
                } else {
                    // no permissions granted.
                }

                return;
            }
        }
    }


    public static final int MULTIPLE_PERMISSIONS = 10;


    String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};

*/
