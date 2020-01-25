package com.example.a3dl_unificados.ferramentas;

import com.example.a3dl_unificados.objetos.objeto_3del5_pericia;

import java.util.ArrayList;
import java.util.List;

public class ferramentasListaPericias {


    public static void  ordenaListaPericiasNome(List<objeto_3del5_pericia> lista){

        int i;
        List<objeto_3del5_pericia> lista2=new ArrayList<>();
        int menor,maior,proximoMenor;
        ArrayList<Integer> candidatos = new ArrayList<>();
        proximoMenor=0;maior=0;


        for(i=0;i<(lista.size());i++){
            if((lista.get(i).getNome().compareTo(lista.get(proximoMenor).getNome())<0)){
                proximoMenor=i;
            }
        }
        for(i=0;i<(lista.size());i++){
            if((lista.get(i).getNome().compareTo(lista.get(maior).getNome())>0)){
                maior=i;
            }
        }
        if(lista.size()!=0) {
            do {
                menor = proximoMenor;
                candidatos.clear();
                proximoMenor = maior;
                for (i = 0; i < lista.size(); i++) {
                    if (lista.get(i).getNome().compareTo(lista.get(menor).getNome()) == 0) {
                        candidatos.add(i);
                    }
                    if ((lista.get(i).getNome().compareTo(lista.get(menor).getNome()) > 0) && ((lista.get(i).getNome().compareTo(lista.get(proximoMenor).getNome()) < 0))) {
                        proximoMenor = i;
                    }
                }
                for (i = 0; i < candidatos.size(); i++) {
                    lista2.add(lista.get(candidatos.get(i)));
                }

            } while ((lista.get(menor).getNome().compareTo(lista.get(maior).getNome()) < 0));
        }
        lista.clear();
        lista.addAll(lista2);

    }

    public static void ordenaListaPericiasTipo(List<objeto_3del5_pericia> lista){

        int i;
        List<objeto_3del5_pericia> lista2=new ArrayList<>();
        int menor,maior,proximoMenor;
        ArrayList<Integer> candidatos = new ArrayList<>();
        proximoMenor=0;maior=0;
        for(i=0;i<(lista.size());i++){
            if(lista.get(i).getTipo()<lista.get(proximoMenor).getTipo()){
                proximoMenor=i;
            }
        }
        for(i=0;i<(lista.size());i++){
            if(lista.get(i).getTipo()>lista.get(maior).getTipo()){
                maior=i;
            }
        }

        if(lista.size()!=0) {
            do {
                menor = proximoMenor;
                candidatos.clear();
                proximoMenor = maior;
                for (i = 0; i < lista.size(); i++) {
                    if (lista.get(i).getTipo() == lista.get(menor).getTipo()) {
                        candidatos.add(i);
                    }
                    if ((lista.get(i).getTipo() > lista.get(menor).getTipo()) && (lista.get(i).getTipo() < lista.get(proximoMenor).getTipo())) {
                        proximoMenor = i;
                    }
                }
                for (i = 0; i < candidatos.size(); i++) {
                    lista2.add(lista.get(candidatos.get(i)));
                }

            } while (lista.get(menor).getTipo() < lista.get(maior).getTipo());
        }
        lista.clear();
        lista.addAll(lista2);


    }


    public static void ordenaListaPericiasAtributo(List<objeto_3del5_pericia> lista){

        int i;
        List<objeto_3del5_pericia> lista2=new ArrayList<>();
        int menor,maior,proximoMenor;
        ArrayList<Integer> candidatos = new ArrayList<>();
        proximoMenor=0;maior=0;
        for(i=0;i<(lista.size());i++){
            if(lista.get(i).getAtributoPrincipal()<lista.get(proximoMenor).getAtributoPrincipal()){
                proximoMenor=i;
            }
        }
        for(i=0;i<(lista.size());i++){
            if(lista.get(i).getAtributoPrincipal()>lista.get(maior).getAtributoPrincipal()){
                maior=i;
            }
        }
        if(lista.size()!=0) {
            do {
                menor = proximoMenor;
                candidatos.clear();
                proximoMenor = maior;
                for (i = 0; i < lista.size(); i++) {
                    if (lista.get(i).getAtributoPrincipal() == lista.get(menor).getAtributoPrincipal()) {
                        candidatos.add(i);
                    }
                    if ((lista.get(i).getAtributoPrincipal() > lista.get(menor).getAtributoPrincipal()) && (lista.get(i).getAtributoPrincipal() < lista.get(proximoMenor).getAtributoPrincipal())) {
                        proximoMenor = i;
                    }
                }
                for (i = 0; i < candidatos.size(); i++) {
                    lista2.add(lista.get(candidatos.get(i)));
                }

            } while (lista.get(menor).getAtributoPrincipal() < lista.get(maior).getAtributoPrincipal());
        }
        lista.clear();
        lista.addAll(lista2);

    }
}
