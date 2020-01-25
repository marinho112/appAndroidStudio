package com.example.a3dl_unificados.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.a3dl_unificados.R;
import com.example.a3dl_unificados.adapters.AdapterListaPericias3del5TelaAdicionar;
import com.example.a3dl_unificados.dao.bdInternoDAO;
import com.example.a3dl_unificados.objetos.objeto_3del5_pericia;
import com.example.a3dl_unificados.objetos.objeto_3del5_periciaPersonagem;
import com.example.a3dl_unificados.objetos.objeto_3del5_personagem;

import java.util.ArrayList;
import java.util.List;

import static com.example.a3dl_unificados.MainActivity.getPersonagem;
import static com.example.a3dl_unificados.ferramentas.ferramentas3del5.alerta;


public class fragment_personagem_3del5_lista_pericias_adicionar_pericia extends DialogFragment {

    private List<objeto_3del5_pericia> listaPericiasUsadas;
    private int tipo;
    private fragment_personagem_3del5_lista_pericias pai;


    public fragment_personagem_3del5_lista_pericias_adicionar_pericia() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        return dialog;
    }

    @SuppressLint("ValidFragment")
    public fragment_personagem_3del5_lista_pericias_adicionar_pericia(fragment_personagem_3del5_lista_pericias pai,int tipo) {
        this.setPai(pai);
        this.tipo=tipo;
        atualizarListaPericiasUsadas();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inflate = inflater.inflate(R.layout.fragment_personagem_3del5_lista_pericias_adicionar_pericia, container, false);

        final ListView list = inflate.findViewById(R.id.personagem_3del5_lista_pericias_adicionar_pericia_listView);
        list.setAdapter(new AdapterListaPericias3del5TelaAdicionar(tipo, getPai().getContext()));

        definirBtnExcluir(inflate, list);
        definirBtnAdicionar(inflate, list);
        definirBtnNovaPericia(inflate, list);

        return inflate;
    }

    private void definirBtnNovaPericia(View inflate, final ListView list) {
        Button btn=inflate.findViewById(R.id.personagem_3del5_lista_pericias_adicionar_pericia_btnNovaPericia);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criaDialog(pai.getContext(),list);
            }
        });
    }

    private void definirBtnAdicionar(View inflate, final ListView list) {
        Button btn = inflate.findViewById(R.id.personagem_3del5_lista_pericias_adicionar_pericia_btnAdicionar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdapterListaPericias3del5TelaAdicionar adapter=((AdapterListaPericias3del5TelaAdicionar)(list.getAdapter()));
                int itemMarcado=adapter.getChecado()-1;
                if(itemMarcado>=0) {
                    List<objeto_3del5_periciaPersonagem> listaPericiaPersonagems = bdInternoDAO.recuperaPericiaPersonagem(getPersonagem(), getListaPericiasUsadas().get(itemMarcado));
                    if(listaPericiaPersonagems.size()==0){
                        bdInternoDAO.inserirPericiaPersonagem3del5(getListaPericiasUsadas().get(itemMarcado), getPersonagem(),0);
                    }
                    dismiss();

                }else{
                alerta("Selecione alguma perícia para adicionar.", getPai().getContext());
            }

            }
        });
    }


    @Override
    public void onDestroyView(){
        getPai().atualizar();
        super.onDestroyView();
    }

    private void definirBtnExcluir(View inflate, final ListView list) {


        Button btn = inflate.findViewById(R.id.personagem_3del5_lista_pericias_adicionar_pericia_btnExcluir);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirPeríciaSelecionada(list);
            }
        });
    }

    private void excluirPeríciaSelecionada(final ListView list) {

        final AlertDialog.Builder alert=new AlertDialog.Builder(getPai().getContext())
                .setTitle("Excluir Perícia")
                .setMessage("Deseja Excluir Perícia?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterListaPericias3del5TelaAdicionar adapter=((AdapterListaPericias3del5TelaAdicionar)(list.getAdapter()));
                        int itemMarcado=adapter.getChecado()-1;
                        if(itemMarcado>=0) {
                            boolean resultado = bdInternoDAO.excluirPericia3del5(getListaPericiasUsadas().get(itemMarcado));

                            if(resultado){
                                atualizarListaPericiasUsadas();
                                adapter.setChecado(0);
                                adapter.notifyDataSetChanged();
                            }else{
                                alerta("Não é possivel excluir Pericias Permanentes", getPai().getContext());
                            }

                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alert.create().show();

    }

    public void atualizarListaPericiasUsadas() {
        listaPericiasUsadas= bdInternoDAO.recuperaTodasAsPericiasTipo(tipo);
    }

    private void criaDialog(Context context,ListView list) {
        AppCompatActivity activity = (AppCompatActivity) context;
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        fragment_personagem_3del5_lista_pericias_cadastrar_pericia dialog = new fragment_personagem_3del5_lista_pericias_cadastrar_pericia(list,this,tipo);
        dialog.show(ft,"0");
    }




    public List<objeto_3del5_pericia> getListaPericiasUsadas() {
        return listaPericiasUsadas;
    }

    public void setListaPericiasUsadas(List<objeto_3del5_pericia> listaPericiasUsadas) {
        this.listaPericiasUsadas = listaPericiasUsadas;
    }

    public fragment_personagem_3del5_lista_pericias getPai() {
        return pai;
    }

    public void setPai(fragment_personagem_3del5_lista_pericias pai) {
        this.pai = pai;
    }
}
