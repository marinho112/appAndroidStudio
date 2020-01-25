package com.example.a3dl_unificados.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.a3dl_unificados.R;
import com.example.a3dl_unificados.dao.bdInternoDAO;
import com.example.a3dl_unificados.ferramentas.ferramentasListaPericias;
import com.example.a3dl_unificados.fragment.fragment_personagem_3del5_lista_pericias;
import com.example.a3dl_unificados.fragment.fragment_personagem_3del5_lista_pericias_adicionar_pericia;
import com.example.a3dl_unificados.objetos.objeto_3del5_pericia;
import com.example.a3dl_unificados.objetos.objeto_3del5_periciaPersonagem;

import java.util.ArrayList;
import java.util.List;

import static com.example.a3dl_unificados.MainActivity.getPersonagem;
import static com.example.a3dl_unificados.ferramentas.ferramentas3del5.converteNumeroParaAtributo3del5;
import static com.example.a3dl_unificados.ferramentas.ferramentasComuns.editGetInteiroCorrigido;

public class AdapterListaPericias3del5 extends BaseAdapter {

    private List<objeto_3del5_pericia> listaPericiasFisicas=new ArrayList<>();
    private List<objeto_3del5_pericia> listaPericiasIntelectuais=new ArrayList<>();
    List<objeto_3del5_periciaPersonagem> listaPericiaPersonagems = new ArrayList<>();

    private Context context;
    private List<objeto_3del5_pericia> listaPersonagem=new ArrayList<>();
    private boolean ativado;
    private fragment_personagem_3del5_lista_pericias pai;
    private View separadorPericiaFisica,separadorPericiaIntelectual;


    public AdapterListaPericias3del5(fragment_personagem_3del5_lista_pericias pai, Context contextParametro) {
        this.pai=pai;
        definirListaPericiasPersonagem();
        dividirListasPorTipo(listaPersonagem,listaPericiasFisicas,listaPericiasIntelectuais);
        listaPericiaPersonagems=bdInternoDAO.recuperaPericiaPersonagemporPersonagem(getPersonagem());
        context=contextParametro;


    }



    private void definirListaPericiasPersonagem() {
        listaPericiaPersonagems = bdInternoDAO.recuperaPericiaPersonagemporPersonagem(getPersonagem());
        listaPersonagem.clear();
        for(int i=0;i<listaPericiaPersonagems.size();i++){
            listaPersonagem.add(listaPericiaPersonagems.get(i).getPericia());
        }
    }

    private void dividirListasPorTipo(List<objeto_3del5_pericia> listaParametro,List<objeto_3del5_pericia> listaParametroFisica,List<objeto_3del5_pericia> listaParametroIntelectual) {
        listaParametroFisica.clear();
        listaParametroIntelectual.clear();
        ferramentasListaPericias.ordenaListaPericiasNome(listaParametro);
        ferramentasListaPericias.ordenaListaPericiasAtributo(listaParametro);
        for(int x=0;x<listaParametro.size();x++){
           if((listaParametro.get(x).getTipo())==0){
               listaParametroFisica.add(listaParametro.get(x));
            }else{
               listaParametroIntelectual.add(listaParametro.get(x));
            }
        }
    }

    @Override
    public int getCount() {
        return (listaPericiasFisicas.size()+listaPericiasIntelectuais.size()+2);
    }

    @Override
    public objeto_3del5_pericia getItem(int position) {
        if(position==0){
            return null;
        }else if(position<=listaPericiasFisicas.size()) {
            return listaPericiasFisicas.get(position-1);
        }else if(position==listaPericiasFisicas.size()+1){
            return  null;
        }else{
           return listaPericiasIntelectuais.get(position-listaPericiasFisicas.size()-2);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        //inflar view
        View viewCriada;
        objeto_3del5_pericia item = getItem(position);
        if(position==0){
            separadorPericiaFisica=LayoutInflater.from(context).inflate(R.layout.activity_objeto_3del5_separador_lista__pericias,parent,false);
            separadorPericiaIntelectual=LayoutInflater.from(context).inflate(R.layout.activity_objeto_3del5_separador_lista__pericias,parent,false);
        }
        if(item == null){
           if(position<=listaPericiasFisicas.size()){
               viewCriada=separadorPericiaFisica;
               definirTextoSeparador(viewCriada,0);
               definirBtnAdicionarPericia(pai,viewCriada,0);
           }else{
               viewCriada=separadorPericiaIntelectual;
               definirTextoSeparador(viewCriada,1);
               definirBtnAdicionarPericia(pai,viewCriada,1);
           }
           return viewCriada;
        }
        viewCriada =LayoutInflater.from(context).inflate(R.layout.activity_objeto_3del5_item_lista_pericias,parent,false);
        defineCamposDoItem(viewCriada, item);
        definirEventoClickLongoItemLista(viewCriada,item);

        return viewCriada;

    }

    private void definirEventoClickLongoItemLista(View viewCriada, final objeto_3del5_pericia pericia) {
        View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popup = new PopupMenu(context, v);
                definirEventoClickPopUpMenu(popup, pericia);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.apenas_excluir_menupopup, popup.getMenu());
                popup.show();

                return false;
            }
        };
        viewCriada.setOnLongClickListener(longClickListener);
        viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtNome).setOnLongClickListener(longClickListener);
        viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtAtributoPrincipal).setOnLongClickListener(longClickListener);
        viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_edit_graduacao).setOnLongClickListener(longClickListener);

    }

    private void definirEventoClickPopUpMenu(PopupMenu popup, final objeto_3del5_pericia pericia) {
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                excluirItemLista(pericia);
                return false;
            }
        });
    }

    private void excluirItemLista(final objeto_3del5_pericia pericia) {

        final AlertDialog.Builder alert=new AlertDialog.Builder(context)
                .setTitle("Remover Perícia")
                .setMessage("Deseja Remover Perícia?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        List<objeto_3del5_periciaPersonagem> periciaPersonagems = bdInternoDAO.recuperaPericiaPersonagem(getPersonagem(),pericia);
                        if(periciaPersonagems.size()>0){
                            bdInternoDAO.excluirPericiaPersonagem3del5(periciaPersonagems.get(0));
                            notifyDataSetChanged();
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

    public int listaPericiasValorTotalGraduacoes(int tipo){
        int retorno=0;
        for(int i=0;i<listaPericiaPersonagems.size();i++){
            if(listaPericiaPersonagems.get(i).getPericia().getTipo()==tipo){
                retorno+=listaPericiaPersonagems.get(i).getGraduacao();
            }
        }
        return retorno;
    }

    private void definirBtnAdicionarPericia(final fragment_personagem_3del5_lista_pericias pai, final View viewCriada, final int tipo) {
        View btn = viewCriada.findViewById(R.id.objeto_3del5_separador_lista_pericias_btnAdicao);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criaDialog(pai,viewCriada.getContext(),tipo);
            }
        });
    }

    private void criaDialog(fragment_personagem_3del5_lista_pericias pai, Context context, int tipo){
        setAtivado(false);
        AppCompatActivity activity = (AppCompatActivity) context;
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        fragment_personagem_3del5_lista_pericias_adicionar_pericia dialog;
        List<objeto_3del5_pericia> listaPericiasInterna= bdInternoDAO.recuperaTodasAsPericias();
        List<objeto_3del5_pericia> fisica=new ArrayList<objeto_3del5_pericia>();
        List<objeto_3del5_pericia> intelectual=new ArrayList<objeto_3del5_pericia>();
        dividirListasPorTipo(listaPericiasInterna,fisica,intelectual);
        if (tipo == 0) {
            dialog = new fragment_personagem_3del5_lista_pericias_adicionar_pericia(pai,0);
        }else{
            dialog= new fragment_personagem_3del5_lista_pericias_adicionar_pericia(pai,1);
        }
        dialog.show(ft,"0");
    }

    private void defineCamposDoItem(final View viewCriada, objeto_3del5_pericia item) {


        final EditText edit = viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_edit_graduacao);
        //final List<objeto_3del5_periciaPersonagem> pp = bdInternoDAO.recuperaPericiaPersonagem(getPersonagem(), item);

        objeto_3del5_periciaPersonagem pp = obterPericiaPersonagemPorItem(item);
        edit.setText(String.valueOf(pp.getGraduacao()));
        definirEventoFocoItemLista(edit, pp);
        definirEventoMudaTextoItemLista(edit, pp);
/*
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               viewCriada.requestFocus();
               edit.clearFocus();
            }
        };*/

        TextView text = viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtNome);
        text.setText(item.getNome());
       // text.setOnClickListener(clickListener);

        text = viewCriada.findViewById(R.id.objeto_3del5_item_lista_pericias_txtAtributoPrincipal);
        text.setText(converteNumeroParaAtributo3del5(item.getAtributoPrincipal()));
       // text.setOnClickListener(clickListener);


    }

    private void definirEventoMudaTextoItemLista(final EditText edit, final objeto_3del5_periciaPersonagem pp) {
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    pp.setGraduacao(editGetInteiroCorrigido(edit));
                    if (pp.getGraduacao() > 10) {
                        pp.setGraduacao(10);
                        edit.setText("10");
                    }

                    bdInternoDAO.inserirPericiaPersonagem3del5(pp);
                    definirTextoSeparador(separadorPericiaFisica, 0);
                    definirTextoSeparador(separadorPericiaIntelectual, 1);
                } catch (Exception ex){}
            }
        });
    }

    private objeto_3del5_periciaPersonagem obterPericiaPersonagemPorItem(objeto_3del5_pericia item) {
        for(int i=0;i<listaPericiaPersonagems.size();i++){

            if(listaPericiaPersonagems.get(i).getPericia().getIdPericia()==item.getIdPericia()){
                return listaPericiaPersonagems.get(i);
            }
        }
        return null;
    }

    private void definirEventoFocoItemLista(EditText edit, final objeto_3del5_periciaPersonagem pp) {
        edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    EditText edit = (EditText) v;
                    String text = String.valueOf(edit.getText());
                    if (hasFocus) {
                        if (text.equals("0") || text.equals(" ")) {
                            edit.setText("");
                        }
                    } else {
                        if ((edit.getText().length() == 0) || text.equals(" ")) {
                            edit.setText("0");
                        }
                        if ((Integer.parseInt(String.valueOf(edit.getText()))) > 10) {
                            edit.setText("10");
                        }
                        pp.setGraduacao(Integer.parseInt(String.valueOf(edit.getText())));
                        bdInternoDAO.inserirPericiaPersonagem3del5(pp);
                        definirTextoSeparador(separadorPericiaFisica, 0);
                        definirTextoSeparador(separadorPericiaIntelectual, 1);

                    }
                } catch (Exception ex){}
            }
        });
    }

    private void definirTextoSeparador(View viewCriada,int tipo) {

        String textoPericia,textoPontos;
        int pontosTotais;
        int pontosGastos=listaPericiasValorTotalGraduacoes(tipo);
        if(tipo==0){
            textoPericia="Perícias Físicas";
            pontosTotais=getPersonagem().getPericiasFisicas();

        }else{
            textoPericia="Perícias Intelectuais";
            pontosTotais=getPersonagem().getPericiasIntelectuais();
        }

        textoPontos=pontosGastos+"/"+pontosTotais;

        TextView text = viewCriada.findViewById(R.id.objeto_3del5_separador_lista_pericias_txtPericia);
        text.setText(textoPericia);

        text = viewCriada.findViewById(R.id.objeto_3del5_separador_lista_pericias_txtPontos);
        if(pontosGastos<=pontosTotais){
            text.setText(textoPontos);
        }else {
            SpannableString ss=new SpannableString(textoPontos);
            ForegroundColorSpan fcs=new ForegroundColorSpan(Color.rgb(195,14,21));
            ss.setSpan(fcs,0,textoPontos.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setText(ss);
        }

    }


    @Override
    public void notifyDataSetChanged(){
        definirListaPericiasPersonagem();
        dividirListasPorTipo(listaPersonagem,listaPericiasFisicas,listaPericiasIntelectuais);
        //listaPericiaPersonagems=bdInternoDAO.recuperaPericiaPersonagemporPersonagem(getPersonagem());
        super.notifyDataSetChanged();


    }

    public boolean isAtivado() {
        return ativado;
    }

    public void setAtivado(boolean ativado) {
        this.ativado = ativado;
    }




}
