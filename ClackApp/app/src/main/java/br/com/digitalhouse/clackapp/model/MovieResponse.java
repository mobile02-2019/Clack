package br.com.digitalhouse.clackapp.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MovieResponse {
    private Integer page;
    private ArrayList<Movie> results;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }
}
