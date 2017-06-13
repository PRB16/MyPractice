package cnetric.mypractice.Interface;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import cnetric.mypractice.ClassFiles.VolleySingleton;

/**
 * Created by cnetric on 3/28/2017.
 */

public interface ResponseListener {
    void onSuccess(VolleySingleton.CallType type, JSONObject response);

    void onFailure(VolleySingleton.CallType type, VolleyError error);
}
