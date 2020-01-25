package com.example.a3dl_unificados.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a3dl_unificados.MainActivity;
import com.example.a3dl_unificados.fragment.fragment_personagem_3del5_atributos;
import com.example.a3dl_unificados.fragment.fragment_personagem_3del5_info_personagem;
import com.example.a3dl_unificados.fragment.fragment_personagem_3del5_lista_caracteristicas;
import com.example.a3dl_unificados.fragment.fragment_personagem_3del5_lista_itens;
import com.example.a3dl_unificados.fragment.fragment_personagem_3del5_lista_pericias;
import com.example.a3dl_unificados.objetos.objeto_3del5_item;
import com.example.a3dl_unificados.objetos.objeto_3del5_personagem;

import java.util.List;

import static com.example.a3dl_unificados.MainActivity.getPersonagem;

public class AdapterPersonagem3del5 extends FragmentPagerAdapter {

    private static int NUM_iTEMS = 5;
    private Context context;
    fragment_personagem_3del5_info_personagem frag0 = new fragment_personagem_3del5_info_personagem();
    fragment_personagem_3del5_atributos frag1 = new fragment_personagem_3del5_atributos();
    fragment_personagem_3del5_lista_itens frag2 = new fragment_personagem_3del5_lista_itens();
    fragment_personagem_3del5_lista_pericias frag3 = new fragment_personagem_3del5_lista_pericias(frag1);
    fragment_personagem_3del5_lista_caracteristicas frag4 = new fragment_personagem_3del5_lista_caracteristicas();

    public AdapterPersonagem3del5(Context contextt, FragmentManager fragmentManager) {
        super(fragmentManager);
        context = contextt;
    }


    @Override
    public int getCount() {
        return NUM_iTEMS;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                frag0.setContext(context);
                frag0.setAdapter(this);
                return frag0;
            case 1:
                frag1.setAdapter(this);
                return frag1;
            case 2:
                frag2.setAdapter(this);
                return frag2;
            case 3:
                frag3.setAdapter(this);
                return frag3;
            case 4:
                frag4.setAdapter(this);
                return frag4;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Info.";
            case 1:
                return "Atri.";
            case 2:
                return "Itens";
            case 3:
                return "Peric.";
            case 4:
                return "Carac.";
            default:
                return null;
        }
    }
}