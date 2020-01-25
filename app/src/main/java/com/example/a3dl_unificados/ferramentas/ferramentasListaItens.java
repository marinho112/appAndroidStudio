package com.example.a3dl_unificados.ferramentas;

import android.widget.ListView;
import android.widget.TextView;

import com.example.a3dl_unificados.R;
import com.example.a3dl_unificados.objetos.objeto_3del5_item;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ferramentasListaItens {

    public static void ordenaListaItensNome(List<objeto_3del5_item> lista){

        int i,x;
        objeto_3del5_item aux;
        for(i=0;i<(lista.size()-1);i++){

            for(x=i+1;x<lista.size();x++){
                if(lista.get(i).getNome().compareTo(lista.get(x).getNome())>0){
                    aux=lista.get(x);
                    lista.set(x,lista.get(i));
                    lista.set(i,aux);
                }
            }
        }
    }

    public static void ordenaListaItensTipo(List<objeto_3del5_item> lista){

        int i,x;
        List<objeto_3del5_item> lista2=new ArrayList<>();

        for(x=0;x<6;x++) {
            for (i = 0; i < (lista.size()); i++) {
                if (lista.get(i).getTipo() == x) {
                    lista2.add(lista.get(i));
                }
            }
        }
        lista.clear();
        lista.addAll(lista2);
    }

    public static float getPesoTotal(objeto_3del5_item objeto3del5Item) {
        float pesoTotal;//definir valor do peso total
        if((objeto3del5Item.getTipo()!=3)&&(objeto3del5Item.getTipo()!=4)){
            pesoTotal = objeto3del5Item.getQuantidade() * objeto3del5Item.getPeso();
        }else{
            pesoTotal=objeto3del5Item.getPeso();
        }
        return pesoTotal;
    }

    public static void atualizaPesoTotal(TextView textViewPeso, List<objeto_3del5_item> listaItens) {
        float acumulaPeso=0;
        for(int i=0;i<listaItens.size();i++){
            acumulaPeso+=getPesoTotal(listaItens.get(i));
        }
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(1);
        format.setMinimumFractionDigits(1);
        textViewPeso.setText("PesoTotal: "+format.format(acumulaPeso)+"Kg");
    }

}
