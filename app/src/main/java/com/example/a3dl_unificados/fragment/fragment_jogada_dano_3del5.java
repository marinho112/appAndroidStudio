package com.example.a3dl_unificados.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a3dl_unificados.R;

import static com.example.a3dl_unificados.ferramentas.ferramentasComuns.editGetInteiroCorrigido;
import static com.example.a3dl_unificados.ferramentas.ferramentasComuns.verificaNumeroEdit;


public class fragment_jogada_dano_3del5 extends DialogFragment {


    private int dano,multiplicador,danoBonus,multiplicadorBonus;
    private View viewPai;
    private static boolean ativado=true;
    private boolean RD=false;
    public fragment_jogada_dano_3del5() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public fragment_jogada_dano_3del5(View view,int dano, int multiplicador,boolean RD) {
        this.viewPai=view;
        this.dano=dano;
        this.multiplicador=multiplicador;
        this.RD=RD;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View inflate = inflater.inflate(R.layout.fragment_jogada_dano_3del5, container, false);
        atualizarValores(inflate);
        definirBtnRollar(inflate);
        definirEventoFocoEditText(inflate);
        definirEventosClick(inflate);
        if(RD){definirComoReducaoDano(inflate);}

        return inflate;
    }

    private void definirEventosClick(final View inflate) {
        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ativado) {
                    atualizarValores(inflate);
                }
            }
        };

        inflate.findViewById(R.id.jogada_dano_3del5_txtMultiplicador).setOnClickListener(click);
        inflate.findViewById(R.id.jogada_dano_3del5_txtDano).setOnClickListener(click);
        inflate.findViewById(R.id.jogada_dano_3del5_editResultadoMultiplicador).setOnClickListener(click);
        inflate.findViewById(R.id.jogada_dano_3del5_editResultadoDano).setOnClickListener(click);
        inflate.findViewById(R.id.jogada_dano_3del5_txtIgualMultiplicador).setOnClickListener(click);
        inflate.findViewById(R.id.jogada_dano_3del5_txtIgualDano).setOnClickListener(click);
        inflate.findViewById(R.id.jogada_dano_3del5_txtSomaMultiplicador).setOnClickListener(click);
        inflate.findViewById(R.id.jogada_dano_3del5_txtSomaDano).setOnClickListener(click);
    }

    private void definirComoReducaoDano(View inflate){
        TextView text = inflate.findViewById(R.id.jogada_dano_3del5_txtEscritoDano);
        text.setText("Redução de Dano");

    }

    private void definirEventoFocoEditText(final View inflate) {
        View.OnFocusChangeListener focus = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (ativado) {
                    String condicao = String.valueOf(((EditText) v).getText());
                    if (hasFocus) {
                        if (condicao.equals("0") || condicao.equals("0.0")) {
                            ((EditText) v).setText("");
                        }
                    } else {
                        verificaValoresNullos(inflate);
                    }
                }
            }
        };

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                atualizarValores(inflate);
            }
        };

        EditText edit = inflate.findViewById(R.id.jogada_dano_3del5_editDano);
        edit.addTextChangedListener(watcher);
        edit.setOnFocusChangeListener(focus);

        edit=inflate.findViewById(R.id.jogada_dano_3del5_editMultiplicador);
        edit.setOnFocusChangeListener(focus);
        edit.addTextChangedListener(watcher);
    }

    private void definirBtnRollar(final View inflate) {
        //Jogadabtr
        Button btnRollar = inflate.findViewById(R.id.jogada_dano_3del5_btnRolar);
        btnRollar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ativado) {
                    atualizarValores(inflate);
                    criaDialog(viewPai.getContext());
                }
            }
        });
    }

    private void criaDialog(Context context){
        ativado=false;
        AppCompatActivity activity = (AppCompatActivity) context;
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        fragment_jogada_dano_resultado_3del5 dialog = new fragment_jogada_dano_resultado_3del5((dano+danoBonus),(multiplicador+multiplicadorBonus));
        dialog.show(ft,"0");
    }

    public void atualizarValores(View view){
        EditText text= view.findViewById(R.id.jogada_dano_3del5_editDano);
        danoBonus=editGetInteiroCorrigido(text);
        text= view.findViewById(R.id.jogada_dano_3del5_editMultiplicador);
        multiplicadorBonus = editGetInteiroCorrigido(text);


        text= view.findViewById(R.id.jogada_dano_3del5_editResultadoDano);
        text.setText(String.valueOf(dano+danoBonus));
        text= view.findViewById(R.id.jogada_dano_3del5_editResultadoMultiplicador);
        text.setText(String.valueOf(multiplicador+multiplicadorBonus));

        TextView txt = view.findViewById(R.id.jogada_dano_3del5_txtDano);
        txt.setText(String.valueOf(dano));
        txt = view.findViewById(R.id.jogada_dano_3del5_txtMultiplicador);
        txt.setText(String.valueOf(multiplicador));


    }

    private void verificaValoresNullos(View view){
        EditText text= view.findViewById(R.id.jogada_dano_3del5_editDano);
        verificaNumeroEdit(text);
        text= view.findViewById(R.id.jogada_dano_3del5_editMultiplicador);
        verificaNumeroEdit(text);
    }


    private void alerta(String texto){
        AlertDialog alertDialog = new AlertDialog.Builder(viewPai.getContext()).create();
        alertDialog.setMessage(texto);
        alertDialog.show();
    }

    public static void ativarBtn3del5PersonagemJogadaDano(){
        ativado=true;
    }

    @Override
    public void onDestroyView() {
        fragment_personagem_3del5_atributos.ativaBtnsPersonagem3del5Atributos();
        super.onDestroyView();

    }
}
