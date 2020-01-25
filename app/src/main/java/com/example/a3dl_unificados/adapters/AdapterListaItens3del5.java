package com.example.a3dl_unificados.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.a3dl_unificados.R;
import com.example.a3dl_unificados.dao.bdInternoDAO;
import com.example.a3dl_unificados.fragment.fragment_item_3del5_editor_informacoes;
import com.example.a3dl_unificados.objetos.objeto_3del5_item;
import com.example.a3dl_unificados.objetos.objeto_3del5_personagem;

import java.text.NumberFormat;
import java.util.List;

import static com.example.a3dl_unificados.MainActivity.getListaPersonagens3del5;
import static com.example.a3dl_unificados.ferramentas.ferramentasListaItens.atualizaPesoTotal;
import static com.example.a3dl_unificados.ferramentas.ferramentasListaItens.getPesoTotal;

public class AdapterListaItens3del5 extends BaseAdapter {

    List<objeto_3del5_item> lista;
    Context context;

    public AdapterListaItens3del5(List<objeto_3del5_item>listaParametro,Context contextParametro) {
        lista=listaParametro;
        context=contextParametro;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public objeto_3del5_item getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        //inflar view
        final View viewCriada =LayoutInflater.from(context).inflate(R.layout.activity_objeto_3del5_item_lista_item,parent,false);

        definirValoresDoCampo(position, viewCriada);
        //criar evento de click
        criarEventoDeClick(position, viewCriada);
        definirEventoClickLongoItemLista(viewCriada,lista.get(position));
        return viewCriada;
    }

    private void criarEventoDeClick(final int position, View viewCriada) {
        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                criaDialog(position);
            }
        };
        viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtNome).setOnClickListener(click);
        viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtAtributoPrincipal).setOnClickListener(click);
    }


    private void definirValoresDoCampo(int position, final View viewCriada) {

        final objeto_3del5_item objeto3del5Item = lista.get(position);
        final CheckBox cb;
        float pesoTotal;
        //setar Texto principal do item
        ((TextView) viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtNome)).setText(
                objeto3del5Item.getNome()+" x"+ objeto3del5Item.getQuantidade());

        //definir tipo do item
        String tipo = defineTipo(position);
        ((TextView) viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtAtributoPrincipal)).setText(tipo);

        //desativar checkBox para não equipamentos &
        cb=viewCriada.findViewById(R.id.checkBox_item_3del5);
        if((objeto3del5Item.getTipo()!=1)&&(objeto3del5Item.getTipo()!=4)){
            cb.setEnabled(false);
        }else{
            cb.setChecked(objeto3del5Item.isEquipado());
            onClickCheckBox(objeto3del5Item, cb);
        }


        //setar Texto do Peso do item
        pesoTotal = getPesoTotal(objeto3del5Item);
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(1);
        format.setMinimumFractionDigits(1);
        ((TextView) viewCriada.findViewById(R.id.textView_sistema_personagem_item)).setText(
                format.format(objeto3del5Item.getPeso())+"Kg / "+
                        format.format(pesoTotal)+"Kg"
        );

    }

    private void onClickCheckBox(final objeto_3del5_item objeto3del5Item, final CheckBox cb) {
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                objeto3del5Item.setEquipado(cb.isChecked());
            }
        });
    }

    @Override
    public void notifyDataSetChanged(){
        super.notifyDataSetChanged();
        Activity ac= (Activity) context;
        TextView pesoTotalView = ac.findViewById(R.id.textView_3del5_lista_itens_pesoTotal);
        atualizaPesoTotal(pesoTotalView,lista);

    }

    private void criaDialog(int position) {
        AppCompatActivity activity = (AppCompatActivity) context;
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        fragment_item_3del5_editor_informacoes dialog = new fragment_item_3del5_editor_informacoes(lista,lista.get(position),this);
        dialog.show(ft,String.valueOf(position));
    }

    private String defineTipo(int position) {
        switch (lista.get(position).getTipo()){
            case 0: return "Utilizável";
            case 1: return "Equipamento";
            case 2: return "Não Utilizavel";
            case 3: return "Carga";
            case 4: return "Equipamento Carga";
            default: return "Personalizado";
        }
    }


    private void definirEventoClickLongoItemLista(View viewCriada, final objeto_3del5_item item) {
        View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popup = new PopupMenu(context, v);
                definirEventoClickPopUpMenu(popup, item);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.apenas_excluir_menupopup, popup.getMenu());
                popup.show();

                return false;
            }
        };
        viewCriada.setOnLongClickListener(onLongClickListener);
        viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtNome).setOnLongClickListener(onLongClickListener);
        viewCriada.findViewById(R.id.textView_sistema_personagem_item).setOnLongClickListener(onLongClickListener);
        viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtAtributoPrincipal).setOnLongClickListener(onLongClickListener);
        viewCriada.findViewById(R.id.checkBox_item_3del5).setOnLongClickListener(onLongClickListener);

    }

    private void definirEventoClickPopUpMenu(PopupMenu popup, final objeto_3del5_item itemSelecionado) {
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                excluirItemLista(itemSelecionado);
                return false;
            }
        });
    }

    private void excluirItemLista(final objeto_3del5_item item){
        final AlertDialog.Builder alert=new AlertDialog.Builder(context)
                .setTitle("Deletar Item")
                .setMessage("Deseja Deletar o Item?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bdInternoDAO.excluirItem3del5(item);
                        lista.remove(item);
                        notifyDataSetChanged();
                        dialog.dismiss();
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

}


