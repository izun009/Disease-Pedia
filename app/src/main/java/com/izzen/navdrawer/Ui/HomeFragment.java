package com.izzen.navdrawer.Ui;

/*
 * Nama          : M Izzudin Wijaya
 * NIM           : 10117152
 * Latest Update : 25 June 2020, 20:19:00 WIB
 * Modified      : 25 June 2020, 22:47:00 WIB
 * */


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
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
import com.izzen.navdrawer.Adapter.MyAdapter;
import com.izzen.navdrawer.Database.FavoriteDatabase;
import com.izzen.navdrawer.Model.ModelItem;
import com.izzen.navdrawer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public static FavoriteDatabase favoriteDatabase;
    private RecyclerView mRecyclerView;
    private MyAdapter mExampleAdapter;
    private ArrayList<ModelItem> mExampleList;
    private RequestQueue mRequestQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_home, container, false);

        setHasOptionsMenu(true);

        mRecyclerView = (RecyclerView) layout.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(getActivity());

        parseJson();

        favoriteDatabase = Room.databaseBuilder(getActivity()
                .getApplicationContext(),FavoriteDatabase.class,"myfavdb")
                .allowMainThreadQueries().build();

        return layout;

    }
    // end class

    private void parseJson() {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Mohon tunggu...");
        progressDialog.show();

        String url = "https://dispedia.000webhostapp.com/";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {

                    int tId;
                    String titleNama, tDeskripsi, tGejala,
                            tPenyebab, tDiagnosis, tPengobatan, tReferensi, tFoto;

                    try {
                        JSONArray jsonArray = response.getJSONArray("diseases");

                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject hit = jsonArray.getJSONObject(i);

                            tId = hit.getInt("id");
                            titleNama = hit.getString("nama");
                            tFoto = hit.getString("foto");
                            tDeskripsi = hit.getString("deskripsi");
                            tGejala = hit.getString("gejala");
                            tPenyebab = hit.getString("penyebab");
                            tDiagnosis = hit.getString("diagnosis");
                            tPengobatan = hit.getString("pengobatan");
                            tReferensi = hit.getString("referensi");

                            mExampleList.add(new ModelItem(
                                            tId, titleNama, tFoto, tDeskripsi, tGejala, tPenyebab, tDiagnosis, tPengobatan, tReferensi
                                    )
                            );

                        }

                        mExampleAdapter = new MyAdapter(getActivity(), mExampleList);
                        mRecyclerView.setAdapter(mExampleAdapter);
                        mExampleAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            error.printStackTrace();
            progressDialog.dismiss();
        }){
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {

                try {
                    Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
                    if (cacheEntry == null) {
                        cacheEntry = new Cache.Entry();
                    }
                    final long cacheHitButRefreshed = 3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
                    final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
                    long now = System.currentTimeMillis();
                    final long softExpire = now + cacheHitButRefreshed;
                    final long ttl = now + cacheExpired;
                    cacheEntry.data = response.data;
                    cacheEntry.softTtl = softExpire;
                    cacheEntry.ttl = ttl;
                    String headerValue;
                    headerValue = response.headers.get("Date");
                    if (headerValue != null) {
                        cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    headerValue = response.headers.get("Last-Modified");
                    if (headerValue != null) {
                        cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    cacheEntry.responseHeaders = response.headers;
                    final String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(new JSONObject(jsonString), cacheEntry);
                } catch (UnsupportedEncodingException | JSONException e) {
                    return Response.error(new ParseError(e));
                }
            }

            @Override
            protected void deliverResponse(JSONObject response) {
                super.deliverResponse(response);
            }

            @Override
            public void deliverError(VolleyError error) {
                super.deliverError(error);
            }

            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                return super.parseNetworkError(volleyError);
            }

        };

        mRequestQueue.add(request);

    }
    // end parseJson

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        MenuInflater inflater1 = getActivity().getMenuInflater();
        inflater1.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView= (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                mExampleAdapter.getFilter().filter(newText);

                return false;
            }
        });

    }


    // end class
}
