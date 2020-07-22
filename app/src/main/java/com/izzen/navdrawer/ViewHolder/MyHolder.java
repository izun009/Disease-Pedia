package com.izzen.navdrawer.ViewHolder;

/*
 * Nama          : M Izzudin Wijaya
 * NIM           : 10117152
 * Latest Update : 25 June 2020, 20:19:00 WIB
 * Modified      : 25 June 2020, 22:47:00 WIB
 * */

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izzen.navdrawer.Interface.ItemClickListener;
import com.izzen.navdrawer.R;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView mImageView;
    public ImageView mFav_btn;
    public TextView mTitle;
    ItemClickListener itemClickListener;

    // constructor
    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.mImageView = itemView.findViewById(R.id.image_view);
        this.mTitle = itemView.findViewById(R.id.title_ill);
        this.mFav_btn = itemView.findViewById(R.id.fav_btn);
        itemView.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClickListener(v,getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener = ic;
    }

// end
}
