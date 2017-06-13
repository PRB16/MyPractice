package cnetric.mypractice.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cnetric.mypractice.PojoClass.MyTaskModel;
import cnetric.mypractice.R;

public class MyVideosRecyclerViewAdapter extends RecyclerView.Adapter<MyVideosRecyclerViewAdapter.MyViewHolder> {

    private List<MyTaskModel> mValues;

    public MyVideosRecyclerViewAdapter(List<MyTaskModel> items) {
        mValues = items;
    }

    @Override
    public MyVideosRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                       int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_videos, parent, false);
        MyVideosRecyclerViewAdapter.MyViewHolder vh = new MyVideosRecyclerViewAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyVideosRecyclerViewAdapter.MyViewHolder holder, final int position) {

        MyTaskModel myTaskModel = mValues.get(position);

        holder.description_tv.setText(myTaskModel.getsDescription());
        holder.title_tv.setText(myTaskModel.getTitleName());
        holder.time_tv.setText(myTaskModel.getTime());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title_tv, description_tv, time_tv;

        public MyViewHolder(View v) {
            super(v);
            title_tv = (TextView) v.findViewById(R.id.title_tv);
            description_tv = (TextView) v.findViewById(R.id.content);
            time_tv = (TextView) v.findViewById(R.id.time_tv);
        }


    }
}
