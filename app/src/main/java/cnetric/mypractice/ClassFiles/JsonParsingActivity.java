package cnetric.mypractice.ClassFiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

import cnetric.mypractice.R;

public class JsonParsingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_parsing);
    }

//    JSONArray jsonArray = json.getJSONArray("data");
//       for(int i = 0;i<jsonArray.length;i++){
//        JSONObject jsonObject = jsonArray.getJSONObject(i);
//        String name = jsonObject.getString("name"); //so you got name..
//        JSONArray nextData = jsonObject.getJSONArray("data"); //data is in array! so for loop...
//        for (int j = o; j < nextData.length; j++) {
//            JSONObject nextjsonObject = nextData.getJSONObject(j);
//            String id = nextjsonObject.getString("_id");
//            String team_1 = nextjsonObject.getString("team1");
////matchscore,match,date,everything comes like this..
//
//
//        }
//
//    }
}
