package br.com.digitalhouse.clackapp.model;

import java.text.SimpleDateFormat;

public class FormatarData {
    public static String formateData(String data) {

        String[] split = data.split("-");
//        data = new SimpleDateFormat("dd/MM/yyyy");


        return split[2]+"/"+split[1]+"/"+split[0];
//        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
//        System.out.println(sDate1+"\t"+date1);
    }
}
