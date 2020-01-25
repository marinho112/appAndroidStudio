package com.example.a3dl_unificados.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a3dl_unificados.R;

import java.text.NumberFormat;
import java.util.Random;

import static com.example.a3dl_unificados.fragment.fragment_jogada_pericia_3del5.ativarBtn3del5PersonagemJogadaPericia;


public class fragment_tela_resultado_rolagem extends DialogFragment {


    private float chance;
    private View inflate;

    public fragment_tela_resultado_rolagem() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public fragment_tela_resultado_rolagem(float chance) {
        this.chance=chance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_tela_resultado_rolagem, container, false);
        definirTexto();
        verificarSucesso();
        return inflate;
    }

    private void definirTexto() {
        TextView txtChance = inflate.findViewById(R.id.fragment_tela_resultado_rolagem_txtChance);

        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(1);
        format.setMinimumFractionDigits(1);

        txtChance.setText("Chance de Sucesso: "+String.valueOf(format.format(chance))+"%");
    }

    public void verificarSucesso(){
        int randomico = getRandomico();


        TextView txtSucesso = inflate.findViewById(R.id.fragment_tela_resultado_rolagem_txtSucesso);

        if(chance>randomico){
            txtSucesso.setText("Sucesso !");
        }else{
            txtSucesso.setText("Fracasso !");
        }
    }

    private void definirValorImgDados(int d1,int d2) {
        TextView txtD1 = inflate.findViewById(R.id.fragment_tela_resultado_rolagem_txtPrimeiroDado);
        TextView txtD2 = inflate.findViewById(R.id.fragment_tela_resultado_rolagem_txtSegundoDado);
        txtD1.setText(String.valueOf(d1));
        txtD2.setText(String.valueOf(d2));

    }

    private int getRandomico() {
        Random gerador = new Random();
        int valor=gerador.nextInt();
        if(valor<0){valor*=-1;}
        int d1=valor%10;

        valor=gerador.nextInt();
        if(valor<0){valor*=-1;}
        int d2=valor%10;

        definirValorImgDados(d1,d2);
        if(d1+d2==0){
            return 100;
        }else{
            return ((d1 * 10) + d2);
        }
    }


    @Override
    public void onDestroyView() {
        ativarBtn3del5PersonagemJogadaPericia();
        super.onDestroyView();

    }
}
