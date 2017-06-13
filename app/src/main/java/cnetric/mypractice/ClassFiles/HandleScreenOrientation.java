package cnetric.mypractice.ClassFiles;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by cnetric on 4/11/2017.
 */

public class HandleScreenOrientation extends Activity {
    static final String SOME_VALUE = "int_value";
    static final String SOME_OTHER_VALUE = "string_value";
    int someIntValue = 32;
    String someStringValue = "hii";

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // Save custom values into the bundle
        savedInstanceState.putInt(SOME_VALUE, someIntValue);
        savedInstanceState.putString(SOME_OTHER_VALUE, someStringValue);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
        // Restore state members from saved instance
        someIntValue = savedInstanceState.getInt(SOME_VALUE);
        someStringValue = savedInstanceState.getString(SOME_OTHER_VALUE);
    }
}