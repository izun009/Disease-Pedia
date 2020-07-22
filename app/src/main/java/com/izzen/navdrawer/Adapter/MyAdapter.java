package com.izzen.navdrawer.Adapter;

/*
 * Nama          : M Izzudin Wijaya
 * NIM           : 10117152
 * Latest Update : 15 July 2020, 11:19:00 WIB
 * Modified      : 15 July 2020, 11:19:00 WIB
 * */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.izzen.navdrawer.CustomFilter;
import com.izzen.navdrawer.Ui.DetailHomeActivity;
import com.izzen.navdrawer.Model.FavoriteList;
import com.izzen.navdrawer.Ui.HomeFragment;
import com.izzen.navdrawer.Model.ModelItem;
import com.izzen.navdrawer.R;
import com.izzen.navdrawer.ViewHolder.MyHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> implements Filterable{

    public Context c;
    public ArrayList<ModelItem> modelItems, filterList;
    private ArrayList<FavoriteList> mExampleList;


//    CustomFilter filter;


    // parameter constructor
    public MyAdapter(Context c, ArrayList<ModelItem> modelItems) {
        this.c = c;
        this.modelItems = modelItems;
        filterList = new ArrayList<>(modelItems);
    }

    // implement method 1
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);

        return new MyHolder(view);
    }

    // implement method 2
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int i) {
        ModelItem currentItem = modelItems.get(i);

        String titleName = currentItem.getTitle();
        String imageUrl = currentItem.getImage();

        holder.mTitle.setText(titleName);
        Picasso.with(c).load(imageUrl).into(holder.mImageView);

        if (HomeFragment.favoriteDatabase.favoriteDao().isFavorite(currentItem.getId())==1)
            holder.mFav_btn.setImageResource(R.drawable.ic_favorite);
        else
            holder.mFav_btn.setImageResource(R.drawable.ic_favorite_border_black_24);

        // item detail
        holder.setItemClickListener((v, position) -> {

            String gTitle = modelItems.get(position).getTitle();
            String gFoto = modelItems.get(position).getImage();

            Intent intent = new Intent(c, DetailHomeActivity.class);
            intent.putExtra("iTitle", gTitle);
            intent.putExtra("iFoto", gFoto);
            c.startActivity(intent);
        });


        // button favorite
        holder.mFav_btn.setOnClickListener(view -> {
            FavoriteList favoriteList = new FavoriteList();

            int id = currentItem.getId();
            String title =currentItem.getTitle();
            String image = currentItem.getImage();

            favoriteList.setId(id);
            favoriteList.setTitle(title);
            favoriteList.setImage(image);

            if (HomeFragment.favoriteDatabase.favoriteDao().isFavorite(id)!=1){
                holder.mFav_btn.setImageResource(R.drawable.ic_favorite);
                HomeFragment.favoriteDatabase.favoriteDao().addData(favoriteList);

            }else {
                holder.mFav_btn.setImageResource(R.drawable.ic_favorite_border_black_24);
                HomeFragment.favoriteDatabase.favoriteDao().delete(favoriteList);

            }

        });

    }

    // implement method 3
    @Override
    public int getItemCount() {
        return modelItems.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<ModelItem> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0){
                filteredList.addAll(filterList);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (ModelItem item : filterList){
                    if (item.getTitle().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }

            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            modelItems.clear();
            modelItems.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

//    @Override
//    public Filter getFilter() {
//
//        if (filter == null){
//            filter = new CustomFilter(filterList,this);
//        }
//
//        return filter;
//    }











}
