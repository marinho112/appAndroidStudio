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
import com.example.a3dl_unificados.adapters.AdapterListaItens3del5;
import com.example.a3dl_unificados.adapters.AdapterPersonagem3del5;
import com.example.a3dl_unificados.objetos.objeto_3del5_item;
import com.example.a3dl_unificados.objetos.objeto_3del5_personagem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.a3dl_unificados.MainActivity.getPersonagem;
import static com.example.a3dl_unificados.dao.bdInternoDAO.recuperaTodosOsItens;
import static com.example.a3dl_unificados.ferramentas.ferramentasListaItens.atualizaPesoTotal;
import static com.example.a3dl_unificados.ferramentas.ferramentasListaItens.ordenaListaItensNome;
import static com.example.a3dl_unificados.ferramentas.ferramentasListaItens.ordenaListaItensTipo;


public class fragment_personagem_3del5_lista_itens extends Fragment {


    private AdapterPersonagem3del5 adapter;
    private List<objeto_3del5_item> listaItens=new ArrayList<>();
    private ListView viewLista;
    private View inflate;
    private  AdapterListaItens3del5 adapterListaItens;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        listaItens=recuperaTodosOsItens(getPersonagem());
        ordenaListaItensNome(listaItens);
        ordenaListaItensTipo(listaItens);
        inflate = inflater.inflate(R.layout.fragment_personagem_3del5_lista_itens, container, false);
        viewLista=inflate.findViewById(R.id.fragment_personagem_3del5_lista_pericias_list);
        definirOnclickFab(inflate.getContext());
        adapterListaItens = new AdapterListaItens3del5(listaItens, inflate.getContext());
        viewLista.setAdapter(adapterListaItens);
        TextView pesoTotalView = inflate.findViewById(R.id.textView_3del5_lista_itens_pesoTotal);
        atualizaPesoTotal(pesoTotalView,listaItens);
        return inflate;
    }



    private void definirOnclickFab(final Context context) {
        FloatingActionButton fab=inflate.findViewById(R.id.fab_lista_pericias_3del5);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objeto_3del5_item novoItem = new objeto_3del5_item(getPersonagem());
                listaItens.add(novoItem);
             criaDialog(context,novoItem);
            }
        });
    }

    private void criaDialog(Context context,objeto_3del5_item novoItem) {
        AppCompatActivity activity = (AppCompatActivity) context;
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        fragment_item_3del5_editor_informacoes dialog = new fragment_item_3del5_editor_informacoes(listaItens,novoItem, (AdapterListaItens3del5) viewLista.getAdapter());
        dialog.show(ft,String.valueOf(0));
    }

    public fragment_personagem_3del5_lista_itens() {
        // Required empty public constructor
    }



    public void setListaItens(List<objeto_3del5_item> listaItens) {
        this.listaItens = listaItens;
    }

    public AdapterListaItens3del5 getAdapterListaItens() {
        return adapterListaItens;
    }

    public void setAdapterListaItens(AdapterListaItens3del5 adapter) {
        this.adapterListaItens = adapter;
    }

    public List<objeto_3del5_item> getListaItens() {
        return listaItens;
    }

    public AdapterPersonagem3del5 getAdapter() {
        return adapter;
    }

    public void setAdapter(AdapterPersonagem3del5 adapter) {
        this.adapter = adapter;
    }
}
