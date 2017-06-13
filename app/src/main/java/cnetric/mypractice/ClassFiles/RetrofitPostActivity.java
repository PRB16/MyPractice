package cnetric.mypractice.ClassFiles;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import cnetric.mypractice.Interface.AInterface;
import cnetric.mypractice.PojoClass.RetroPostResponseModel;
import cnetric.mypractice.R;
import cnetric.mypractice.Util.OkHttpUrlStack;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class RetrofitPostActivity extends AppCompatActivity {
    private EditText etLogonId, etpassword;
    private Button btSignIn;
    String sEnterUserName, sEnterPassword;
    static String url = "https://35.154.52.107/wcs/resources/store/10701/";
    String sUserId, sWcToken, sWcTrustedToken, sPersonalizationID,
            sAddressId, sWcUnRegToken, sWcUnRegTrustedToken;

    OkHttpUrlStack okHttpUrlStack;
    private static final int CONNECT_TIMEOUT_MILLIS = 60 * 1000; // 30s
    private static final int READ_TIMEOUT_MILLIS = 85 * 1000; // 45s
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_post);

        etLogonId = (EditText) findViewById(R.id.etUserName);
        etpassword = (EditText) findViewById(R.id.etPwd);
        btSignIn = (Button) findViewById(R.id.btnSignIn);
        context = RetrofitPostActivity.this;

        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sEnterUserName = etLogonId.getText().toString().trim();
                sEnterPassword = etpassword.getText().toString().trim();
                try {
                    emitt();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //public static final String login = "loginidentity";//POST
   /* void postRetrofitArray() throws JSONException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        okHttpUrlStack = new OkHttpUrlStack(getApplicationContext());


        JSONObject map = new JSONObject();
        map.put("logonId", sEnterUserName);
        map.put("logonPassword", sEnterPassword);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), map.toString());

        OkHttpClient client = new OkHttpClient
                .Builder().hostnameVerifier(okHttpUrlStack.hostNameVerifire())
                .sslSocketFactory(okHttpUrlStack.getSslContext().getSocketFactory(),
                        okHttpUrlStack.getTrustManagers(okHttpUrlStack.getKeyStore()))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AInterface service = retrofit.create(AInterface.class);
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(API_URL).client(client)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//

        Call<RetroPostResponseModel> call = (Call<RetroPostResponseModel>) service.postDetails(body);
//        Response<RetroPostResponseModel> execute = call.execute();
//        System.out.println(execute.isSuccessful() ? execute.body() : execute.errorBody().string());
        call.enqueue(new Callback<RetroPostResponseModel>() {
            @Override
            public void onResponse(Call<RetroPostResponseModel> call, Response<RetroPostResponseModel> response) {
                Log.d("onResponse", "onResponse is calling");

                try {
                    sUserId = response.body().getUserId();
                    sWcToken = response.body().getWCToken();
                    sWcTrustedToken = response.body().getWCTrustedToken();
                    sPersonalizationID = response.body().getPersonalizationID();

                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<RetroPostResponseModel> call, Throwable t) {
                Log.d("onFailure", "onFailure is calling");
                Log.d("onFailure", t.toString());
            }

        });
    }
*/
    private void emitt() {
        try {
            //String json = "{\"logonId\":username,\"logonPassword\":password}";
            JSONObject jsonBody = null;
            try {

                jsonBody = new JSONObject();
                jsonBody.put("logonId", sEnterUserName);
                jsonBody.put("logonPassword", sEnterPassword);
                Log.d("jsonBody", "" + jsonBody);
            } catch (Exception e) {

            }

            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonBody.toString());

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            OkHttpClient client = new OkHttpClient
                    .Builder().hostnameVerifier(hostNameVerifire())
                    .sslSocketFactory(getSslContext().getSocketFactory(),
                            getTrustManagers(getKeyStore()))
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url).client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            ApiInterface service = retrofit.create(ApiInterface.class);
            Call<RetroPostResponseModel> responseBodyCall = service.postDetails(body);


//            Call<RetroPostResponseModel> call = (Call<RetroPostResponseModel>) service.postDetails(json);
//        Response<RetroPostResponseModel> execute = call.execute();
//        System.out.println(execute.isSuccessful() ? execute.body() : execute.errorBody().string());
            responseBodyCall.enqueue(new Callback<RetroPostResponseModel>() {
                @Override
                public void onResponse(Call<RetroPostResponseModel> call, Response<RetroPostResponseModel> response) {
                    Log.d("onResponse", "onResponse is calling" + response.body());

                    try {
                        if (response.isSuccessful()) {
                            sUserId = response.body().getUserId();
                            sWcToken = response.body().getWCToken();
                            sWcTrustedToken = response.body().getWCTrustedToken();
                            sPersonalizationID = response.body().getPersonalizationID();
                            Log.d("onResponse", sUserId + "--" + sWcToken + "--" + sWcTrustedToken);
                        } else {
                            Toast.makeText(RetrofitPostActivity.this, "Dialog fragment dismissed!", Toast.LENGTH_LONG).toString();
                        }
                    } catch (Exception e) {
                        Log.d("onResponse", "There is an error");
                        e.printStackTrace();
                    }


                }

                @Override
                public void onFailure(Call<RetroPostResponseModel> call, Throwable t) {
                    Log.d("onFailure", "onFailure is calling");
                    Log.d("onFailure", t.toString());
                }

            });
//            Response<ResponseBody> execute = responseBodyCall.execute();
//
//            System.out.println(execute.isSuccessful() ? execute.body().string() : execute.errorBody().string());
        } catch (CertificateException | KeyStoreException | IOException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    public static HostnameVerifier hostNameVerifire() {
        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                //   Log.i(TAG, "HOST NAME " + hostname);
                HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                return true;
            }
        };
        return hostnameVerifier;
    }

    private static SSLContext getSslContext() throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, KeyManagementException {
        KeyStore keyStore = getKeyStore();
        // Create a TrustManager that trusts the CAs in our KeyStore
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);
        TrustManager[] wrappedTrustManagers = getWrappedTrustManagers(tmf.getTrustManagers());
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, wrappedTrustManagers, null);
        return sslContext;

    }

    private static KeyStore getKeyStore() throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException {
        // Load CAs from an InputStream
// (could be from a resource or ByteArrayInputStream or ...)
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
// From https://www.washington.edu/itconnect/security/ca/load-der.crt
        //InputStream caInput = new BufferedInputStream(new FileInputStream("D:\\android-workspace\\MyApplication\\expermental\\src\\main\\java\\com\\example\\aurora.crt"));

        InputStream caInput = context.getResources().openRawResource(R.raw.aurora);
        Certificate ca;
        try {
            ca = cf.generateCertificate(caInput);
            System.out.println("ca=" + ((java.security.cert.X509Certificate) ca).getSubjectDN());
        } finally {
            caInput.close();
        }
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", ca);
        return keyStore;
    }

    private static X509TrustManager getTrustManagers(KeyStore keyStore) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        // Create a TrustManager that trusts the CAs in our KeyStore
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);


        return (X509TrustManager) tmf.getTrustManagers()[0];
    }

    private static TrustManager[] getWrappedTrustManagers(TrustManager[] trustManagers) {
        final X509TrustManager originalTrustManager = (X509TrustManager) trustManagers[0];
        return new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return originalTrustManager.getAcceptedIssuers();
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        try {
                            if (certs != null && certs.length > 0) {
                                certs[0].checkValidity();
                            } else {
                                originalTrustManager.checkClientTrusted(certs, authType);
                            }
                        } catch (CertificateException e) {
//                            Log.w("checkClientTrusted", e.toString());
                        }
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        try {
                            if (certs != null && certs.length > 0) {
                                certs[0].checkValidity();
                            } else {
                                originalTrustManager.checkServerTrusted(certs, authType);
                            }
                        } catch (CertificateException e) {
//                            Log.w("checkServerTrusted", e.toString());
                        }
                    }
                }
        };
    }

    public interface ApiInterface {
        @POST("loginidentity")
        Call<RetroPostResponseModel> postDetails(@Body RequestBody retroPost);
    }

}
