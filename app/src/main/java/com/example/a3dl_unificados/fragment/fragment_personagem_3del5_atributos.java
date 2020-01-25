package com.example.a3dl_unificados.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a3dl_unificados.R;
import com.example.a3dl_unificados.adapters.AdapterPersonagem3del5;
import com.example.a3dl_unificados.ferramentas.ferramentas3del5;
import com.example.a3dl_unificados.ferramentas.ferramentasComuns;
import com.example.a3dl_unificados.objetos.objeto_3del5_personagem;

import java.text.DecimalFormat;

import static com.example.a3dl_unificados.MainActivity.getPersonagem;
import static com.example.a3dl_unificados.dao.bdInternoDAO.inserirPersonagem3del5;
import static com.example.a3dl_unificados.ferramentas.ferramentas3del5.alerta;


public class fragment_personagem_3del5_atributos extends Fragment {

    private AdapterPersonagem3del5 adapter;
    private Context context;
    private static Boolean ativado=true;
    private View inflate;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_personagem_3del5_atributos, container, false);

        definirCampos();
        definirEventoMudancaTexto();
        definirEventosDeFoco();
        definirOnClickEditores();
        definirOnClickTextView();

        context=inflate.getContext();
        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();
        atualizaPersonagem();
    }

    public void definirCampos() {
        definirCamposSemCalculo();
        definirCamposComCalculo();
        defineFab();
    }

    private void defineFab() {
        inflate.findViewById(R.id.fab_personagem_3del5_atributos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               criaDialogValores(inflate.getContext(),getPersonagem(),0,0);
            }
        });
    }

    private void defineOnClickRolagemAtributo() {
        View view;
        view = inflate.findViewById(R.id.textView_valor_acerto);
        definirRolagemAtributo(view,2,3);
        view = inflate.findViewById(R.id.textView_valor_reflexo);
        definirRolagemAtributo(view,4,3);
        view = inflate.findViewById(R.id.textView_valor_resistencia);
        definirRolagemAtributo(view,1,0);
        view = inflate.findViewById(R.id.textView_valor_vontade);
        definirRolagemAtributo(view,5,4);
        view = inflate.findViewById(R.id.textView_valor_percepcao);
        definirRolagemAtributo( view,4,5);

    }

    private void definirRolagemAtributo(View view, final int principal, final int secundario) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ativado) {
                    criaDialogValores(inflate.getContext(), getPersonagem(), principal, secundario);
                }
            }
        });
    }

    private void definirOnClickEditor(final View view, final String tipo) {


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ativado){
                    atualizaPersonagem();
                    criaDialogEditor(inflate.getContext(),tipo);
                }
            }
        });

    }


    private void definirOnClickEditores() {

        EditText editText;
        editText=inflate.findViewById(R.id.editText_valores_pv_atual);
        definirOnClickEditor(editText,"pv");
        editText=inflate.findViewById(R.id.editText_acoes);
        definirOnClickEditor(editText,"acoes");
        editText=inflate.findViewById(R.id.editText_valor_dano);
        definirOnClickEditor(editText,"dano");
        editText=inflate.findViewById(R.id.editText_valor_reducao_dano);
        definirOnClickEditor(editText,"reducaoDano");
        editText=inflate.findViewById(R.id.editText_valor_acerto);
        definirOnClickEditor(editText,"acerto");
        editText=inflate.findViewById(R.id.editText_valor_reflexo);
        definirOnClickEditor(editText,"reflexo");
        editText=inflate.findViewById(R.id.editText_valor_resistencia);
        definirOnClickEditor(editText,"resistencia");
        editText=inflate.findViewById(R.id.editText_valor_percepcao);
        definirOnClickEditor(editText,"percepcao");
        editText=inflate.findViewById(R.id.editText_valor_vontade);
        definirOnClickEditor(editText,"vontade");
        TextView textView=inflate.findViewById(R.id.textView_valor_multiplicador);
        definirOnClickEditor(textView,"multiplicador");

    }

    private void definirOnClickEditorDano(TextView textView, final View view, final objeto_3del5_personagem personagem){

       textView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(ativado){
                   atualizaPersonagem();
                   criaDialogDano(view, personagem);
               }

           }
       });

    }

    private void definirOnClickEditorReducaoDano(TextView textView, final View view, final objeto_3del5_personagem personagem){

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ativado){
                    atualizaPersonagem();
                    criaDialogReducaoDano(view, personagem);
                }

            }
        });

    }

    private void definirOnClickEditorAtualiza(TextView textView){

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ativado){
                    atualizaPersonagem();
                }

            }
        });

    }



    private void definirOnClickTextView() {
        TextView textView;
        //atualiza tela e abre janela de jogada de pericia
        defineOnClickRolagemAtributo();
        //atualiza tela e abre janela de Dano
        textView=inflate.findViewById(R.id.textView_valor_dano);
        definirOnClickEditorDano(textView,inflate,getPersonagem());
        //atualiza tela e abre janela de Redução de Dano
        textView=inflate.findViewById(R.id.textView_valor_reducao_dano);
        definirOnClickEditorReducaoDano(textView,inflate,getPersonagem());
        //apenas atualiza tela
        textView=inflate.findViewById(R.id.textView_valores_pv);
        definirOnClickEditorAtualiza(textView);
        textView=inflate.findViewById(R.id.textView_valores_acoes);
        definirOnClickEditorAtualiza(textView);

    }


    private void definirCamposComCalculo() {

        calcularAtributoTotal();
        calcularAtributosRelacionais();

    }

    private void calcularAtributosRelacionais() {
        DecimalFormat decimalFormat=new DecimalFormat("0.0");
        TextView textPts = inflate.findViewById(R.id.textView_valores_pontosGastos);
        textPts.setText("Ficha: "+getPersonagem().getPtsFicha()+"pts");
        EditText editPvTotal = inflate.findViewById(R.id.editText_valores_pv_atual);
        editPvTotal.setText(String.valueOf(getPersonagem().getPv()));
        EditText editLevPeso = inflate.findViewById(R.id.editText_peso_levantar);
        String st="Kg";
        float fl=getPersonagem().getLevPeso();String numero;
        if(fl>=1000){st="T";fl/=1000;}
        numero=String.valueOf((int)fl);
        editLevPeso.setText(numero+st);

        EditText editAltSalto = inflate.findViewById(R.id.editText_altura_saltar);
        st="m";
        fl=getPersonagem().getAltSalto();
        if(fl>=1000){st="Km";fl/=1000;}
        numero=decimalFormat.format(fl);
        editAltSalto.setText(numero+st);
        
        EditText editVelCorrida = inflate.findViewById(R.id.editText_velocidade_correr);
        editVelCorrida.setText(getPersonagem().getVelCorrida() +"km/h");
        EditText editAcoes = inflate.findViewById(R.id.editText_acoes);
        editAcoes.setText(String.valueOf(getPersonagem().getAcoes()));

        EditText editDano = inflate.findViewById(R.id.editText_valor_dano);
        editDano.setText(formataConteudoVariavelRelacional(getPersonagem().getDano(),getPersonagem().getDanoBase(),getPersonagem().getDanoBonus()));
        EditText editReducaoDano = inflate.findViewById(R.id.editText_valor_reducao_dano);
        editReducaoDano.setText(formataConteudoVariavelRelacional(getPersonagem().getReducaoDano(),getPersonagem().getReducaoDanoBase(),getPersonagem().getReducaoDanoBonus()));
        EditText editAcerto = inflate.findViewById(R.id.editText_valor_acerto);
        editAcerto.setText(formataConteudoVariavelRelacional(getPersonagem().getAcerto(),getPersonagem().getAcertoBase(),getPersonagem().getAcertoBonus()));
        EditText editReflexo = inflate.findViewById(R.id.editText_valor_reflexo);
        editReflexo.setText(formataConteudoVariavelRelacional(getPersonagem().getReflexo(),getPersonagem().getReflexoBase(),getPersonagem().getReflexoBonus()));
        EditText editResistencia = inflate.findViewById(R.id.editText_valor_resistencia);
        editResistencia.setText(formataConteudoVariavelRelacional(getPersonagem().getResistencia(),getPersonagem().getResistenciaBase(),getPersonagem().getResistenciaBonus()));
        EditText editVontade = inflate.findViewById(R.id.editText_valor_vontade);
        editVontade.setText(formataConteudoVariavelRelacional(getPersonagem().getVontade(),getPersonagem().getVontadeBase(),getPersonagem().getVontadeBonus()));
        EditText editPerpcao = inflate.findViewById(R.id.editText_valor_percepcao);
        editPerpcao.setText(formataConteudoVariavelRelacional(getPersonagem().getPercepcao(),getPersonagem().getPercepcaoBase(),getPersonagem().getPercepcaoBonus()));

        EditText editMultiplicador1 = inflate.findViewById(R.id.editText_valor_multiplicador_um);
        editMultiplicador1.setText(String.valueOf(getPersonagem().getMultiplicador()));
        EditText editMultiplicador2 = inflate.findViewById(R.id.editText_valor_multiplicador_dois);
        editMultiplicador2.setText(String.valueOf(getPersonagem().getMultiplicador()*2));
        EditText editMultiplicador3 = inflate.findViewById(R.id.editText_valor_multiplicador_tres);
        editMultiplicador3.setText(String.valueOf(getPersonagem().getMultiplicador()*3));
        EditText editMultiplicador4 = inflate.findViewById(R.id.editText_valor_multiplicador_quatro);
        editMultiplicador4.setText(String.valueOf(getPersonagem().getMultiplicador()*4));
        EditText editMultiplicador5 = inflate.findViewById(R.id.editText_valor_multiplicador_cinco);
        editMultiplicador5.setText(String.valueOf(getPersonagem().getMultiplicador()*5));

    }

    private SpannableString formataConteudoVariavelRelacional(int total, int base, int[] vetor) {
        boolean zerar=true;
        String retorno=total +" = " + base;
        if(vetor[0]!=0){
            zerar=false;
            if(vetor[0]>0){
                retorno+=" + " + vetor[0];
            }else{
                retorno+=" - " + (-1*vetor[0]);
            }

        }
        if(vetor[1]!=0){
            zerar=false;
            if(vetor[1]>0){
                retorno+=" + " + vetor[1];
            }else{
                retorno+=" - " + (-1*vetor[1]);
            }
        }
        if(vetor[2]!=0){
            zerar=false;
            if(vetor[2]>0){
                retorno+=" + " + vetor[2];
            }else{
                retorno+=" - " + (-1*vetor[2]);
            }
        }
        if(vetor[3]!=0){
            zerar=false;
            if(vetor[3]>0){
                retorno+=" + " + vetor[3];
            }else{
                retorno+=" - " + (-1*vetor[3]);
            }
        }

        if(zerar){ return new SpannableString(""+total);}

        int[] tamanhos = definirTamanhos(total, base, vetor);

        SpannableString ss = obterTextoColorido(retorno, tamanhos);

        return ss;
    }

    private SpannableString obterTextoColorido(String retorno, int[] tamanhos) {
        SpannableString ss=new SpannableString(retorno);
        ForegroundColorSpan fcsTotal=new ForegroundColorSpan(Color.BLACK);
        ForegroundColorSpan fcsBase=new ForegroundColorSpan(Color.GRAY);
        ForegroundColorSpan fcsB1=new ForegroundColorSpan(Color.rgb(8,74,134));
        ForegroundColorSpan fcsB2=new ForegroundColorSpan(Color.rgb(34,118,3));
        ForegroundColorSpan fcsB3=new ForegroundColorSpan(Color.rgb(255,196,33));
        ForegroundColorSpan fcsB4=new ForegroundColorSpan(Color.rgb(195,14,21));

        ss.setSpan(fcsTotal,tamanhos[0],tamanhos[1], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(fcsBase,tamanhos[2],tamanhos[3], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(fcsB1,tamanhos[4],tamanhos[5], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(fcsB2,tamanhos[6],tamanhos[7], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(fcsB3,tamanhos[8],tamanhos[9], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(fcsB4,tamanhos[10],tamanhos[11], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    private int[] definirTamanhos(int total, int base, int[] vetor) {
        int[] tamanhos=new int[12];
        tamanhos[0]=0;
        tamanhos[1]=String.valueOf(total).length();
        tamanhos[2]=tamanhos[1]+3;
        tamanhos[3] = tamanhos[2] + String.valueOf(base).length();
        if(vetor[0]!=0) {
            tamanhos[4] = tamanhos[3] + 1;
            tamanhos[5] = tamanhos[4] + String.valueOf(positivoDe(vetor[0])).length() + 2;
        }else{
            tamanhos[4]=tamanhos[3];
            tamanhos[5]=tamanhos[3];
        }
        if(vetor[1]!=0) {
            tamanhos[6] = tamanhos[5] + 1;
            tamanhos[7] = tamanhos[6] + String.valueOf(positivoDe(vetor[1])).length() + 2;
        }
        else{
            tamanhos[6]=tamanhos[5];
            tamanhos[7]=tamanhos[5];
        }
        if(vetor[2]!=0) {
            tamanhos[8] = tamanhos[7] + 1;
            tamanhos[9] = tamanhos[8] + String.valueOf(positivoDe(vetor[2])).length() + 2;
        }
        else{
            tamanhos[8]=tamanhos[7];
            tamanhos[9]=tamanhos[7];
        }
        if(vetor[3]!=0) {
            tamanhos[10] = tamanhos[9] + 1;
            tamanhos[11] = tamanhos[10] + String.valueOf(positivoDe(vetor[3])).length() + 2;
        }
        return tamanhos;
    }

    private int positivoDe(int i){
        if(i<0){return -1*i;}
        else{return i;}
    }
    private void calcularAtributoTotal() {
        EditText editForcaTotal = inflate.findViewById(R.id.editText_atributos_forca_total);
        editForcaTotal.setText(String.valueOf(getPersonagem().getForca()+getPersonagem().getForcaBonus()));
        EditText editVitalidadeTotal = inflate.findViewById(R.id.editText_atributos_vitalidade_total);
        editVitalidadeTotal.setText(String.valueOf(getPersonagem().getVitalidade()+getPersonagem().getVitalidadeBonus()));
        EditText editDestrezaTotal = inflate.findViewById(R.id.editText_atributos_destreza_total);
        editDestrezaTotal.setText(String.valueOf(getPersonagem().getDestreza()+getPersonagem().getDestrezaBonus()));
        EditText editAgilidadeTotal = inflate.findViewById(R.id.editText_atributos_agilidade_total);
        editAgilidadeTotal.setText(String.valueOf(getPersonagem().getAgilidade()+getPersonagem().getAgilidadeBonus()));
        EditText editSentidosTotal = inflate.findViewById(R.id.editText_atributos_sentidos_total);
        editSentidosTotal.setText(String.valueOf(getPersonagem().getSentidos()+getPersonagem().getSentidosBonus()));
        EditText editInteligenciaTotal = inflate.findViewById(R.id.editText_atributos_inteligencia_total);
        editInteligenciaTotal.setText(String.valueOf(getPersonagem().getInteligengia()+getPersonagem().getInteligengiaBonus()));
        EditText editAdicionalUmTotal = inflate.findViewById(R.id.editText_atributos_AdicionalUm_total);
        editAdicionalUmTotal.setText(String.valueOf(getPersonagem().getAtributo1()+getPersonagem().getAtributo1Bonus()));
        EditText editAdicionalDoisTotal = inflate.findViewById(R.id.editText_atributos_AdicionalDois_total);
        editAdicionalDoisTotal.setText(String.valueOf(getPersonagem().getAtributo2()+getPersonagem().getAtributo2Bonus()));


    }

    private void definirCamposSemCalculo() {
        EditText editForcaBase = inflate.findViewById(R.id.editText_atributos_forca_base);
        editForcaBase.setText(String.valueOf(getPersonagem().getForca()));
        EditText editForcaBonus = inflate.findViewById(R.id.editText_atributos_forca_bonus);
        editForcaBonus.setText(String.valueOf(getPersonagem().getForcaBonus()));
        EditText editVitalidadeBase = inflate.findViewById(R.id.editText_atributos_vitalidade_base);
        editVitalidadeBase.setText(String.valueOf(getPersonagem().getVitalidade()));
        EditText editVitalidadeBonus = inflate.findViewById(R.id.editText_atributos_vitalidade_bonus);
        editVitalidadeBonus.setText(String.valueOf(getPersonagem().getVitalidadeBonus()));
        EditText editDestrezaBase = inflate.findViewById(R.id.editText_atributos_destreza_base);
        editDestrezaBase.setText(String.valueOf(getPersonagem().getDestreza()));
        EditText editDestrezaBonus = inflate.findViewById(R.id.editText_atributos_destreza_bonus);
        editDestrezaBonus.setText(String.valueOf(getPersonagem().getDestrezaBonus()));
        EditText editAgilidadeBase = inflate.findViewById(R.id.editText_atributos_agilidade_base);
        editAgilidadeBase.setText(String.valueOf(getPersonagem().getAgilidade()));
        EditText editAgilidadeBonus = inflate.findViewById(R.id.editText_atributos_agilidade_bonus);
        editAgilidadeBonus.setText(String.valueOf(getPersonagem().getAgilidadeBonus()));
        EditText editSentidosBase = inflate.findViewById(R.id.editText_atributos_sentidos_base);
        editSentidosBase.setText(String.valueOf(getPersonagem().getSentidos()));
        EditText editSentidosBonus = inflate.findViewById(R.id.editText_atributos_sentidos_bonus);
        editSentidosBonus.setText(String.valueOf(getPersonagem().getSentidosBonus()));
        EditText editInteligenciaBase = inflate.findViewById(R.id.editText_atributos_inteligencia_base);
        editInteligenciaBase.setText(String.valueOf(getPersonagem().getInteligengia()));
        EditText editInteligenciaBonus = inflate.findViewById(R.id.editText_atributos_inteligencia_bonus);
        editInteligenciaBonus.setText(String.valueOf(getPersonagem().getInteligengiaBonus()));

        EditText editAdicionalUmBase = inflate.findViewById(R.id.editText_atributos_AdicionalUm_base);
        editAdicionalUmBase.setText(String.valueOf(getPersonagem().getAtributo1()));
        EditText editAdicionalUmBonus = inflate.findViewById(R.id.editText_atributos_AdicionalUm_bonus);
        editAdicionalUmBonus.setText(String.valueOf(getPersonagem().getAtributo1Bonus()));
        EditText editAdicionalUmNome = inflate.findViewById(R.id.editText_atributos_AdicionalUm_nome);
        editAdicionalUmNome.setText(getPersonagem().getAtributo1Nome());
        EditText editAdicionalDoisBase = inflate.findViewById(R.id.editText_atributos_AdicionalDois_base);
        editAdicionalDoisBase.setText(String.valueOf(getPersonagem().getAtributo2()));
        EditText editAdicionalDoisBonus = inflate.findViewById(R.id.editText_atributos_AdicionalDois_bonus);
        editAdicionalDoisBonus.setText(String.valueOf(getPersonagem().getAtributo2Bonus()));
        EditText editAdicionalDoisNome = inflate.findViewById(R.id.editText_atributos_AdicionalDois_nome);
        editAdicionalDoisNome.setText(getPersonagem().getAtributo2Nome());


        EditText editPvPerdido = inflate.findViewById(R.id.editText_valores_pv_perdido);
        editPvPerdido.setText(String.valueOf(getPersonagem().getPvPerdido()));
        EditText editDeslocamento = inflate.findViewById(R.id.editText_valores_deslocamento);
        editDeslocamento.setText(String.valueOf(getPersonagem().getDeslocamento()));
    }

    private void definirEventoMudancaTexto(){

        EditText editForcaBase=inflate.findViewById(R.id.editText_atributos_forca_base);
        EditText editForcaBonus = inflate.findViewById(R.id.editText_atributos_forca_bonus);
        EditText editVitalidadeBase = inflate.findViewById(R.id.editText_atributos_vitalidade_base);
        EditText editVitalidadeBonus = inflate.findViewById(R.id.editText_atributos_vitalidade_bonus);
        EditText editDestrezaBase = inflate.findViewById(R.id.editText_atributos_destreza_base);
        EditText editDestrezaBonus = inflate.findViewById(R.id.editText_atributos_destreza_bonus);
        EditText editAgilidadeBase = inflate.findViewById(R.id.editText_atributos_agilidade_base);
        EditText editAgilidadeBonus = inflate.findViewById(R.id.editText_atributos_agilidade_bonus);
        EditText editSentidosBase = inflate.findViewById(R.id.editText_atributos_sentidos_base);
        EditText editSentidosBonus = inflate.findViewById(R.id.editText_atributos_sentidos_bonus);
        EditText editInteligenciaBase = inflate.findViewById(R.id.editText_atributos_inteligencia_base);
        EditText editInteligenciaBonus = inflate.findViewById(R.id.editText_atributos_inteligencia_bonus);


        EditText editAdicionalUmBase = inflate.findViewById(R.id.editText_atributos_AdicionalUm_base);
        EditText editAdicionalUmBonus = inflate.findViewById(R.id.editText_atributos_AdicionalUm_bonus);
        EditText editAdicionalUmNome = inflate.findViewById(R.id.editText_atributos_AdicionalUm_nome);
        EditText editAdicionalDoisBase = inflate.findViewById(R.id.editText_atributos_AdicionalDois_base);
        EditText editAdicionalDoisBonus = inflate.findViewById(R.id.editText_atributos_AdicionalDois_bonus);
        EditText editAdicionalDoisNome = inflate.findViewById(R.id.editText_atributos_AdicionalDois_nome);


        EditText editPvPerdido = inflate.findViewById(R.id.editText_valores_pv_perdido);
        EditText editDeslocamento = inflate.findViewById(R.id.editText_valores_deslocamento);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                atualizaPersonagem();
            }
        };

        editForcaBase.addTextChangedListener(watcher);
        editForcaBonus.addTextChangedListener(watcher);
        editVitalidadeBase.addTextChangedListener(watcher);
        editVitalidadeBonus.addTextChangedListener(watcher);
        editDestrezaBase.addTextChangedListener(watcher);
        editDestrezaBonus.addTextChangedListener(watcher);
        editAgilidadeBase.addTextChangedListener(watcher);
        editAgilidadeBonus.addTextChangedListener(watcher);
        editSentidosBase.addTextChangedListener(watcher);
        editSentidosBonus.addTextChangedListener(watcher);
        editInteligenciaBase.addTextChangedListener(watcher);
        editInteligenciaBonus.addTextChangedListener(watcher);

        editAdicionalUmBase.addTextChangedListener(watcher);
        editAdicionalUmBonus.addTextChangedListener(watcher);
        editAdicionalUmNome.addTextChangedListener(watcher);
        editAdicionalDoisBase.addTextChangedListener(watcher);
        editAdicionalDoisBonus.addTextChangedListener(watcher);
        editAdicionalDoisNome.addTextChangedListener(watcher);

        editPvPerdido.addTextChangedListener(watcher);
        editDeslocamento.addTextChangedListener(watcher);



    }

    private void definirEventosDeFoco() {

        View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String condicao=String.valueOf(((EditText)v).getText());
                if(hasFocus){
                    if(condicao.equals("0")||condicao.equals("0.0")){
                        ((EditText)v).setText("");
                    }
                }else{
                    EditText atual=(EditText)v;
                    if((atual.getText().length()==0)||(String.valueOf(atual.getText()).equals("-"))){
                        atual.setText("0");
                    }
                    atualizaPersonagem();
                }
            }
        };

        EditText editForcaBase = inflate.findViewById(R.id.editText_atributos_forca_base);
        editForcaBase.setOnFocusChangeListener(onFocusChangeListener);
        EditText editForcaBonus = inflate.findViewById(R.id.editText_atributos_forca_bonus);
        editForcaBonus.setOnFocusChangeListener(onFocusChangeListener);
        EditText editVitalidadeBase = inflate.findViewById(R.id.editText_atributos_vitalidade_base);
        editVitalidadeBase.setOnFocusChangeListener(onFocusChangeListener);
        EditText editVitalidadeBonus = inflate.findViewById(R.id.editText_atributos_vitalidade_bonus);
        editVitalidadeBonus.setOnFocusChangeListener(onFocusChangeListener);
        EditText editDestrezaBase = inflate.findViewById(R.id.editText_atributos_destreza_base);
        editDestrezaBase.setOnFocusChangeListener(onFocusChangeListener);
        EditText editDestrezaBonus = inflate.findViewById(R.id.editText_atributos_destreza_bonus);
        editDestrezaBonus.setOnFocusChangeListener(onFocusChangeListener);
        EditText editAgilidadeBase = inflate.findViewById(R.id.editText_atributos_agilidade_base);
        editAgilidadeBase.setOnFocusChangeListener(onFocusChangeListener);
        EditText editAgilidadeBonus = inflate.findViewById(R.id.editText_atributos_agilidade_bonus);
        editAgilidadeBonus.setOnFocusChangeListener(onFocusChangeListener);
        EditText editSentidosBase = inflate.findViewById(R.id.editText_atributos_sentidos_base);
        editSentidosBase.setOnFocusChangeListener(onFocusChangeListener);
        EditText editSentidosBonus = inflate.findViewById(R.id.editText_atributos_sentidos_bonus);
        editSentidosBonus.setOnFocusChangeListener(onFocusChangeListener);
        EditText editInteligenciaBase = inflate.findViewById(R.id.editText_atributos_inteligencia_base);
        editInteligenciaBase.setOnFocusChangeListener(onFocusChangeListener);
        EditText editInteligenciaBonus = inflate.findViewById(R.id.editText_atributos_inteligencia_bonus);
        editInteligenciaBonus.setOnFocusChangeListener(onFocusChangeListener);

        EditText editAdicionalUmBase = inflate.findViewById(R.id.editText_atributos_AdicionalUm_base);
        editAdicionalUmBase.setOnFocusChangeListener(onFocusChangeListener);
        EditText editAdicionalUmBonus = inflate.findViewById(R.id.editText_atributos_AdicionalUm_bonus);
        editAdicionalUmBonus.setOnFocusChangeListener(onFocusChangeListener);
        EditText editAdicionalDoisBase = inflate.findViewById(R.id.editText_atributos_AdicionalDois_base);
        editAdicionalDoisBase.setOnFocusChangeListener(onFocusChangeListener);
        EditText editAdicionalDoisBonus = inflate.findViewById(R.id.editText_atributos_AdicionalDois_bonus);
        editAdicionalDoisBonus.setOnFocusChangeListener(onFocusChangeListener);

        EditText editPvPerdido = inflate.findViewById(R.id.editText_valores_pv_perdido);
        editPvPerdido.setOnFocusChangeListener(onFocusChangeListener);
        EditText editDeslocamento = inflate.findViewById(R.id.editText_valores_deslocamento);
        editDeslocamento.setOnFocusChangeListener(onFocusChangeListener);

    }


    public void atualizaPersonagem() {
        salvarCamposExplicitos();
        definirCamposComCalculo();
        inserirPersonagem3del5(getPersonagem());
    }


    private void criaDialogEditor(Context context, String tipo){
        ativado=false;
        AppCompatActivity activity = (AppCompatActivity) context;
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        fragment_editor_valores_personagem_3del5 dialog = new fragment_editor_valores_personagem_3del5(inflate,this,tipo);
        dialog.show(ft,"0");
    }

    private void criaDialogValores(Context context, objeto_3del5_personagem personagem,int principal,int secundario){
        ativado=false;
        AppCompatActivity activity = (AppCompatActivity) context;
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        fragment_jogada_pericia_3del5 dialog = new fragment_jogada_pericia_3del5(context,principal,secundario);

        dialog.show(ft,"0");
    }

    private void criaDialogDano(View view, objeto_3del5_personagem personagem) {
        ativado=false;
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        fragment_jogada_dano_3del5 log = new fragment_jogada_dano_3del5(view,personagem.getDano(),personagem.getMultiplicador(),false);
        log.show(ft,"0");
    }

    private void criaDialogReducaoDano(View view, objeto_3del5_personagem personagem) {
        ativado=false;
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        fragment_jogada_dano_3del5 log = new fragment_jogada_dano_3del5(view,personagem.getReducaoDano(),personagem.getMultiplicador(),true);
        log.show(ft,"0");
    }

    private void salvarCamposExplicitos() {
        EditText editForcaBase = inflate.findViewById(R.id.editText_atributos_forca_base);
        getPersonagem().setForca(ferramentasComuns.editGetInteiroCorrigido(editForcaBase));
        EditText editForcaBonus = inflate.findViewById(R.id.editText_atributos_forca_bonus);
        getPersonagem().setForcaBonus(ferramentasComuns.editGetInteiroCorrigido(editForcaBonus));
        EditText editVitalidadeBase = inflate.findViewById(R.id.editText_atributos_vitalidade_base);
        getPersonagem().setVitalidade(ferramentasComuns.editGetInteiroCorrigido(editVitalidadeBase));
        EditText editVitalidadeBonus = inflate.findViewById(R.id.editText_atributos_vitalidade_bonus);
        getPersonagem().setVitalidadeBonus(ferramentasComuns.editGetInteiroCorrigido(editVitalidadeBonus));
        EditText editDestrezaBase = inflate.findViewById(R.id.editText_atributos_destreza_base);
        getPersonagem().setDestreza(ferramentasComuns.editGetInteiroCorrigido(editDestrezaBase));
        EditText editDestrezaBonus = inflate.findViewById(R.id.editText_atributos_destreza_bonus);
        getPersonagem().setDestrezaBonus(ferramentasComuns.editGetInteiroCorrigido(editDestrezaBonus));
        EditText editAgilidadeBase = inflate.findViewById(R.id.editText_atributos_agilidade_base);
        getPersonagem().setAgilidade(ferramentasComuns.editGetInteiroCorrigido(editAgilidadeBase));
        EditText editAgilidadeBonus = inflate.findViewById(R.id.editText_atributos_agilidade_bonus);
        getPersonagem().setAgilidadeBonus(ferramentasComuns.editGetInteiroCorrigido(editAgilidadeBonus));
        EditText editSentidosBase = inflate.findViewById(R.id.editText_atributos_sentidos_base);
        getPersonagem().setSentidos(ferramentasComuns.editGetInteiroCorrigido(editSentidosBase));
        EditText editSentidosBonus = inflate.findViewById(R.id.editText_atributos_sentidos_bonus);
        getPersonagem().setSentidosBonus(ferramentasComuns.editGetInteiroCorrigido(editSentidosBonus));
        EditText editInteligenciaBase = inflate.findViewById(R.id.editText_atributos_inteligencia_base);
        getPersonagem().setInteligengia(ferramentasComuns.editGetInteiroCorrigido(editInteligenciaBase));
        EditText editInteligenciaBonus = inflate.findViewById(R.id.editText_atributos_inteligencia_bonus);
        getPersonagem().setInteligengiaBonus(ferramentasComuns.editGetInteiroCorrigido(editInteligenciaBonus));

        EditText editAdicionalUmBase = inflate.findViewById(R.id.editText_atributos_AdicionalUm_base);
        getPersonagem().setAtributo1(ferramentasComuns.editGetInteiroCorrigido(editAdicionalUmBase));
        EditText editAdicionalUmBonus = inflate.findViewById(R.id.editText_atributos_AdicionalUm_bonus);
        getPersonagem().setAtributo1Bonus(ferramentasComuns.editGetInteiroCorrigido(editAdicionalUmBonus));
        EditText editAdicionalUmNome = inflate.findViewById(R.id.editText_atributos_AdicionalUm_nome);
        getPersonagem().setAtributo1Nome(String.valueOf(editAdicionalUmNome.getText()));
        EditText editAdicionalDoisBase = inflate.findViewById(R.id.editText_atributos_AdicionalDois_base);
        getPersonagem().setAtributo2(ferramentasComuns.editGetInteiroCorrigido(editAdicionalDoisBase));
        EditText editAdicionalDoisBonus = inflate.findViewById(R.id.editText_atributos_AdicionalDois_bonus);
        getPersonagem().setAtributo2Bonus(ferramentasComuns.editGetInteiroCorrigido(editAdicionalDoisBonus));
        EditText editAdicionalDoisNome = inflate.findViewById(R.id.editText_atributos_AdicionalDois_nome);
        getPersonagem().setAtributo2Nome(String.valueOf(editAdicionalDoisNome.getText()));

        EditText editPvPerdido = inflate.findViewById(R.id.editText_valores_pv_perdido);
        getPersonagem().setPvPerdido(ferramentasComuns.editGetInteiroCorrigido(editPvPerdido));
        EditText editDeslocamento = inflate.findViewById(R.id.editText_valores_deslocamento);
        getPersonagem().setDeslocamento(ferramentasComuns.editGetFloatCorrigido(editDeslocamento));
    }

    public fragment_personagem_3del5_atributos() {
        // Required empty public constructor
    }


    public AdapterPersonagem3del5 getAdapter() {
        return adapter;
    }

    public void setAdapter(AdapterPersonagem3del5 adapter) {
        this.adapter = adapter;
    }


    public static void ativaBtnsPersonagem3del5Atributos(){
        ativado=true;
    }

}
