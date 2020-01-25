package com.example.a3dl_unificados.ferramentas;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.a3dl_unificados.objetos.objeto_3del5_personagem;

import java.util.ArrayList;

public class ferramentas3del5 {

    public static String converteNumeroParaAtributo3del5(int numero){
        switch (numero){
            case 0: return "Força";
            case 1: return "Constituição";
            case 2: return "Destreza";
            case 3: return "Agilidade";
            case 4: return "Sentidos";
            case 5: return "Inteligência";
        }
        return"";
    }

    public static int valorDoSpinnerAtributo(int selectedItemPosition, objeto_3del5_personagem personagem) {
        switch (selectedItemPosition){
            case 0: return personagem.getForca()+personagem.getForcaBonus();
            case 1: return personagem.getVitalidade()+personagem.getVitalidadeBonus();
            case 2: return personagem.getDestreza()+personagem.getDestrezaBonus();
            case 3: return personagem.getAgilidade()+personagem.getAgilidadeBonus();
            case 4: return personagem.getSentidos()+personagem.getSentidosBonus();
            case 5: return personagem.getInteligengia()+personagem.getInteligengiaBonus();
            default: return 0;
        }
    }

    public static ArrayAdapter<String> obterAdapterSpinnerAtributo(Context context){
        ArrayList<String> listaSpinner= criarListaSpinnerAtributo();
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item,listaSpinner);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return  stringArrayAdapter;
    }

    public static ArrayList<String> criarListaSpinnerAtributo() {
        ArrayList<String> listaSpinner=new ArrayList<>();
        listaSpinner.add("Força");
        listaSpinner.add("Constituição");
        listaSpinner.add("Destreza");
        listaSpinner.add("Agilidade");
        listaSpinner.add("Sentidos");
        listaSpinner.add("Inteligência");


        return listaSpinner;
    }

    public static String converteNumeroParaTipo3del5(int numero){
        switch (numero){
            case 0: return "Física";
            case 1: return "Intelectual";
        }
        return"";
    }


    public static ArrayAdapter<String> obterAdapterSpinnerTipo(Context context){
        ArrayList<String> listaSpinner= criarListaSpinnerTipo();
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item,listaSpinner);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return  stringArrayAdapter;
    }

    public static ArrayList<String> criarListaSpinnerTipo() {
        ArrayList<String> listaSpinner=new ArrayList<>();
        listaSpinner.add("Física");
        listaSpinner.add("Intelectual");

        return listaSpinner;
    }


    public static void alerta(String texto, Context context){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage(texto);
        alertDialog.show();
    }

    public static boolean validaString(String texto,String nomeCampo , Context context){
        if(texto.isEmpty()){
            alerta("Campo "+nomeCampo+" Está Vazio!",context);
            return false;
        }else{return true;}
    }
}
