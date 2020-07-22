package com.izzen.navdrawer.Ui;

/*
 * Nama          : M Izzudin Wijaya
 * NIM           : 10117152
 * Latest Update : 20 July 2020, 15:20:00 WIB
 * Modified      : 20 July 2020, 15:20:00 WIB
 * */

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.izzen.navdrawer.Model.FavoriteList;
import com.izzen.navdrawer.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class DetailFavoriteActivity extends AppCompatActivity {


    private TextView fTitleName;
    private TextView fTextViewDeskripsi;
    private TextView fTextViewGejala;
    private TextView fTextViewPenyebab;
    private TextView fTextViewDiagnosis;
    private TextView fTextViewPengobatan;
    private TextView fTextViewReferensi;
    private ImageView fFotos;

    private RequestQueue fRequestQueue;
    private String ftitleName;
    private String ffotoUrl;

    private FavoriteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favorite);

        ActionBar actionBar = getSupportActionBar();

        fTitleName = findViewById(R.id.textView_title_detail_favorite);
        fFotos = findViewById(R.id.imageView_detail_favorite);
        fTextViewDeskripsi = findViewById(R.id.textView_deskripsi_favorite);
        fTextViewGejala = findViewById(R.id.textView_gejala_favorite);
        fTextViewPenyebab = findViewById(R.id.textView_penyebab_favorite);
        fTextViewDiagnosis = findViewById(R.id.textView_diagnosis_favorite);
        fTextViewPengobatan = findViewById(R.id.textView_pengobatan_favorite);
        fTextViewReferensi = findViewById(R.id.textView_referensi_favorite);

        Intent intent = getIntent();
        ftitleName = intent.getStringExtra("fTitle");
        ffotoUrl = intent.getStringExtra("fFoto");

        // title dari setiap header
        actionBar.setTitle(ftitleName);

        fTitleName.setText(ftitleName);
        Picasso.with(this).load(ffotoUrl).fit().centerInside().into(fFotos);

        fRequestQueue = Volley.newRequestQueue(this);
        jsonParse();

    }
    // end class

//    private void getFavData() {
//        List<FavoriteList> favoriteLists = HomeFragment.favoriteDatabase.favoriteDao().getFavoriteData();
//        adapter = new FavoriteAdapter(favoriteLists,getApplicationContext());
//        rv.setAdapter(adapter);
//    }




    private void jsonParse() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu...");
        progressDialog.show();

        String url = "https://dispedia.000webhostapp.com/";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {

                    String nama, tDeskripsi, tGejala,
                            tPenyebab, tDiagnosis, tPengobatan, tReferensi, fotos;

                    try {
                        JSONArray jsonArray = response.getJSONArray("diseases");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject hit = jsonArray.getJSONObject(i);

                            nama = hit.getString("nama");
                            fotos = hit.getString("foto");
                            tDeskripsi = hit.getString("deskripsi");
                            tGejala = hit.getString("gejala");
                            tPenyebab = hit.getString("penyebab");
                            tDiagnosis = hit.getString("diagnosis");
                            tPengobatan = hit.getString("pengobatan");
                            tReferensi = hit.getString("referensi");

                            // cek apakah judul dengan image sama dengan yang di main
                            if (ftitleName.equals(nama) && ffotoUrl.equals(fotos)) {
                                fTextViewDeskripsi.append(tDeskripsi);
                                fTextViewGejala.append(tGejala);
                                fTextViewPenyebab.append(tPenyebab);
                                fTextViewDiagnosis.append(tDiagnosis);
                                fTextViewPengobatan.append(tPengobatan);
                                fTextViewReferensi.append(tReferensi);

                            }

                        }

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

        fRequestQueue.add(request);

    }


}