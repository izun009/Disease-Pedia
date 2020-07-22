package com.izzen.navdrawer.Model;

/*
 * Nama          : M Izzudin Wijaya
 * NIM           : 10117152
 * Latest Update : 15 July 2020, 14:30:00 WIB
 * Modified      : 15 July 2020, 10:40:00 WIB
 * */


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="favoritelist")
public class FavoriteList {



    @PrimaryKey
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "description")
    private String Deskripsi;

    @ColumnInfo(name = "gejala")
    private String Gejala;

    @ColumnInfo(name = "penyebab")
    private String Penyabab;

    @ColumnInfo(name = "diagnosis")
    private String Diagnosis;

    @ColumnInfo(name = "pengobatan")
    private String Pengobatan;

    @ColumnInfo(name = "referensi")
    private String Referensi;



//    public FavoriteList(int id, String title, String image, String Deskripsi, String Gejala,
//                        String Penyabab, String Diagnosis, String Pengobatan, String Referensi) {
//        this.id = id;
//        this.title = title;
//        this.image = image;
//        this.Deskripsi = Deskripsi;
//        this.Gejala = Gejala;
//        this.Penyabab = Penyabab;
//        this.Diagnosis = Diagnosis;
//        this.Pengobatan = Pengobatan;
//        this.Referensi = Referensi;
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getGejala() {
        return Gejala;
    }

    public void setGejala(String gejala) {
        Gejala = gejala;
    }

    public String getPenyabab() {
        return Penyabab;
    }

    public void setPenyabab(String penyabab) {
        Penyabab = penyabab;
    }

    public String getDiagnosis() {
        return Diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        Diagnosis = diagnosis;
    }

    public String getPengobatan() {
        return Pengobatan;
    }

    public void setPengobatan(String pengobatan) {
        Pengobatan = pengobatan;
    }

    public String getReferensi() {
        return Referensi;
    }

    public void setReferensi(String referensi) {
        Referensi = referensi;
    }
}
