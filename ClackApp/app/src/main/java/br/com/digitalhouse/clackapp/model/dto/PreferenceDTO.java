package br.com.digitalhouse.clackapp.model.dto;

import java.util.ArrayList;
import java.util.List;

public class PreferenceDTO {

    private List<String> itensChecados;
    private String userId;
    private String databaseKey;

    public PreferenceDTO() {
        itensChecados = new ArrayList<>();
    }

    public PreferenceDTO(List<String> itensChecados) {
        this.itensChecados = itensChecados;
    }

    public String getDatabaseKey (){
        if (!(databaseKey==null)) return databaseKey;
        if (userId == null) return  null;
        databaseKey = userId.replace(".","");
        return databaseKey;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void adicionar (String option){
        itensChecados.add(option);
    }

    public List<String> getItensChecados (){
        return itensChecados;
    }

    public void clear(){
        itensChecados.clear();
    }

    public String getItensChecadosAsString(){
        String retorno = "";
        for (String numero: itensChecados) {
            retorno += numero + ",";
        }
        return retorno;
    }
}
