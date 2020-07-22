package com.izzen.navdrawer.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izzen.navdrawer.Interface.FavDeleteClickListener;
import com.izzen.navdrawer.Interface.FavItemClickListener;
import com.izzen.navdrawer.R;

public class FavHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView fImageView;
    public ImageView fFav_btn;
    public TextView fTitle;
    FavItemClickListener favItemClickListener;
    FavDeleteClickListener favDeleteClickListener;

    public FavHolder(@NonNull View itemView) {
        super(itemView);

        this.fImageView = itemView.findViewById(R.id.fimg_pr);
        this.fTitle = itemView.findViewById(R.id.ftv_name);
        this.fFav_btn = itemView.findViewById(R.id.del_fav_btn);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        this.favItemClickListener.onFavItemClickListener(view,getLayoutPosition());
//        this.favDeleteClickListener.onDeleteFavClickListener(view,getLayoutPosition());
    }

    public void setItemClickListener(FavItemClickListener fa){
        this.favItemClickListener = fa;
    }

//    public void setDeleteItemClickListener(FavDeleteClickListener del){
//        this.favDeleteClickListener = del;
//    }

}
