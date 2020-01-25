package com.example.a3dl_unificados.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.a3dl_unificados.R;
import com.example.a3dl_unificados.adapters.AdapterListaPericias3del5;
import com.example.a3dl_unificados.adapters.AdapterPersonagem3del5;


public class fragment_personagem_3del5_lista_pericias extends Fragment {

    private AdapterPersonagem3del5 adapter;
    private fragment_personagem_3del5_atributos viewAnterior;
    private AdapterListaPericias3del5 adapterLista;


    public fragment_personagem_3del5_lista_pericias() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public fragment_personagem_3del5_lista_pericias(fragment_personagem_3del5_atributos viewAnterior) {
        this.viewAnterior=viewAnterior;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_personagem_3del5_lista_pericias, container, false);
        ListView listView = inflate.findViewById(R.id.fragment_personagem_3del5_lista_pericias_list);
        adapterLista=new AdapterListaPericias3del5(this,inflate.getContext());
        listView.setAdapter(adapterLista);



        return inflate;
    }


    public void atualizar(){
        adapterLista.notifyDataSetChanged();
    }

    public AdapterPersonagem3del5 getAdapter() {
        return adapter;
    }

    public void setAdapter(AdapterPersonagem3del5 adapter) {
        this.adapter = adapter;
    }
}
