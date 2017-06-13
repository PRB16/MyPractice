package cnetric.mypractice.ClassFiles.Frags;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cnetric.mypractice.PojoClass.MyTaskModel;
import cnetric.mypractice.R;

public class VideosFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private MyVideosRecyclerViewAdapter mAdapter;
    private List<MyTaskModel> myTaskModels = new ArrayList<>();
    private MyTaskModel myTaskModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public VideosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.videos_item_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        prepareData();
        return view;
    }

    public void prepareData() {
        if (myTaskModels == null) {
            myTaskModels = new ArrayList<>();
        } else {
            myTaskModels.clear();
        }
        for (int i = 0; i < 5; i++) {
            myTaskModel = new MyTaskModel();
            myTaskModel.setTitleName("Title " + (i + 1));
            myTaskModel.setsDescription("Description " + (i + 1));
            myTaskModel.setTime("" + (i +10) +" hours ago");
            myTaskModels.add(myTaskModel);
        }
        mAdapter = new MyVideosRecyclerViewAdapter(myTaskModels);
        recyclerView.setAdapter(mAdapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

    }
}
