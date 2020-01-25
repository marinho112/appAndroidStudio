package com.example.a3dl_unificados.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a3dl_unificados.R;

import java.util.Random;

import static com.example.a3dl_unificados.fragment.fragment_jogada_dano_3del5.ativarBtn3del5PersonagemJogadaDano;


public class fragment_jogada_dano_resultado_3del5 extends DialogFragment {


    private int dano,multiplicador;
    public fragment_jogada_dano_resultado_3del5() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public fragment_jogada_dano_resultado_3del5(int dano, int multiplicador) {
        this.dano=dano;
        this.multiplicador=multiplicador;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_jogada_dano_resultado_3del5, container, false);

        rolarDano(inflate);

        return inflate;
    }

    private void rolarDano(View inflate) {
        Random gerador = new Random();
        int rolagem=gerador.nextInt(10);

        TextView text = inflate.findViewById(R.id.jogada_dano_resultado_3del5_txtDado);
        text.setText(String.valueOf(rolagem));
        int valorRol=((int)((1+rolagem)/2));
        int rolDano=multiplicador*valorRol;
        text = inflate.findViewById(R.id.jogada_dano_resultado_3del5_txtResultado);
        text.setText("Resultado: "+multiplicador+"*"+valorRol+"="+rolDano);
        int danoTotal=dano+rolDano;
        text = inflate.findViewById(R.id.jogada_dano_resultado_3del5_txtTotal);
        text.setText("Total: "+dano+"+"+rolDano+"="+danoTotal);
    }

    @Override
    public void onDestroyView() {
        ativarBtn3del5PersonagemJogadaDano();
        super.onDestroyView();

    }
}
