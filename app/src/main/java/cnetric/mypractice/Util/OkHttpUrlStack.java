package cnetric.mypractice.Util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.toolbox.HurlStack;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import cnetric.mypractice.R;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * Created by cnetric on 9/2/2016.
 */
public class OkHttpUrlStack {

    private static final int CONNECT_TIMEOUT_MILLIS = 60 * 1000; // 30s
    private static final int READ_TIMEOUT_MILLIS = 85 * 1000; // 45s
    // private final OkUrlFactory okUrlFactory;
    private static Context context;

    public OkHttpUrlStack(Context mContext) {
        //this(new OkHttpClient(), mContext);
        context = mContext;
    }


    public static OkHttpClient getOkHttpClient() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {


        OkHttpClient client = new OkHttpClient
                .Builder().hostnameVerifier(hostNameVerifire())
                .sslSocketFactory(getSslContext().getSocketFactory(),
                        getTrustManagers(getKeyStore()))
                .build();
        return client;


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

    public static SSLContext getSslContext() throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, KeyManagementException {
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

    public static KeyStore getKeyStore() throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException {
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

    public static X509TrustManager getTrustManagers(KeyStore keyStore) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
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
}