package br.com.digitalhouse.clackapp.Elementos;

import android.widget.ImageView;

public class FilmeFavorito {
    private String titulo;
    private String sinopse;
    private int imageView;

    public FilmeFavorito(String titulo, String sinopse, int imageView) {
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.imageView = imageView;
    }

    public FilmeFavorito() {
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

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }
}
