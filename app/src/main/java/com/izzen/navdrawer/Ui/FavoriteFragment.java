package com.izzen.navdrawer.Ui;

/*
 * Nama          : M Izzudin Wijaya
 * NIM           : 10117152
 * Latest Update : 15 July 2020, 12:19:00 WIB
 * Modified      : 25 June 2020, 22:47:00 WIB
 * */

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.izzen.navdrawer.Adapter.FavoriteAdapter;
import com.izzen.navdrawer.Adapter.MyAdapter;
import com.izzen.navdrawer.Database.FavoriteDatabase;
import com.izzen.navdrawer.Model.FavoriteList;
import com.izzen.navdrawer.Model.ModelItem;
import com.izzen.navdrawer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {


    ImageView btn_fav_del;
    public static FavoriteDatabase favoriteDatabase;
    private RecyclerView rv;
    private FavoriteAdapter adapter;
    private RequestQueue fRequestQueue;

//    private RecyclerView mRecyclerView;
//    private FavoriteAdapter favoriteAdapter;
    private ArrayList<FavoriteList> favoriteLists;
//    private RequestQueue mRequestQueue;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_favorite, container, false);

        btn_fav_del = (ImageView) layout.findViewById(R.id.del_fav_btn);
        rv = (RecyclerView) layout.findViewById(R.id.rec);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        favoriteLists = new ArrayList<>();
        fRequestQueue = Volley.newRequestQueue(getActivity());

        favoriteDatabase = Room.databaseBuilder(getActivity()
                .getApplicationContext(), FavoriteDatabase.class,"myfavdb")
                .allowMainThreadQueries().build();



        getFavData();

//        parseJson();

        return layout;

    }

    // end class


    private void getFavData() {
        List<FavoriteList> favoriteLists = HomeFragment.favoriteDatabase.favoriteDao().getFavoriteData();
        adapter = new FavoriteAdapter(favoriteLists, getActivity().getApplicationContext());
        rv.setAdapter(adapter);

    }
//     end getFavData









































}
