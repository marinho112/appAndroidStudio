package com.example.a3dl_unificados;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a3dl_unificados.adapters.AdapterPersonagem3del5;
import com.example.a3dl_unificados.objetos.objeto_3del5_item;

import java.util.ArrayList;

import static com.example.a3dl_unificados.MainActivity.atualizaMainActivity;
import static com.example.a3dl_unificados.MainActivity.getMain;
import static com.example.a3dl_unificados.MainActivity.setPersonagem;

public class personagem_3del5 extends AppCompatActivity {

    private TabLayout tb;
    private  ViewPager vp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personagem_3del5);
        Toolbar toolbar = findViewById(R.id.toolbar_personagem_3del5);
        setSupportActionBar(toolbar);
        tb=findViewById(R.id.tabLayout_personagem_tela_principal);
        vp=findViewById(R.id.viewPager_personagem_tela_principal);
        vp.setAdapter(new AdapterPersonagem3del5(this,getSupportFragmentManager()));
        tb.setupWithViewPager(vp);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //setPersonagem(null);
        atualizaMainActivity();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return getMain().defineOnOpitionsItemSelected(item);
    }
   

}
