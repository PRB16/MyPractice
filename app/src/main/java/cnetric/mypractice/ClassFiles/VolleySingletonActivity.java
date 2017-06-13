package cnetric.mypractice.ClassFiles;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cnetric.mypractice.Adapter.AdapterClass;
import cnetric.mypractice.Interface.ResponseListener;
import cnetric.mypractice.PojoClass.CatalogGroupView;
import cnetric.mypractice.PojoClass.ModelClass;
import cnetric.mypractice.R;
import cnetric.mypractice.Util.GridSpacingItemDecoration;
import cnetric.mypractice.Util.NetworkConstants;

/**
 * Created by cnetric on 3/28/2017.
 */

public class VolleySingletonActivity extends AppCompatActivity implements ResponseListener {

    TextView company;
    List<CatalogGroupView> mCatalogGroupView;
    RequestQueue requestQueue;
    private GridLayoutManager gridLayoutManager;
    RecyclerView rv;
    private final String TAG = VolleySingletonActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VolleySingleton.getInstance(this).addResponseListener(VolleySingleton.CallType.GET_ALL_CHAT, this);
        idInit();
        getData();
    }

    //    Views Id Initialize Method
    private void idInit() {

        company = (TextView) findViewById(R.id.dd);
        requestQueue = Volley.newRequestQueue(this);

        gridLayoutManager = new GridLayoutManager(this, 2);
        rv = (RecyclerView) findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(gridLayoutManager);
        mCatalogGroupView = new ArrayList<CatalogGroupView>();
        int spanCount = 2; // 3 columns
        int spacing = 10; // 50px
        boolean includeEdge = false;
        rv.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

    }


    //    Get All Chat Data Method
    private void getData() {
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("user_id", "");
//        hashMap.put("wedding_id", "");
        VolleySingleton.getInstance(this).postMethod(VolleySingleton.CallType.GET_ALL_CHAT,
                NetworkConstants.getConstants(NetworkConstants.GET_ALL_CHAT)/*, hashMap*/);

    }

    @Override
    public void onSuccess(VolleySingleton.CallType type, JSONObject response) {

        try {
            if (type.equals(VolleySingleton.CallType.GET_ALL_CHAT)) {
                Log.d(TAG, "in onSuccess response ==> " + response);
                ModelClass feedListBean = new Gson().fromJson(String.valueOf(response), ModelClass.class);
                mCatalogGroupView = feedListBean.getCatalogGroupView();
                AdapterClass adapter = new AdapterClass(mCatalogGroupView, VolleySingletonActivity.this);
                adapter.notifyDataSetChanged();
                rv.setAdapter(adapter);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(VolleySingleton.CallType type, VolleyError error) {
        if (type.equals(VolleySingleton.CallType.GET_ALL_CHAT)) {

        }
    }


}