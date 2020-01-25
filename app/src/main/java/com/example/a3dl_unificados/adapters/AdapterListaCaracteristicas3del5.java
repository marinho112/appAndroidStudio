package com.example.a3dl_unificados.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.a3dl_unificados.R;
import com.example.a3dl_unificados.dao.bdInternoDAO;
import com.example.a3dl_unificados.fragment.fragment_personagem_3del5_lista_caracteristicas;
import com.example.a3dl_unificados.objetos.objeto_3del5_caracteristica;
import com.example.a3dl_unificados.objetos.objeto_3del5_personagem;

import java.util.List;

import static com.example.a3dl_unificados.MainActivity.getPersonagem;
import static com.example.a3dl_unificados.ferramentas.ferramentas3del5.alerta;

public class AdapterListaCaracteristicas3del5 extends BaseAdapter {
    private List<objeto_3del5_caracteristica> lista;
    private Context context;
    private fragment_personagem_3del5_lista_caracteristicas caracteristica;

    public AdapterListaCaracteristicas3del5(Context contextParametro,fragment_personagem_3del5_lista_caracteristicas caracteristica) {
        setLista(bdInternoDAO.recuperaTodasAsCaracteristicas(getPersonagem()));
        setContext(contextParametro);
        this.caracteristica=caracteristica;
    }

    @Override
    public int getCount() {
        return getLista().size();
    }

    @Override
    public objeto_3del5_caracteristica getItem(int position) {
        return getLista().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        //inflar view


        View viewCriada = LayoutInflater.from(getContext()).inflate(R.layout.activity_objeto_3del5_item_lista_caracteristicas,parent,false);

        definirCampos(position, viewCriada);
        definirOnclickEvent(position, viewCriada);
        definirEventoClickLongoItemLista(viewCriada, getLista().get(position));
        return viewCriada;
    }

    private void definirOnclickEvent(final int position, View viewCriada) { ;
        viewCriada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerta(getLista().get(position).getNomeCaracteristica()+"\n\n"+ getLista().get(position).getDescricaoCaracteristica(), getContext());
            }
        });
    }

    private void definirCampos(int position, View viewCriada) {

        TextView text = viewCriada.findViewById(R.id.objeto_3del5_item_lista_caracteristicas_textNome);
        text.setText(getLista().get(position).getNomeCaracteristica());

        text= viewCriada.findViewById(R.id.objeto_3del5_item_lista_caracteristicas_textCusto);
        int custo= getLista().get(position).getCustoCaracteristica();
        if(custo==1){
            text.setText("1pt");
        }else {
            text.setText(custo+"pts");
        }
    }

    public void setLista(List<objeto_3del5_caracteristica> lista) {
        this.lista = lista;
    }

    private void definirEventoClickLongoItemLista(View viewCriada, final objeto_3del5_caracteristica caracteristica) {
        View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popup = new PopupMenu(getContext(), v);
                definirEventoClickPopUpMenu(popup, caracteristica);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.apenas_excluir_menupopup, popup.getMenu());
                popup.show();

                return false;
            }
        };
        viewCriada.setOnLongClickListener(onLongClickListener);
        viewCriada.findViewById(R.id.objeto_3del5_item_lista_caracteristicas_textNome).setOnLongClickListener(onLongClickListener);
        viewCriada.findViewById(R.id.objeto_3del5_item_lista_caracteristicas_textCusto).setOnLongClickListener(onLongClickListener);
    }

    private void definirEventoClickPopUpMenu(PopupMenu popup, final objeto_3del5_caracteristica caracteristica) {
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                excluirItemLista(caracteristica);
                return false;
            }
        });
    }

    private void excluirItemLista(objeto_3del5_caracteristica caracteristica){
        bdInternoDAO.excluirCaracteristica3del5(caracteristica);
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        setLista(bdInternoDAO.recuperaTodasAsCaracteristicas(getPersonagem()));
        caracteristica.defineTextoPontosGastos();
        super.notifyDataSetChanged();

    }

    public List<objeto_3del5_caracteristica> getLista() {
        return lista;
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
