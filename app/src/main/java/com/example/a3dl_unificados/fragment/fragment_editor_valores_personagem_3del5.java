package com.example.a3dl_unificados.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.a3dl_unificados.R;
import com.example.a3dl_unificados.objetos.objeto_3del5_personagem;

import static com.example.a3dl_unificados.MainActivity.getPersonagem;
import static com.example.a3dl_unificados.dao.bdInternoDAO.inserirPersonagem3del5;
import static com.example.a3dl_unificados.ferramentas.ferramentasComuns.editGetInteiroCorrigido;


public class fragment_editor_valores_personagem_3del5 extends DialogFragment {

    private String tipoObjeto;
    private fragment_personagem_3del5_atributos viewAnterior;
    private View inflateAnterior;
    private int valorBase;
    private int[] valores={0,0,0,0};

    public fragment_editor_valores_personagem_3del5() {

    }

    @SuppressLint("ValidFragment")
    public fragment_editor_valores_personagem_3del5(View inflaterInterno,fragment_personagem_3del5_atributos viewAnteriorInterno,String tipoObjetoInterno) {
        viewAnterior=viewAnteriorInterno;
        inflateAnterior=inflaterInterno;
        tipoObjeto=tipoObjetoInterno;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.fragment_editor_valores_personagem_3del5, container, false);
        EditText editValor = inflate.findViewById(R.id.editText_valor_editor_valores_3del5);
        final int[] valoresBonus=seletorDeTipoItem(tipoObjeto,inflate);
        definirEventosDeFoco(inflate);
        definirEventosClick(inflate);
        defineBtnSalvar(inflate, valoresBonus);
        return inflate;
    }

    private void definirEventosClick(final View inflate) {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarValores(inflate);
            }
        };

        inflate.findViewById(R.id.textFild_total_editor_valores_3del5).setOnClickListener(clickListener);
        inflate.findViewById(R.id.textView_texto_editor_valores_3del5).setOnClickListener(clickListener);
        inflate.findViewById(R.id.editText_valor_editor_valores_3del5).setOnClickListener(clickListener);
        inflate.findViewById(R.id.editText_soma_editor_valores_3del5).setOnClickListener(clickListener);

    }


    private void definirEventosDeFoco(final View inflate) {

        EditText editBonus1 = inflate.findViewById(R.id.editText_bonus1_editor_valores_3del5);
        definirEventoDeFoco(inflate, editBonus1);
        EditText editBonus2 = inflate.findViewById(R.id.editText_bonus2_editor_valores_3del5);
        definirEventoDeFoco(inflate, editBonus2);
        EditText editBonus3 = inflate.findViewById(R.id.editText_bonus3_editor_valores_3del5);
        definirEventoDeFoco(inflate, editBonus3);
        EditText editBonus4 = inflate.findViewById(R.id.editText_bonus4_editor_valores_3del5);
        definirEventoDeFoco(inflate, editBonus4);

    }

    private void definirEventoDeFoco(final View inflate, final EditText editBonus) {


        editBonus.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                String condicao=String.valueOf(((EditText)v).getText());
                if(hasFocus){
                    if(condicao.equals("0")||condicao.equals("0.0")){
                        ((EditText)v).setText("");
                    }
                }else{

                    atualizarValores(inflate);
                }
            }
        });
    }

    private void atualizarValores(View inflate) {
        EditText editBonus1 = inflate.findViewById(R.id.editText_bonus1_editor_valores_3del5);
        verificaNumeroEdit(editBonus1);
        valores[0]=Integer.parseInt(String.valueOf(editBonus1.getText()));
        EditText editBonus2 = inflate.findViewById(R.id.editText_bonus2_editor_valores_3del5);
        verificaNumeroEdit(editBonus2);
        valores[1]=Integer.parseInt(String.valueOf(editBonus2.getText()));
        EditText editBonus3 = inflate.findViewById(R.id.editText_bonus3_editor_valores_3del5);
        verificaNumeroEdit(editBonus3);
        valores[2]=Integer.parseInt(String.valueOf(editBonus3.getText()));
        EditText editBonus4 = inflate.findViewById(R.id.editText_bonus4_editor_valores_3del5);
        verificaNumeroEdit(editBonus4);
        valores[3]=Integer.parseInt(String.valueOf(editBonus4.getText()));
        EditText editTotal = inflate.findViewById(R.id.editText_soma_editor_valores_3del5);
        editTotal.setText(String.valueOf(valorBase+valores[0]+valores[1]+valores[2]+valores[3]));
    }

    private void verificaNumeroEdit(EditText atual) {
        if((atual.getText().length()==0)||(String.valueOf(atual.getText()).equals("-"))){
            atual.setText("0");
        }
    }

    private void defineBtnSalvar(final View inflate, final int[] valoresBonus) {
        Button btnSalvar = inflate.findViewById(R.id.btn_salvar_editor_valores_3del5);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarPersonagem(valoresBonus, inflate);
                dismiss();
            }
        });
    }

    private void salvarPersonagem(int[] valoresBonus, View inflate) {

        valoresBonus[0]=editGetInteiroCorrigido((EditText)inflate.findViewById(R.id.editText_bonus1_editor_valores_3del5));
        valoresBonus[1]=editGetInteiroCorrigido((EditText)inflate.findViewById(R.id.editText_bonus2_editor_valores_3del5));
        valoresBonus[2]=editGetInteiroCorrigido((EditText)inflate.findViewById(R.id.editText_bonus3_editor_valores_3del5));
        valoresBonus[3]=editGetInteiroCorrigido((EditText)inflate.findViewById(R.id.editText_bonus4_editor_valores_3del5));

        if(tipoObjeto.equals("pv")){getPersonagem().setPvBonus(valoresBonus);}
        if(tipoObjeto.equals("acoes")){getPersonagem().setAcoesBonus(valoresBonus);}
        if(tipoObjeto.equals("dano")){getPersonagem().setDanoBonus(valoresBonus);}
        if(tipoObjeto.equals("reducaoDano")){getPersonagem().setReducaoDanoBonus(valoresBonus);}
        if(tipoObjeto.equals("acerto")){getPersonagem().setAcertoBonus(valoresBonus);}
        if(tipoObjeto.equals("reflexo")){getPersonagem().setReflexoBonus(valoresBonus);}
        if(tipoObjeto.equals("resistencia")){getPersonagem().setResistenciaBonus(valoresBonus);}
        if(tipoObjeto.equals("percepcao")){getPersonagem().setPercepcaoBonus(valoresBonus);}
        if(tipoObjeto.equals("vontade")){getPersonagem().setVontadeBonus(valoresBonus);}
        if(tipoObjeto.equals("multiplicador")){getPersonagem().setMultiplicadorBonus(valoresBonus);}

        inserirPersonagem3del5(getPersonagem());
        viewAnterior.atualizaPersonagem();
    }

    private int[] seletorDeTipoItem(String tipoObjeto,View inflate) {
        int valor=0;
        int valorTotal=0;
        int[] var={0,0,0,0};
        EditText editSoma = inflate.findViewById(R.id.editText_valor_editor_valores_3del5);
        EditText editTotal = inflate.findViewById(R.id.editText_soma_editor_valores_3del5);
        EditText editBonus1 = inflate.findViewById(R.id.editText_bonus1_editor_valores_3del5);
        EditText editBonus2 = inflate.findViewById(R.id.editText_bonus2_editor_valores_3del5);
        EditText editBonus3 = inflate.findViewById(R.id.editText_bonus3_editor_valores_3del5);
        EditText editBonus4 = inflate.findViewById(R.id.editText_bonus4_editor_valores_3del5);
        if(tipoObjeto.equals("pv")){valorTotal=getPersonagem().getPv();valor=getPersonagem().getPvBase();var=getPersonagem().getPvBonus();}
        if(tipoObjeto.equals("acoes")){valorTotal=getPersonagem().getAcoes();valor=getPersonagem().getAcoesBase();var=getPersonagem().getAcoesBonus();}
        if(tipoObjeto.equals("dano")){valorTotal=getPersonagem().getDano();valor=getPersonagem().getDanoBase();var=getPersonagem().getDanoBonus();}
        if(tipoObjeto.equals("reducaoDano")){valorTotal=getPersonagem().getReducaoDano();valor=getPersonagem().getReducaoDanoBase();var=getPersonagem().getReducaoDanoBonus();}
        if(tipoObjeto.equals("acerto")){valorTotal=getPersonagem().getAcerto();valor=getPersonagem().getAcertoBase();var=getPersonagem().getAcertoBonus();}
        if(tipoObjeto.equals("reflexo")){valorTotal=getPersonagem().getReflexo();valor=getPersonagem().getReflexoBase();var=getPersonagem().getReflexoBonus();}
        if(tipoObjeto.equals("resistencia")){valorTotal=getPersonagem().getResistencia();valor=getPersonagem().getResistenciaBase();var=getPersonagem().getResistenciaBonus();}
        if(tipoObjeto.equals("percepcao")){valorTotal=getPersonagem().getPercepcao();valor=getPersonagem().getPercepcaoBase();var=getPersonagem().getPercepcaoBonus();}
        if(tipoObjeto.equals("vontade")){valorTotal=getPersonagem().getVontade();valor=getPersonagem().getVontadeBase();var=getPersonagem().getVontadeBonus();}
        if(tipoObjeto.equals("multiplicador")){valorTotal=getPersonagem().getMultiplicador();valor=getPersonagem().getMultiplicadorBase();var=getPersonagem().getMultiplicadorBonus();}

        valorBase=valor;
        editSoma.setText(String.valueOf(valor));
        editTotal.setText(String.valueOf(valorTotal));
        editBonus1.setText(String.valueOf(var[0]));
        editBonus2.setText(String.valueOf(var[1]));
        editBonus3.setText(String.valueOf(var[2]));
        editBonus4.setText(String.valueOf(var[3]));
        return var.clone();
    }

    @Override
    public void onDestroyView() {
        fragment_personagem_3del5_atributos.ativaBtnsPersonagem3del5Atributos();
        super.onDestroyView();

    }

    public String getTipoObjeto() {
        return tipoObjeto;
    }

    public void setTipoObjeto(String tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }
}
