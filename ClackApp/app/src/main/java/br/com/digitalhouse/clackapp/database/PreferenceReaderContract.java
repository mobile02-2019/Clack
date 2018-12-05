package br.com.digitalhouse.clackapp.database;

import android.provider.BaseColumns;

public class PreferenceReaderContract {

        private PreferenceReaderContract() {}

        /* Inner class that defines the table contents */
        public static class PreferenceEntry implements BaseColumns {
            public static final String TABLE_NAME = "preference";
            public static final String COLUMN_NAME_PREFERENCE1 = "preference1";
            public static final String COLUMN_NAME_PREFERENCE2 = "preference2";
            public static final String COLUMN_NAME_PREFERENCE3 = "preference3";
            public static final String COLUMN_NAME_PREFERENCE4 = "preference4";
        }
}