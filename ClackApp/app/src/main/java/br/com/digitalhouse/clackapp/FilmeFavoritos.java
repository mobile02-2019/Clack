package br.com.digitalhouse.clackapp;

import android.widget.ImageView;

public class FilmeFavoritos {
    private String titulo;
    private String sinopse;
    private ImageView imageView;

    public FilmeFavoritos(String titulo, String sinopse, ImageView imageView) {
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.imageView = imageView;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
