package com.example.resource.controlelivros.util;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataUtil {
    @NonNull
    public static String periodoEmTexto() {
        Calendar dataIda = Calendar.getInstance();
        Calendar dataVolta = Calendar.getInstance();
        dataVolta.add(Calendar.DATE, 2);
        SimpleDateFormat formetoBrasileiro = new SimpleDateFormat("dd/MM");
        String dataFormatIda = formetoBrasileiro.format(dataIda.getTime());
        String dataFormatVolta = formetoBrasileiro.format(dataVolta.getTime());
        return dataFormatIda +" - "+
                dataFormatVolta +" de "+
                dataIda.get(Calendar.YEAR);
    }
}
