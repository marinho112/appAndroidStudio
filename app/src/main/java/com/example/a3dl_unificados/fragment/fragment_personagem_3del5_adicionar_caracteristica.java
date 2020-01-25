package com.example.a3dl_unificados.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.a3dl_unificados.R;
import com.example.a3dl_unificados.dao.bdInternoDAO;
import com.example.a3dl_unificados.objetos.objeto_3del5_caracteristica;
import com.example.a3dl_unificados.objetos.objeto_3del5_personagem;

import static com.example.a3dl_unificados.MainActivity.getPersonagem;
import static com.example.a3dl_unificados.ferramentas.ferramentas3del5.validaString;


public class fragment_personagem_3del5_adicionar_caracteristica extends DialogFragment {


    private fragment_personagem_3del5_lista_caracteristicas viewListaCaracteristicas;

    public fragment_personagem_3del5_adicionar_caracteristica() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public fragment_personagem_3del5_adicionar_caracteristica(fragment_personagem_3del5_lista_caracteristicas viewListaCaracteristicas) {

      this.viewListaCaracteristicas=viewListaCaracteristicas;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inflate = inflater.inflate(R.layout.fragment_personagem_3del5_adicionar_caracteristica, container, false);

        definirBtnSalvar(inflate);

        return inflate;
    }

    private void definirBtnSalvar(final View inflate) {
        Button btn = inflate.findViewById(R.id.personagem_3del5_adiionar_caracteristica_btnSalvar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                salvarNovaCaracteristica(inflate);
            }
        });
    }

    private void salvarNovaCaracteristica(View inflate) {
        objeto_3del5_caracteristica caracteristica=new objeto_3del5_caracteristica(getPersonagem());
        String str;
        boolean certo=true;

        EditText editNome = inflate.findViewById(R.id.personagem_3del5_adiionar_caracteristica_editNome);
        str=String.valueOf(editNome.getText());
        if(validaString(str,"nome",viewListaCaracteristicas.getContext())) {
            caracteristica.setNomeCaracteristica(str);
        }else {certo=false;}

        EditText editDescricao = inflate.findViewById(R.id.personagem_3del5_adiionar_caracteristica_editDescricao);
        str=String.valueOf(editDescricao.getText());
        if((validaString(str,"nome",viewListaCaracteristicas.getContext()))&&certo) {
            caracteristica.setDescricaoCaracteristica(str);
        }else {certo=false;}


        EditText editCusto = inflate.findViewById(R.id.personagem_3del5_adiionar_caracteristica_editCusto);
        str=String.valueOf(editCusto.getText());
        if((validaString(str,"nome",viewListaCaracteristicas.getContext()))&&certo) {
            caracteristica.setCustoCaracteristica(Integer.parseInt(str));
        }else {certo=false;}

        if(certo) {
            bdInternoDAO.inserirCaracteristica3del5(caracteristica);
            dismiss();
            viewListaCaracteristicas.getAdapterCaracteristicas().notifyDataSetChanged();
        }

    }


}
