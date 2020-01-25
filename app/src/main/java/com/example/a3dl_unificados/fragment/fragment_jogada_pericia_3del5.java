package com.example.a3dl_unificados.fragment;

import android.annotation.SuppressLint;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.a3dl_unificados.R;
import com.example.a3dl_unificados.objetos.objeto_3del5_personagem;

import java.text.NumberFormat;

import static com.example.a3dl_unificados.MainActivity.getPersonagem;
import static com.example.a3dl_unificados.ferramentas.ferramentas3del5.obterAdapterSpinnerAtributo;
import static com.example.a3dl_unificados.ferramentas.ferramentas3del5.valorDoSpinnerAtributo;
import static com.example.a3dl_unificados.ferramentas.ferramentasComuns.editGetInteiroCorrigido;
import static com.example.a3dl_unificados.ferramentas.ferramentasComuns.verificaNumeroEdit;


public class fragment_jogada_pericia_3del5 extends DialogFragment {

    private Context contextoPai;
    private float chanceSucesso;
    private int principal,secundario;
    private static boolean ativado=true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_jogada_pericia_3del5, container, false);

        definirListaSpinners(inflate);
        exibirValores(inflate);
        definirBtnRolar(inflate);
        verificaValoresNullos(inflate);
        definirAtualizacaoAutomatica(inflate);

        return inflate;
    }

    private void definirAtualizacaoAutomatica(View inflate) {

        definirAtualizacaoClick(inflate);
        definirAtualizacaoFoco(inflate);

    }

    private void definirAtualizacaoFoco(final View inflate) {
        EditText editTotalTeste = inflate.findViewById(R.id.editText_jogada_pericia_3del5_total_teste);
        EditText editDificuldade = inflate.findViewById(R.id.editText_jogada_pericia_3del5_dificuldade);
        EditText editBonusPercentual = inflate.findViewById(R.id.editText_jogada_pericia_3del5_bonus_percentual);
        EditText editBonusNumerico = inflate.findViewById(R.id.editText_jogada_pericia_3del5_bonus_numerico);
        Spinner spinnerPrincipal = inflate.findViewById(R.id.spinner_jogada_pericia_3del5_atributo_principal);
        Spinner spinnerSecundario = inflate.findViewById(R.id.spinner_jogada_pericia_3del5_atributo_secundario);

        View.OnFocusChangeListener OnFocusEvent = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String condicao=String.valueOf(((EditText)v).getText());
                if(hasFocus){
                    if(condicao.equals("0")||condicao.equals("0.0")){
                        ((EditText)v).setText("");
                    }
                }else{
                    verificaValoresNullos(inflate);
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
                exibirValores(inflate);
            }
        };

            AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                exibirValores(inflate);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };


        editDificuldade.setOnFocusChangeListener(OnFocusEvent);
        editDificuldade.addTextChangedListener(watcher);
        editBonusPercentual.setOnFocusChangeListener(OnFocusEvent);
        editBonusPercentual.addTextChangedListener(watcher);
        editBonusNumerico.setOnFocusChangeListener(OnFocusEvent);
        editBonusNumerico.addTextChangedListener(watcher);
        spinnerPrincipal.setOnItemSelectedListener(itemSelectedListener);
        spinnerSecundario.setOnItemSelectedListener(itemSelectedListener);


    }

    private void verificaValoresNullos(View inflate) {

        EditText editTeste = inflate.findViewById(R.id.editText_jogada_pericia_3del5_bonus_numerico);
        verificaNumeroEdit(editTeste);
        editTeste= inflate.findViewById(R.id.editText_jogada_pericia_3del5_bonus_percentual);
        verificaNumeroEdit(editTeste);
        editTeste=inflate.findViewById(R.id.editText_jogada_pericia_3del5_dificuldade);
        verificaNumeroEdit(editTeste);

    }

    private void definirAtualizacaoClick(final View inflate) {

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirValores(inflate);
            }
        };

        inflate.findViewById(R.id.textView_jogada_pericia_3del5_atributo_principal).setOnClickListener(clickListener);
        inflate.findViewById(R.id.textView_jogada_pericia_3del5_atributo_secundario).setOnClickListener(clickListener);
        inflate.findViewById(R.id.textView_jogada_pericia_3del5_bonus).setOnClickListener(clickListener);
        inflate.findViewById(R.id.textView_jogada_pericia_3del5_bonus_percentual).setOnClickListener(clickListener);
        inflate.findViewById(R.id.textView_jogada_pericia_3del5_valor_total).setOnClickListener(clickListener);
        inflate.findViewById(R.id.textView_jogada_pericia_3del5_dificuldade).setOnClickListener(clickListener);
        inflate.findViewById(R.id.textView_jogada_pericia_3del5_chance_final).setOnClickListener(clickListener);
        inflate.findViewById(R.id.editText_jogada_pericia_3del5_total_teste).setOnClickListener(clickListener);
    }

    private void definirBtnRolar(final View inflate) {
        inflate.findViewById(R.id.btn_rollar_jogada_pericia_3del5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ativado) {
                    exibirValores(inflate);
                    criaDialog(contextoPai, chanceSucesso);
                }
            }
        });
    }

    public fragment_jogada_pericia_3del5() {

    }

    @SuppressLint("ValidFragment")
    public fragment_jogada_pericia_3del5(Context context,int principal,int secundario) {
        contextoPai=context;
        this.secundario=secundario;
        this.principal=principal;
    }

    private void exibirValores(View inflate){

        int atributoPrincipal,atributoSecundario,bonus,total,dificuldade;
        float bonusPercentual;

        atributoPrincipal= valorDoSpinnerAtributo(((Spinner)inflate.findViewById(R.id.spinner_jogada_pericia_3del5_atributo_principal)).getSelectedItemPosition(),getPersonagem());
        atributoSecundario= valorDoSpinnerAtributo(((Spinner)inflate.findViewById(R.id.spinner_jogada_pericia_3del5_atributo_secundario)).getSelectedItemPosition(),getPersonagem());

        EditText editTeste = inflate.findViewById(R.id.editText_jogada_pericia_3del5_bonus_numerico);
        bonus=editGetInteiroCorrigido(editTeste);

        editTeste= inflate.findViewById(R.id.editText_jogada_pericia_3del5_bonus_percentual);
        bonusPercentual=editGetInteiroCorrigido(editTeste);

        editTeste=inflate.findViewById(R.id.editText_jogada_pericia_3del5_dificuldade);
        dificuldade=editGetInteiroCorrigido(editTeste);

        total=atributoPrincipal+(atributoSecundario/2)+bonus;
        chanceSucesso=calcularSucesso(total,dificuldade,bonusPercentual);
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(1);
        format.setMinimumFractionDigits(1);

        ((EditText)inflate.findViewById(R.id.editText_jogada_pericia_3del5_total_teste)).setText(String.valueOf(total));
        ((TextView)inflate.findViewById(R.id.textView_jogada_pericia_3del5_chance_final)).setText(format.format(chanceSucesso) +"%");

    }


    private void definirListaSpinners(View inflate) {
        ArrayAdapter<String> stringArrayAdapter= obterAdapterSpinnerAtributo(inflate.getContext());
        Spinner spinner = inflate.findViewById(R.id.spinner_jogada_pericia_3del5_atributo_principal);
        spinner.setAdapter(stringArrayAdapter);
        spinner.setSelection(principal);
        spinner=inflate.findViewById(R.id.spinner_jogada_pericia_3del5_atributo_secundario);
        spinner.setAdapter(stringArrayAdapter);
        spinner.setSelection(secundario);

    }



    private float calcularSucesso(int total, int dificuldade, float bonusPercentual) {
        float retorno=50;
        int diferença,i;
        if(total>dificuldade){
            diferença=total-dificuldade;
            for(i=0;i<diferença;i++){
                retorno*=0.95;
            }
            retorno=((100-retorno)+(retorno*(bonusPercentual/100)));
        }else{
            diferença=dificuldade-total;
            retorno*=1+(bonusPercentual/100);
            for(i=0;i<diferença;i++){
                retorno*=0.95;
            }
        }
        return retorno;
    }



    private void criaDialog(Context context, float chance){
        ativado=false;
        AppCompatActivity activity = (AppCompatActivity) context;
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        fragment_tela_resultado_rolagem dialog = new fragment_tela_resultado_rolagem(chance);
        dialog.show(ft,"0");
    }


    public static void ativarBtn3del5PersonagemJogadaPericia(){
        ativado=true;
    }

    @Override
    public void onDestroyView() {
        fragment_personagem_3del5_atributos.ativaBtnsPersonagem3del5Atributos();
        super.onDestroyView();

    }
}
