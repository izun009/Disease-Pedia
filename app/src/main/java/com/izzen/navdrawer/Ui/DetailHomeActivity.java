package com.izzen.navdrawer.Ui;

/*
 * Nama          : M Izzudin Wijaya
 * NIM           : 10117152
 * Latest Update : 25 June 2020, 20:19:00 WIB
 * Modified      : 25 June 2020, 22:47:00 WIB
 * */

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.izzen.navdrawer.Database.FavoriteDatabase;
import com.izzen.navdrawer.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class DetailHomeActivity extends AppCompatActivity {

    private TextView mTitleName;
    private TextView mTextViewDeskripsi;
    private TextView mTextViewGejala;
    private TextView mTextViewPenyebab;
    private TextView mTextViewDiagnosis;
    private TextView mTextViewPengobatan;
    private TextView mTextViewReferensi;
    private ImageView mFotos;

    public static FavoriteDatabase favoriteDatabase;
    private RequestQueue mRequestQueue;
    private String titleName;
    private String fotoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();

        mTitleName = findViewById(R.id.text_view_title_detail);
        mFotos = findViewById(R.id.image_view_detail);
        mTextViewDeskripsi = findViewById(R.id.text_view_deskripsi);
        mTextViewGejala = findViewById(R.id.text_view_gejala);
        mTextViewPenyebab = findViewById(R.id.text_view_penyebab);
        mTextViewDiagnosis = findViewById(R.id.text_view_diagnosis);
        mTextViewPengobatan = findViewById(R.id.text_view_pengobatan);
        mTextViewReferensi = findViewById(R.id.text_view_referensi);

        Intent intent = getIntent();
        titleName = intent.getStringExtra("iTitle");
        fotoUrl = intent.getStringExtra("iFoto");

        // title dari setiap header
        actionBar.setTitle(titleName);

        mTitleName.setText(titleName);
        Picasso.with(this).load(fotoUrl).fit().centerInside().into(mFotos);

        mRequestQueue = Volley.newRequestQueue(this);
        jsonParse();

        favoriteDatabase= Room.databaseBuilder(
                getApplicationContext(),
                FavoriteDatabase.class,"myfavdb")
                .allowMainThreadQueries().build();
    }


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
                            if (titleName.equals(nama) && fotoUrl.equals(fotos)) {
                                mTextViewDeskripsi.append(tDeskripsi);
                                mTextViewGejala.append(tGejala);
                                mTextViewPenyebab.append(tPenyebab);
                                mTextViewDiagnosis.append(tDiagnosis);
                                mTextViewPengobatan.append(tPengobatan);
                                mTextViewReferensi.append(tReferensi);

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

        mRequestQueue.add(request);

    }



}