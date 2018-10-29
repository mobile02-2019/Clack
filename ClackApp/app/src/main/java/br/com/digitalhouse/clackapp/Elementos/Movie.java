package br.com.digitalhouse.clackapp.Elementos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Movie implements Serializable {

    private String nome;
    private String diretor;
    private List<String> atores;
    private Date data;
    private int poster;
    private String sinopse;

    public Movie(){
        //Construtor vazio
    }

    public Movie(String nomeFilme, int posterFilme){
        this.nome = nomeFilme;
        this.poster = posterFilme;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public List<String> getAtores() {
        return atores;
    }

    public void setAtores(List<String> atores) {
        this.atores = atores;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }
}
