package cnetric.mypractice.ClassFiles;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import cnetric.mypractice.R;

/**
 * Created by cnetric on 5/16/2017.
 */

public class ChennaiIntent extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.javascriptwebviewdemo);
        WebView browser;
        browser=(WebView)findViewById(R.id.webkit);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.loadUrl("http://apps.programmerguru.com/examples/chennai.html");
    }
}