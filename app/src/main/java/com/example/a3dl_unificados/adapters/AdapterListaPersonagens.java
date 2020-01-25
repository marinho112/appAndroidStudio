package com.example.a3dl_unificados.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.a3dl_unificados.MainActivity;
import com.example.a3dl_unificados.R;
import com.example.a3dl_unificados.dao.bdInternoDAO;
import com.example.a3dl_unificados.objetos.objeto_3del5_personagem;
import com.example.a3dl_unificados.personagem_3del5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

import static com.example.a3dl_unificados.MainActivity.adapterListaPersonagens;
import static com.example.a3dl_unificados.MainActivity.getListaPersonagens3del5;
import static com.example.a3dl_unificados.MainActivity.setPersonagem;
import static com.example.a3dl_unificados.dao.bdInternoDAO.excluirPersonagem3del5;
import static com.example.a3dl_unificados.ferramentas.ferramentas3del5.alerta;

public class AdapterListaPersonagens extends BaseAdapter {
    List<objeto_3del5_personagem> lista;
    Context context;
    public AdapterListaPersonagens(Context contextParametro) {
        lista=getListaPersonagens3del5();
        context=contextParametro;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public objeto_3del5_personagem getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        //inflar view
        final Intent intent=new Intent(context, personagem_3del5.class);


        final View viewCriada = LayoutInflater.from(context).inflate(R.layout.activity_objeto_personagens_item,parent,false);
        definirCampos(position, viewCriada);

        definirOnclickEvent(position, intent, viewCriada);
        definirEventoClickLongoItemLista(viewCriada,lista.get(position));
        return viewCriada;
    }

    private void definirOnclickEvent(final int position, final Intent intent, View viewCriada) {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                setPersonagem(lista.get(position));
                ((MainActivity) context).startActivityForResult(intent, 1);

            }
        };
        viewCriada.setOnClickListener(clickListener);
        viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtNome).setOnClickListener(clickListener);
        viewCriada.findViewById(R.id.textView_sistema_personagem_item).setOnClickListener(clickListener);
        viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtAtributoPrincipal).setOnClickListener(clickListener);;

    }

    private void definirCampos(int position, View viewCriada) {
        objeto_3del5_personagem personagem=lista.get(position);
        TextView textNome = viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtNome);
        textNome.setText(personagem.getNome());
        TextView textSitema = viewCriada.findViewById(R.id.textView_sistema_personagem_item);
        textSitema.setText("3D&L5.0");
        TextView textFuncao = viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtAtributoPrincipal);
        textFuncao.setText(personagem.getFuncao());
    }

    public void setLista(List<objeto_3del5_personagem> lista) {
        this.lista = lista;
    }

    private void definirEventoClickLongoItemLista(View viewCriada, final objeto_3del5_personagem personagem) {
        View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popup = new PopupMenu(context, v);
                definirEventoClickPopUpMenu(popup, personagem);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.apenas_excluir_menupopup, popup.getMenu());
                popup.show();

                return false;
            }
        };
        viewCriada.setOnLongClickListener(longClickListener);
        viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtNome).setOnLongClickListener(longClickListener);
        viewCriada.findViewById(R.id.textView_sistema_personagem_item).setOnLongClickListener(longClickListener);
        viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtAtributoPrincipal).setOnLongClickListener(longClickListener);
    }

    private void definirEventoClickPopUpMenu(PopupMenu popup, final objeto_3del5_personagem personagem) {
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                excluirItemLista(personagem);
                return false;
            }
        });
    }

    private void excluirItemLista(final objeto_3del5_personagem personagem){
        final AlertDialog.Builder alert=new AlertDialog.Builder(context)
                .setTitle("Deletar Personagem")
                .setMessage("Deseja Deletar o Personagem?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deletarImageFromStorage(personagem.getImagem());
                        bdInternoDAO.excluirPersonagem3del5(personagem);
                        lista=getListaPersonagens3del5();
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

    private void deletarImageFromStorage(String path)
    {
        if(path==null){return;}
        String[] t = path.split (Pattern.quote ("/imagemPersonagem"));
        File f=new File(t[0], "/imagemPersonagem"+t[1]);
        f.delete();

    }

}
