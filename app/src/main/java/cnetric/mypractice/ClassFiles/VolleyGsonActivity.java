package cnetric.mypractice.ClassFiles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cnetric.mypractice.Adapter.AdapterClass;
import cnetric.mypractice.R;
import cnetric.mypractice.PojoClass.CatalogGroupView;
import cnetric.mypractice.Util.GridSpacingItemDecoration;
import cnetric.mypractice.PojoClass.ModelClass;

/**
 * Created by cnetric on 3/21/2017.
 */

public class VolleyGsonActivity extends AppCompatActivity {
    String url = "http://35.154.52.107/wcs/resources/store/10701/";

    TextView company;
    TextView blog;
    TextView htmlurl;
    List<CatalogGroupView> mCatalogGroupView;
    String sName, sIdentifier;
    RequestQueue requestQueue;
    private GridLayoutManager gridLayoutManager;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        requestVolleyToGetProductDetails(url + "categoryview/@top");

    }

    private void requestVolleyToGetProductDetails(String url) {
        JSONObject jsonBody = null;

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        if (response != null && response.length() > 0) {
                            ModelClass feedListBean = new Gson().fromJson(String.valueOf(response), ModelClass.class);
                            mCatalogGroupView = feedListBean.getCatalogGroupView();
                            AdapterClass adapter = new AdapterClass(mCatalogGroupView, VolleyGsonActivity.this);
                            adapter.notifyDataSetChanged();
                            rv.setAdapter(adapter);
                        } else {

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String json = null;
                        //8888 String errorMsg = error.networkResponse.data;
                        NetworkResponse response = error.networkResponse;
                        if (response != null && response.data != null) {
                            switch (response.statusCode) {
                                case 400:
                                    json = new String(response.data);
                                    json = trimMessage(json, "errors");
                                    if (json != null) displayMessage(json);
                                    break;
                            }
                            //Additional cases
                        }
                    }

                    public String trimMessage(String json, String key) {
                        String trimmedString = null;

                        try {
                            JSONObject obj = new JSONObject(json);
                            JSONArray jsonArray = obj.getJSONArray(key);
                            JSONObject errorJsonObject = jsonArray.getJSONObject(0);
                            trimmedString = errorJsonObject.getString("errorMessage");

                        } catch (JSONException e) {
                            e.printStackTrace();
                            return null;
                        }

                        return trimmedString;
                    }

                    //Somewhere that has access to a context
                    public void displayMessage(String toastString) {
                        Toast.makeText(VolleyGsonActivity.this, toastString, Toast.LENGTH_LONG).show();
//                        Log.v("SERVER RESPONSE", toastString);

                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=utf-8");
                return params;
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                //mStatusCode = response.statusCode;
//                Log.i("statusCode", String.valueOf(mStatusCode));
                return super.parseNetworkResponse(response);
            }
        };
        requestQueue.add(jsObjRequest);

    }
}
