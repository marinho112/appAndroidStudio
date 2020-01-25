package com.example.a3dl_unificados.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a3dl_unificados.R;
import com.example.a3dl_unificados.dao.bdInternoDAO;
import com.example.a3dl_unificados.dao.conexao_bd_interno;
import com.example.a3dl_unificados.ferramentas.CheckableConstraintLayout;
import com.example.a3dl_unificados.ferramentas.ferramentas3del5;
import com.example.a3dl_unificados.ferramentas.ferramentasListaItens;
import com.example.a3dl_unificados.ferramentas.ferramentasListaPericias;
import com.example.a3dl_unificados.objetos.objeto_3del5_pericia;
import com.example.a3dl_unificados.objetos.objeto_3del5_personagem;

import java.util.ArrayList;
import java.util.List;

public class AdapterListaPericias3del5TelaAdicionar extends BaseAdapter {

    private Context context;
    private List<objeto_3del5_pericia> listaPericia =new ArrayList<>();
    private int tipo,checado=0;

    public AdapterListaPericias3del5TelaAdicionar(int tipo, Context contextParametro) {

        context=contextParametro;
        this.tipo=tipo;
        this.listaPericia=bdInternoDAO.recuperaTodasAsPericiasTipo(tipo);
    }



    @Override
    public int getCount() {
        return (listaPericia.size());
    }

    @Override
    public objeto_3del5_pericia getItem(int position) {
        return listaPericia.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        //inflar view
        final CheckableConstraintLayout viewCriada;
        objeto_3del5_pericia item = getItem(position);
        viewCriada = (CheckableConstraintLayout) LayoutInflater.from(context).inflate(R.layout.activity_lista_cadastro_pericias_3del5,parent,false);
        defineCamposDoItem(viewCriada, item,position);
        definirClickItemLista(position, viewCriada);

        return viewCriada;

    }

    private void definirClickItemLista(final int position, final CheckableConstraintLayout viewCriada) {
        View.OnClickListener clickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!viewCriada.isChecked()) {
                    setChecado(position + 1);
                    notifyDataSetChanged();
                } else {
                    setChecado(0);
                    notifyDataSetChanged();
                }
            }
        };
        viewCriada.setOnClickListener(clickListener);
        viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtNome2).setOnClickListener(clickListener);
        viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtAtributoPrincipal2).setOnClickListener(clickListener);
    }

    public void notifyDataSetChanged() {
        listaPericia=bdInternoDAO.recuperaTodasAsPericiasTipo(tipo);

        super.notifyDataSetChanged();

    }



    private void defineCamposDoItem(CheckableConstraintLayout viewCriada, objeto_3del5_pericia pericia,int position) {

        int periciasPermanentes = conexao_bd_interno.getPericiasPermanentes();
        if(getChecado() -1==position){
            viewCriada.setChecked(true);
        }
        TextView text = viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtNome2);
        text.setText(pericia.getNome());
        TextView text2 = viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtAtributoPrincipal2);
        text2.setText(ferramentas3del5.converteNumeroParaAtributo3del5(pericia.getAtributoPrincipal()));

        if(pericia.getIdPericia()<=periciasPermanentes){
            text.setTextColor(Color.BLUE);
            text2.setTextColor(Color.BLUE);
        }
    }

    public int getChecado() {
        return checado;
    }

    public void setChecado(int checado) {
        this.checado = checado;
    }
}
