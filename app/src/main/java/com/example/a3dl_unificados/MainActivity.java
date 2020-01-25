package com.example.a3dl_unificados;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.a3dl_unificados.adapters.AdapterListaPersonagens;
import com.example.a3dl_unificados.dao.bdInternoDAO;
import com.example.a3dl_unificados.objetos.objeto_3del5_personagem;

import java.util.ArrayList;
import java.util.List;

import static com.example.a3dl_unificados.dao.bdInternoDAO.recuperaTodosOsPersonagens;

public class MainActivity extends AppCompatActivity {

    private static List<objeto_3del5_personagem> personagens3del5=new ArrayList<>();
    private static objeto_3del5_personagem personagem;
    private bdInternoDAO bd;
    public static AdapterListaPersonagens adapterListaPersonagens;
    private Intent intent;
    private static MainActivity main;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd=new bdInternoDAO(this);
        setContentView(R.layout.activity_main);

        setMain(this);

        Toolbar toolbar = findViewById(R.id.toolbar_MainActivity);
        setSupportActionBar(toolbar);

        intent=new Intent(this,personagem_3del5.class);
        personagens3del5= recuperaTodosOsPersonagens();
        definirAdapterListView();


        defineFab(intent);



    }

    private void definirAdapterListView() {
        ListView listView = findViewById(R.id.listView_lista_personagens);
        adapterListaPersonagens= new AdapterListaPersonagens( this);
        listView.setAdapter(adapterListaPersonagens);

    }


    private void defineFab(final Intent intent) {
        FloatingActionButton fab = findViewById(R.id.fab_lista_personagens);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                iniciarActivity(intent);
            }
        });
    }

    public void iniciarActivity(Intent intent) {
        setPersonagem(new objeto_3del5_personagem());
        startActivityForResult(intent,1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return defineOnOpitionsItemSelected(item);
    }

    public boolean defineOnOpitionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){

            case R.id.menu_opcao_personagens:
                definirMenuOpçãoPersonagem();
                break;
            case R.id.menu_opcao_regras:
                Intent nIntent=new Intent(this, tela_de_regras.class);
                startActivityForResult(nIntent,2);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void definirMenuOpçãoPersonagem() {
        if(getPersonagem().equals(null)){
            /// não sei oq fazer ainda
        }else{
            startActivityForResult(intent,1);
        }
        return;
    }




    public static List<objeto_3del5_personagem> getListaPersonagens3del5() {
        personagens3del5=bdInternoDAO.recuperaTodosOsPersonagens();
        return personagens3del5;
    }

    public static void atualizaMainActivity(){
        personagens3del5=recuperaTodosOsPersonagens();
        adapterListaPersonagens.setLista(personagens3del5);
        adapterListaPersonagens.notifyDataSetChanged();
    }

    public static objeto_3del5_personagem getPersonagem() {
        return personagem;
    }

    public static void setPersonagem(objeto_3del5_personagem personagem) {
        MainActivity.personagem = personagem;
    }

    public static MainActivity getMain() {
        return main;
    }

    public static void setMain(MainActivity main) {
        MainActivity.main = main;
    }


}
