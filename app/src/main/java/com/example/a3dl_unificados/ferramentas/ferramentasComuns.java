package com.example.a3dl_unificados.ferramentas;

import android.widget.EditText;

public class ferramentasComuns {

    public static int editGetInteiroCorrigido(EditText text) {
        if((text.getText().length()==0)||(String.valueOf(text.getText()).equals("-"))||(String.valueOf(text.getText()).equals(" "))){
            return 0;
        }else{
            return Integer.parseInt(String.valueOf(text.getText()));
        }
    }

    public static float editGetFloatCorrigido(EditText text) {
        if((text.getText().length()==0)||(String.valueOf(text.getText()).equals("-"))){
            return 0;
        }else{
            return Float.parseFloat(String.valueOf(text.getText()));
        }
    }

    public static void verificaNumeroEdit(EditText atual) {
        if((atual.getText().length()==0)||(String.valueOf(atual.getText()).equals("-"))){
            atual.setText("0");
        }
    }

}
