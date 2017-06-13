package cnetric.mypractice.ClassFiles.Frags;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cnetric.mypractice.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {
    ImageView imageView;
    Bundle args;
    int position;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getArguments();
        if (args != null) {
            position = args.getInt("position");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        if (position == 0) {
            imageView.setBackgroundResource(R.drawable.images_one);
        } else if (position == 1) {
            imageView.setBackgroundResource(R.drawable.images_two);
        } else if (position == 2) {
            imageView.setBackgroundResource(R.drawable.images_three);
        }
        return view;
    }

}
