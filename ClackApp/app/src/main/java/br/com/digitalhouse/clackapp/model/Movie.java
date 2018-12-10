package br.com.digitalhouse.clackapp.model;

import android.content.Intent;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Movie implements Serializable{
//    private String diretor;
//    private List<String> atores;

    @SerializedName("title")
    private String nome;

    @SerializedName("original_title")
    private String nomeOriginal;

    @SerializedName("release_date")
    private String data;

    @SerializedName("poster_path")
    private String poster;

    private int posterId;

    @SerializedName("overview")
    private String sinopse;

    @SerializedName("vote_average")
    private float nota;

    @SerializedName("genre_ids")
    private List<Integer> generos;

    @SerializedName ("id")
    private Integer id;
    private String dataString;


//    private Boolean adult;
//    private Integer genre_ids;
//    private Integer id;
//    private String original_language;
//    private Boolean video;



    public Movie(){
        //Construtor vazio
    }

    public Movie(String nomeFilme, int posterId){
        this.nome = nomeFilme;
        this.posterId = posterId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

//    public String getDiretor() {
//        return diretor;
//    }
//
//    public void setDiretor(String diretor) {
//        this.diretor = diretor;
//    }
//
//    public List<String> getAtores() {
//        return atores;
//    }
//
//    public void setAtores(List<String> atores) {
//        this.atores = atores;
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public int getPosterId() {
        return posterId;
    }

    public void setPosterId(int posterId) {
        this.posterId = posterId;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public List<Integer> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Integer> generos) {
        this.generos = generos;
    }

    public void setDataString(String dataFilme) {
        dataString = dataFilme;
    }

    public String getDataString() {
        return dataString;
    }
}
