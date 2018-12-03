package br.com.digitalhouse.clackapp.database;

import android.provider.BaseColumns;

public final class FilmesFavoritosContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FilmesFavoritosContract() {}

    /* Inner class that defines the table contents */
    public static class FilmesFavoritosEntry implements BaseColumns {
        public static final String TABLE_NAME = "filmes_favoritos";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_DATE = "data";
        public static final String COLUMN_POSTER = "poster";
        public static final String COLUMN_NOTA = "nota";
        public static final String COLUMN_GENERO = "genero";
        public static final String COLUMN_SINOPSE = "sinopse";

    }
}