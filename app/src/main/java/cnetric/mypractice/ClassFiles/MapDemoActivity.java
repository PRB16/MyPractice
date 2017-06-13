package cnetric.mypractice.ClassFiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cnetric.mypractice.R;

public class MapDemoActivity extends AppCompatActivity {
    Button bLoc, bMap,bShowLocOnMap;
    private TrackGPS gps;
    double longitude;
    double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_demo);
        bLoc = (Button) findViewById(R.id.btnLoc);
        bMap = (Button) findViewById(R.id.btnMap);
        bShowLocOnMap = (Button) findViewById(R.id.btnLocOnMap);

        bLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                gps = new TrackGPS(MapDemoActivity.this);
//                if (gps.canGetLocation()) {
//                    longitude = gps.getLongitude();
//                    latitude = gps.getLatitude();
//                    Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_SHORT).show();
//                } else {
//                    gps.showSettingsAlert();
//                }
                Intent in = new Intent(MapDemoActivity.this, CurrentLocationActivity.class);
                startActivity(in);

            }
        });
        bMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MapDemoActivity.this, CustomMapActivity.class);
                startActivity(in);
            }
        });

        bShowLocOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MapDemoActivity.this, ShowLocOnMapActivity.class);
                startActivity(in);
            }
        });
    }
}
