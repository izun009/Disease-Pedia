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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izzen.navdrawer.Interface.FavDeleteClickListener;
import com.izzen.navdrawer.Model.FavoriteList;
import com.izzen.navdrawer.R;
import com.izzen.navdrawer.Ui.DetailFavoriteActivity;
import com.izzen.navdrawer.Ui.DetailHomeActivity;
import com.izzen.navdrawer.Ui.HomeFragment;
import com.izzen.navdrawer.ViewHolder.FavHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavHolder> {
    private List<FavoriteList> favoriteLists;
    Context context;
    FavDeleteClickListener onDeleteClickListener;

    public FavoriteAdapter(List<FavoriteList> favoriteLists, Context context) {
        this.favoriteLists = favoriteLists;
        this.context = context;
    }

    @NonNull
    @Override
    public FavHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favorite_list,viewGroup,false);
        return new FavHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavHolder viewHolder, int i) {
        FavoriteList fl = favoriteLists.get(i);

        int idFav = fl.getId();
        String ftitleName = fl.getTitle();
        String fimageUrl = fl.getImage();

        viewHolder.fTitle.setText(ftitleName);
        Picasso.with(context).load(fimageUrl).into(viewHolder.fImageView);

        if (HomeFragment.favoriteDatabase.favoriteDao().isFavorite(fl.getId())==1)
            viewHolder.fFav_btn.setImageResource(R.drawable.ic_favorite);
        else
            viewHolder.fFav_btn.setImageResource(R.drawable.ic_favorite_border_black_24);

        viewHolder.setItemClickListener((v, position) -> {

            String favTitle = favoriteLists.get(position).getTitle();
            String favFoto = favoriteLists.get(position).getImage();

            // tandain
            Intent intent = new Intent(context, DetailFavoriteActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("fTitle", favTitle);
            intent.putExtra("fFoto", favFoto);
            context.startActivity(intent);
        });




//        viewHolder.fFav_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FavoriteList favoriteList = new FavoriteList();
//
//                int id = fl.getId();
//                String title =fl.getTitle();
//                String image = fl.getImage();
//
//                favoriteList.setId(id);
//                favoriteList.setTitle(title);
//                favoriteList.setImage(image);
//
//                if (onDeleteClickListener != null){
//                    onDeleteClickListener.onDeleteFavClickListener(fl.getId());
//                }
//            }
//        });


        viewHolder.fFav_btn.setOnClickListener(view -> {

            FavoriteList favoriteList = new FavoriteList();

            int id = fl.getId();
            String title =fl.getTitle();
            String image = fl.getImage();

            favoriteList.setId(id);
            favoriteList.setTitle(title);
            favoriteList.setImage(image);


            if (HomeFragment.favoriteDatabase.favoriteDao().isFavorite(id)==1) {
                viewHolder.fFav_btn.setImageResource(R.drawable.ic_favorite_border_black_24);
                HomeFragment.favoriteDatabase.favoriteDao().delete(favoriteList);
                favoriteLists.remove(favoriteList);
                notifyItemRemoved(id);
                notifyItemRangeChanged(id, favoriteLists.size());
                notifyDataSetChanged();
                Toast.makeText(context, "Removed, Please back back !", Toast.LENGTH_SHORT).show();
            }
//            else {
//                viewHolder.fFav_btn.setImageResource(R.drawable.ic_favorite);
//                HomeFragment.favoriteDatabase.favoriteDao().getFavoriteData();
//            }


        });




    }

    private void removeItem(FavoriteList infodata, FavHolder favHolder) {

        ;

    }

    @Override
    public int getItemCount() {
        return favoriteLists.size();
    }

//    public class ViewHolder extends RecyclerView.ViewHolder{
//        ImageView img;
//        TextView tv;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            img=(ImageView)itemView.findViewById(R.id.fimg_pr);
//            tv=(TextView)itemView.findViewById(R.id.ftv_name);
//        }
//    }












}
// end class