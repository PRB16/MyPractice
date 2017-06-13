package cnetric.mypractice.ClassFiles;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cnetric.mypractice.R;

/**
 * Created by cnetric on 4/11/2017.
 */

public class HandleScreenOrientationFragment extends Fragment {
    private int someStateValue;
    private final String SOME_VALUE_KEY = "someValueToSave";

//    // Fires when a configuration change occurs and fragment needs to save state
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        outState.putInt(SOME_VALUE_KEY, someStateValue);
//        super.onSaveInstanceState(outState);
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        if (savedInstanceState != null) {
            someStateValue = savedInstanceState.getInt(SOME_VALUE_KEY);
            // Do something with value if needed
        }
        return view;
    }
}