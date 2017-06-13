package cnetric.mypractice.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import cnetric.mypractice.R;
import cnetric.mypractice.PojoClass.CatalogGroupView;

/**
 * Created by cnetric on 11/23/2016.
 */
public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {

    private List<CatalogGroupView> mClassModels;
    Context mContext;
    String sProdImage;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout lCategory;
        public TextView tCategoryName, tCategoryItemName;
        public ImageView iCategoryIcon;

        public MyViewHolder(View v) {
            super(v);

            tCategoryName = (TextView) v.findViewById(R.id.tvCategories);
            //tCategoryItemName = (TextView) v.findViewById(R.id.tvCategoriesItem);
            lCategory = (RelativeLayout) v.findViewById(R.id.llCategory);
            iCategoryIcon = (ImageView) v.findViewById(R.id.ivCatImage);

        }
    }

    public AdapterClass(List<CatalogGroupView> classModels, Context context) {
        mClassModels = classModels;
        mContext = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categories_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        CatalogGroupView classModel = mClassModels.get(position);
        sProdImage = "http://35.154.52.107/wcsstore/ExtendedSitesCatalogAssetStore/" + classModel.getThumbnail();
        holder.tCategoryName.setText(classModel.getName());
//        holder.tCategoryItemName.setText(classModel.getsItemCategory());
        Glide.with(mContext)
                .load(sProdImage)
                .override(200, 200)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.iCategoryIcon);
        /*holder.lCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(), ProductbyParentCategoryActivity.class);
                in.putExtra("CategoryName", mClassModels.get(position).getsCategoryName());
                in.putExtra("CategoryId", mClassModels.get(position).getsId());
                v.getContext().startActivity(in);
            }
        });*/

    }


    @Override
    public int getItemCount() {
        return mClassModels.size();
    }
}