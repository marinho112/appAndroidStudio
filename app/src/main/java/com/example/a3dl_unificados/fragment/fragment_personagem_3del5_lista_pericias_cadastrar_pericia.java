package com.example.a3dl_unificados.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.a3dl_unificados.R;
import com.example.a3dl_unificados.adapters.AdapterListaPericias3del5TelaAdicionar;
import com.example.a3dl_unificados.dao.bdInternoDAO;
import com.example.a3dl_unificados.objetos.objeto_3del5_pericia;

import static com.example.a3dl_unificados.ferramentas.ferramentas3del5.obterAdapterSpinnerAtributo;
import static com.example.a3dl_unificados.ferramentas.ferramentas3del5.obterAdapterSpinnerTipo;


public class fragment_personagem_3del5_lista_pericias_cadastrar_pericia extends DialogFragment {

    private ListView list;
    private fragment_personagem_3del5_lista_pericias_adicionar_pericia viewAnterior;
    private int tipo;

    public fragment_personagem_3del5_lista_pericias_cadastrar_pericia() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public fragment_personagem_3del5_lista_pericias_cadastrar_pericia(ListView list,fragment_personagem_3del5_lista_pericias_adicionar_pericia viewAnterior,int tipo) {
        this.list=list;
        this.tipo=tipo;
        this.viewAnterior=viewAnterior;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inflate = inflater.inflate(R.layout.fragment_personagem_3del5_lista_pericias_cadastrar_pericia, container, false);

        final Spinner spinnerAtributo = inflate.findViewById(R.id.personagem_3del5_lista_pericias_cadastrar_pericia_spinnerAtributo);
        spinnerAtributo.setAdapter(obterAdapterSpinnerAtributo(inflate.getContext()));


        definirClickBtn(inflate);

        return inflate;
    }

    private void definirClickBtn(final View inflate) {
        Button btnSalvar=inflate.findViewById(R.id.personagem_3del5_lista_pericias_cadastrar_pericia_btn);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinnerAtributo = inflate.findViewById(R.id.personagem_3del5_lista_pericias_cadastrar_pericia_spinnerAtributo);
                EditText edit=inflate.findViewById(R.id.personagem_3del5_lista_pericias_cadastrar_pericia_editText);
                String text=String.valueOf(edit.getText());

                if(text.isEmpty()){
                    alerta("De um nome a sua Perícia",inflate.getContext());
                }else{
                    objeto_3del5_pericia pericia=new objeto_3del5_pericia();
                    pericia.setNome(text);
                    pericia.setAtributoPrincipal(spinnerAtributo.getSelectedItemPosition());
                    pericia.setTipo(tipo);

                    bdInternoDAO.inserirPericia3del5(pericia);
                    AdapterListaPericias3del5TelaAdicionar adapter = (AdapterListaPericias3del5TelaAdicionar) list.getAdapter();
                    adapter.notifyDataSetChanged();
                    viewAnterior.atualizarListaPericiasUsadas();
                    dismiss();
                }
            }
        });
    }

    private void alerta(String texto, Context context){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage(texto);
        alertDialog.show();
    }


}
