package cnetric.mypractice.ClassFiles;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cnetric.mypractice.Interface.ResponseListener;

/**
 * Created by cnetric on 3/28/2017.
 */

public class VolleySingleton {
    private final String TAG = VolleySingleton.class.getSimpleName();
    private static VolleySingleton instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private ArrayList<ResponseListener> mResponseListener;
    private HashMap<CallType, ResponseListener> mresponseListenerMap = new HashMap<>();

    public enum CallType {

        ALBUM_LIST,
        GET_ALL_CHAT,
        GET_CHAT_USER_LIST,
        GET_ONE_TO_ONE_CHAT,
        REMOVE_ADMIN
    }

    //Images Loader Method
    private VolleySingleton(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        /*imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(
                            20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @SuppressLint("NewApi")
                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });*/
        mResponseListener = new ArrayList<>();
    }


    public static VolleySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleySingleton(context);
        }
        return instance;
    }

    public void addResponseListener(CallType type, ResponseListener listener) {
        mresponseListenerMap.put(type, listener);
    }

    public void removeResponseListener(ResponseListener listener) {

    }

    private void notifySuccessListener(CallType type, JSONObject response) {
        mresponseListenerMap.get(type).onSuccess(type, response);
    }

    private void notifyFailureListener(CallType type, VolleyError error) {
        mresponseListenerMap.get(type).onFailure(type, error);
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag("App");
        getRequestQueue().add(req);
    }

    //Post Method Api Call
    public void postMethod(final CallType type, final String url/*, final Map<String, String> params*/) {

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

//                         convert the String response to XM L
                        // if you use Simple, something like following should do
                        // it
                        if (mResponseListener != null)
//                        if (response != null)
                            notifySuccessListener(type, response);

                        Log.d(TAG, "in onRespose response ==> " + response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // handle error response
                        if (mResponseListener != null)
                            notifyFailureListener(type, error);
                    }


                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=utf-8");
                return params;
            }

            private byte[] encodeParameters(Map<String, String> params,
                                            String paramsEncoding) {
                StringBuilder encodedParams = new StringBuilder();
                try {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        encodedParams.append(URLEncoder.encode(entry.getKey(),
                                paramsEncoding));
                        encodedParams.append('=');
                        encodedParams.append(URLEncoder.encode(
                                entry.getValue(), paramsEncoding));
                        encodedParams.append('&');
                    }
                    return encodedParams.toString().getBytes(paramsEncoding);
                } catch (UnsupportedEncodingException uee) {
                    throw new RuntimeException("Encoding not supported: "
                            + paramsEncoding, uee);
                }
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset="
                        + getParamsEncoding();
            }
        };
        addToRequestQueue(jsObjRequest);
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

}
