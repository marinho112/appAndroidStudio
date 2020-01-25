package com.example.a3dl_unificados.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a3dl_unificados.R;
import com.example.a3dl_unificados.adapters.AdapterListaCaracteristicas3del5;
import com.example.a3dl_unificados.adapters.AdapterPersonagem3del5;
import com.example.a3dl_unificados.objetos.objeto_3del5_caracteristica;
import com.example.a3dl_unificados.objetos.objeto_3del5_personagem;

import java.util.List;

import static com.example.a3dl_unificados.MainActivity.getPersonagem;

public class fragment_personagem_3del5_lista_caracteristicas extends Fragment {

    private AdapterPersonagem3del5 adapter;
    private AdapterListaCaracteristicas3del5 adapterCaracteristicas;
    private TextView text;

    public fragment_personagem_3del5_lista_caracteristicas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inflate = inflater.inflate(R.layout.fragment_personagem_3del5_lista_caracteristicas, container, false);

        definirAdapterLista(inflate);
        definirTextoPontosGastos(inflate);
        definirFab(inflate);

        return inflate;
    }

    private void definirAdapterLista(View inflate) {
        adapterCaracteristicas =new AdapterListaCaracteristicas3del5(inflate.getContext(),this);
        ListView listView = inflate.findViewById(R.id.fragment_personagem_3del5_lista_caracteristicas_listView);
        listView.setAdapter(adapterCaracteristicas);
    }

    private void definirFab(final View inflate) {
        FloatingActionButton fab = inflate.findViewById(R.id.fragment_personagem_3del5_lista_caracteristicas_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criaDialog(inflate.getContext());
            }
        });
    }

    private void definirTextoPontosGastos(View inflate) {
        text = inflate.findViewById(R.id.fragment_personagem_3del5_lista_caracteristicas_textViewPontos);
        defineTextoPontosGastos();
    }

    public void defineTextoPontosGastos() {
        String str="1pt";
        int pts=0;
        List<objeto_3del5_caracteristica> lista = adapterCaracteristicas.getLista();
        for(int x=0;x<lista.size();x++){
            pts+=lista.get(x).getCustoCaracteristica();
        }
        if(pts!=1){
            str=pts+"pts";
        }
        text.setText(str);
    }

    private void criaDialog(Context context) {
        AppCompatActivity activity = (AppCompatActivity) context;
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        fragment_personagem_3del5_adicionar_caracteristica dialog= new fragment_personagem_3del5_adicionar_caracteristica(this);
        dialog.show(ft,"0");
    }



    public AdapterListaCaracteristicas3del5 getAdapterCaracteristicas() {
        return adapterCaracteristicas;
    }

    public void setAdapterCaracteristicas(AdapterListaCaracteristicas3del5 adapterCaracteristicas) {
        this.adapterCaracteristicas = adapterCaracteristicas;
    }

    public AdapterPersonagem3del5 getAdapter() {
        return adapter;
    }

    public void setAdapter(AdapterPersonagem3del5 adapter) {
        this.adapter = adapter;
    }
}
