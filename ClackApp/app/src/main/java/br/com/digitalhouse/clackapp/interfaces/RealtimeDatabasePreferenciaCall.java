package br.com.digitalhouse.clackapp.interfaces;

import br.com.digitalhouse.clackapp.model.dto.PreferenceDTO;

public interface RealtimeDatabasePreferenciaCall {

    void onDataChange (PreferenceDTO preferenceDTO);

    void onDataCanceled();
}
