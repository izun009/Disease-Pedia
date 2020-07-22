package com.izzen.navdrawer.Model;

/*
 * Nama          : M Izzudin Wijaya
 * NIM           : 10117152
 * Latest Update : 25 June 2020, 20:19:00 WIB
 * Modified      : 25 June 2020, 22:47:00 WIB
 * */


public class ModelItem {
    private int id;
    private String title;
    private String image;
    private String Deskripsi;
    private String Gejala;
    private String Penyebab;
    private String Diagnosis;
    private String Pengobatan;
    private String Referensi;


    public ModelItem(int id, String title, String image, String Deskripsi, String Gejala, String Penyebab,
                     String Diagnosis, String Pengobatan, String Referensi){

        this.id = id;
        this.title = title;
        this.image = image;
        this.Deskripsi = Deskripsi;
        this.Gejala = Gejala;
        this.Penyebab = Penyebab;
        this.Diagnosis = Diagnosis;
        this.Pengobatan = Pengobatan;
        this.Referensi = Referensi;

    }

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

    public String getPenyebab() {
        return Penyebab;
    }

    public void setPenyebab(String penyebab) {
        Penyebab = penyebab;
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