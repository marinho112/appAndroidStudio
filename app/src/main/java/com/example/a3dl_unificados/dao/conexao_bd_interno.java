package com.example.a3dl_unificados.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Switch;

import com.example.a3dl_unificados.objetos.objeto_3del5_pericia;

import java.util.ArrayList;

import static com.example.a3dl_unificados.ferramentas.ferramentasListaPericias.ordenaListaPericiasAtributo;
import static com.example.a3dl_unificados.ferramentas.ferramentasListaPericias.ordenaListaPericiasNome;
import static com.example.a3dl_unificados.ferramentas.ferramentasListaPericias.ordenaListaPericiasTipo;

public class conexao_bd_interno extends SQLiteOpenHelper {

    private static final String nomeBd="bdInterno.bd";
    private static final int versaoBd=7;
    private static int periciasPermanentes;

    public conexao_bd_interno(Context context) {
        super(context,nomeBd, null, versaoBd);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        criarTabelaPersonagem3del5(db);
        criarTabelaItem3del5(db);
        criarTabelaPericia3del5(db);
        criarTabelaPericiaPersonagem3del5(db);
        criarTabelaCaracteristica3del5(db);
        cadastrarPericiasPermanentes(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

        if(oldVersion <7){
            //adicionar atributo Adicional 1
            db.execSQL("ALTER TABLE personagem3del5 add COLUMN atributoUmPersonagem integer");
            db.execSQL("ALTER TABLE personagem3del5 add COLUMN atributoUmBonusPersonagem integer ");
            db.execSQL("ALTER TABLE personagem3del5 add COLUMN atributoUmNomePersonagem varchar(5) ");
            //adicionar atributo Adicional 2
            db.execSQL("ALTER TABLE personagem3del5 add COLUMN atributoDoisPersonagem integer ");
            db.execSQL("ALTER TABLE personagem3del5 add COLUMN atributoDoisBonusPersonagem integer ");
            db.execSQL("ALTER TABLE personagem3del5 add COLUMN atributoDoisNomePersonagem varchar(5) ");
            //adiciona imagem
            db.execSQL("ALTER TABLE personagem3del5 add COLUMN fotoPersonagem varchar(300)");
            //adiciona bonusde levantamento de peso
            db.execSQL("ALTER TABLE personagem3del5 add COLUMN levPesoBonus0Personagem integer");
            db.execSQL("ALTER TABLE personagem3del5 add COLUMN levPesoBonus1Personagem integer");
            db.execSQL("ALTER TABLE personagem3del5 add COLUMN levPesoBonus2Personagem integer");
            db.execSQL("ALTER TABLE personagem3del5 add COLUMN levPesoBonus3Personagem integer");
            //adiciona bonus de altura do salto
            db.execSQL("ALTER TABLE personagem3del5 add COLUMN altSaltoBonus0Personagem float");
            db.execSQL("ALTER TABLE personagem3del5 add COLUMN altSaltoBonus1Personagem float");
            db.execSQL("ALTER TABLE personagem3del5 add COLUMN altSaltoBonus2Personagem float");
            db.execSQL("ALTER TABLE personagem3del5 add COLUMN altSaltoBonus3Personagem float");
            // adiciona bonus de velocidade da corrida
            db.execSQL("ALTER TABLE personagem3del5 add COLUMN velCorridaBonus0Personagem integer");
            db.execSQL("ALTER TABLE personagem3del5 add COLUMN velCorridaBonus1Personagem integer");
            db.execSQL("ALTER TABLE personagem3del5 add COLUMN velCorridaBonus2Personagem integer");
            db.execSQL("ALTER TABLE personagem3del5 add COLUMN velCorridaBonus3Personagem integer");



        }

    }

    @Override
    public void onOpen(SQLiteDatabase db){

        definirListaPericiasPermanentes();

        try{
            criarTabelaPersonagem3del5(db);
        }catch (SQLException E){}
        try{
            criarTabelaItem3del5(db);
        }catch (SQLException E){}
        try{
            criarTabelaPericia3del5(db);
        }catch (SQLException E){}
        try{
            criarTabelaPericiaPersonagem3del5(db);
        }catch (SQLException E){}
        try{
            criarTabelaCaracteristica3del5(db);
        }catch (SQLException E){}
        Cursor cursor = db.query("pericia3del5", new String[]{"*"}, "idPericia = 1", null, null, null, null);
        if(cursor.getCount()==0){
            cadastrarPericiasPermanentes(db);
        }

    }

    private void criarTabelaItem3del5(SQLiteDatabase db) {
        db.execSQL("create table item3del5(" +
                "idItem integer primary key autoincrement," +
                "nomeItem varchar(40)," +
                "descricaoItem varchar(200)," +
                "PesoItem float," +
                "quantidadeItem integer," +
                "tipoItem integer," +
                "equipadoItem integer," +
                "idPersonagem integer" +
                ")");
    }

    private void criarTabelaPericia3del5(SQLiteDatabase db) {
        db.execSQL("create table pericia3del5(" +
                "idPericia integer primary key autoincrement," +
                "nomePericia varchar(40)," +
                "tipoPericia integer," +
                "AtributoPrincipal integer" +
                ")");
    }

    private void criarTabelaPericiaPersonagem3del5(SQLiteDatabase db)  {
        db.execSQL("create table periciaPersonagem3del5(" +
                "idPericia integer ," +
                "idPersonagem integer ," +
                "graduacao integer" +
                ")");
    }

    private void criarTabelaCaracteristica3del5(SQLiteDatabase db) {
        db.execSQL("create table caracteristica3del5(" +
                "idCaracteristica integer primary key autoincrement," +
                "nomeCaracteristica varchar(40)," +
                "descricaoCaracteristica varchar(1000)," +
                "custoCaracteristica integer," +
                "idPersonagem integer" +
                ")");
    }

    private void criarTabelaPersonagem3del5(SQLiteDatabase db) {
        db.execSQL("create table personagem3del5(" +
                "idPersonagem integer primary key autoincrement," +
                "nomePersonagem varchar(50)," +
                "racaPersonagem varchar(20)," +
                "funcaoPersonagem varchar(30)," +
                "tamanhoPersonagem varchar(10)," +
                "idadePersonagem integer," +
                "" +
                "forcaPersonagem integer," +
                "forcaBonusPersonagem integer," +
                "vitalidadePersonagem integer," +
                "vitalidadeBonusPersonagem integer," +
                "destrezaPersonagem integer," +
                "destrezaBonusPersonagem integer," +
                "agilidadePersonagem integer," +
                "agilidadeBonusPersonagem integer," +
                "sentidosPersonagem integer," +
                "sentidosBonusPersonagem integer," +
                "inteligenciaPersonagem integer," +
                "inteligenciaBonusPersonagem integer," +
                "" +
                "pvPerdidoPersonagem integer," +
                "deslocamentoPersonagem float," +
                "" +
                "danoBonus0Personagem integer," +
                "danoBonus1Personagem integer," +
                "danoBonus2Personagem integer," +
                "danoBonus3Personagem integer," +
                "" +
                "reducaoDanoBonus0Personagem integer," +
                "reducaoDanoBonus1Personagem integer," +
                "reducaoDanoBonus2Personagem integer," +
                "reducaoDanoBonus3Personagem integer," +
                "" +
                "acertoBonus0Personagem integer," +
                "acertoBonus1Personagem integer," +
                "acertoBonus2Personagem integer," +
                "acertoBonus3Personagem integer," +
                "" +
                "reflexoBonus0Personagem integer," +
                "reflexoBonus1Personagem integer," +
                "reflexoBonus2Personagem integer," +
                "reflexoBonus3Personagem integer," +
                "" +
                "resistenciaBonus0Personagem integer," +
                "resistenciaBonus1Personagem integer," +
                "resistenciaBonus2Personagem integer," +
                "resistenciaBonus3Personagem integer," +
                "" +
                "vontadeBonus0Personagem integer," +
                "vontadeBonus1Personagem integer," +
                "vontadeBonus2Personagem integer," +
                "vontadeBonus3Personagem integer," +
                "" +
                "percepcaoBonus0Personagem integer," +
                "percepcaoBonus1Personagem integer," +
                "percepcaoBonus2Personagem integer," +
                "percepcaoBonus3Personagem integer," +
                "" +
                "multiplicadorBonus0Personagem integer," +
                "multiplicadorBonus1Personagem integer," +
                "multiplicadorBonus2Personagem integer," +
                "multiplicadorBonus3Personagem integer," +
                "" +
                "acoesBonus0Personagem integer," +
                "acoesBonus1Personagem integer," +
                "acoesBonus2Personagem integer," +
                "acoesBonus3Personagem integer," +
                "" +
                "pvBonus0Personagem integer," +
                "pvBonus1Personagem integer," +
                "pvBonus2Personagem integer," +
                "pvBonus3Personagem integer," +
                "" +
                "atributoUmPersonagem integer," +
                "atributoUmBonusPersonagem integer," +
                "atributoUmNomePersonagem varchar(5)," +
                "" +
                "atributoDoisPersonagem integer," +
                "atributoDoisBonusPersonagem integer," +
                "atributoDoisNomePersonagem varchar(5)," +

                "fotoPersonagem varchar(300)," +
                "" +
                "levPesoBonus0Personagem integer," +
                "levPesoBonus1Personagem integer," +
                "levPesoBonus2Personagem integer," +
                "levPesoBonus3Personagem integer," +
                "" +
                "altSaltoBonus0Personagem float," +
                "altSaltoBonus1Personagem float," +
                "altSaltoBonus2Personagem float," +
                "altSaltoBonus3Personagem float," +
                "" +
                "velCorridaBonus0Personagem integer," +
                "velCorridaBonus1Personagem integer," +
                "velCorridaBonus2Personagem integer," +
                "velCorridaBonus3Personagem integer" +
                ")");
    }

    private void cadastrarPericiasPermanentes(SQLiteDatabase db){
        ArrayList<objeto_3del5_pericia> listaPericias = definirListaPericiasPermanentes();

        for(int i=0;i<listaPericias.size();i++){
            ContentValues values = new ContentValues();
            values.put("idPericia",i+1);
            values.put("nomePericia",listaPericias.get(i).getNome());
            values.put("tipoPericia",listaPericias.get(i).getTipo());
            values.put("AtributoPrincipal",listaPericias.get(i).getAtributoPrincipal());
            String tabela="pericia3del5";
            db.insert(tabela, null, values);

        }

    }

    private void testePericiaPersonagem(SQLiteDatabase db){

        db.delete("periciaPersonagem3del5",null,null);

        //TESTE
        ContentValues contentValues = new ContentValues();
        contentValues.put("idPersonagem", 16);

        contentValues.put("graduacao",5);
        for(int i=1;i<50;i+=5) {
            contentValues.put("idPericia",i);
            db.insert("periciaPersonagem3del5", null, contentValues);
        }
    }

    private ArrayList<objeto_3del5_pericia> definirListaPericiasPermanentes(){
        ArrayList<objeto_3del5_pericia> listaPericias=new ArrayList<>();
        listaPericias.add(new objeto_3del5_pericia("Agarrar",0,0));
        listaPericias.add(new objeto_3del5_pericia("Destruir",0,0));
        listaPericias.add(new objeto_3del5_pericia("Levantar Peso",0,0));
        listaPericias.add(new objeto_3del5_pericia("Resistir Veneno",0,1));
        listaPericias.add(new objeto_3del5_pericia("Tenacidade (Desmaiar)",0,1));
        listaPericias.add(new objeto_3del5_pericia("Tolerancia a Dor",0,1));
        listaPericias.add(new objeto_3del5_pericia("Vitalidade (Morrer)",0,1));
        listaPericias.add(new objeto_3del5_pericia("Arco e Flecha",0,2));
        listaPericias.add(new objeto_3del5_pericia("Arma de Fogo",0,2));
        listaPericias.add(new objeto_3del5_pericia("Armas de Peso",0,2));
        listaPericias.add(new objeto_3del5_pericia("Ataque Desarmado",0,2));
        listaPericias.add(new objeto_3del5_pericia("Lâminas Longas",0,2));
        listaPericias.add(new objeto_3del5_pericia("Lanças",0,2));
        listaPericias.add(new objeto_3del5_pericia("Acrobacia",0,3));
        listaPericias.add(new objeto_3del5_pericia("Correr",0,3));
        listaPericias.add(new objeto_3del5_pericia("Escalar",0,3));
        listaPericias.add(new objeto_3del5_pericia("Furtividade",0,3));
        listaPericias.add(new objeto_3del5_pericia("Nadar",0,3));
        listaPericias.add(new objeto_3del5_pericia("Saltar",0,3));
        listaPericias.add(new objeto_3del5_pericia("Bloquear",0,4));
        listaPericias.add(new objeto_3del5_pericia("Esquivar",0,4));
        listaPericias.add(new objeto_3del5_pericia("Farejar",0,4));
        listaPericias.add(new objeto_3del5_pericia("Mirar",0,4));
        listaPericias.add(new objeto_3del5_pericia("Ouvir",0,4));
        listaPericias.add(new objeto_3del5_pericia("Percepção",0,4));
        listaPericias.add(new objeto_3del5_pericia("Vontade",0,5));
        listaPericias.add(new objeto_3del5_pericia("Alquimia",1,5));
        listaPericias.add(new objeto_3del5_pericia("Armadilha",1,5));
        listaPericias.add(new objeto_3del5_pericia("Blefe",1,5));
        listaPericias.add(new objeto_3del5_pericia("Biologia",1,5));
        listaPericias.add(new objeto_3del5_pericia("Camuflagem",1,5));
        listaPericias.add(new objeto_3del5_pericia("Computação",1,5));
        listaPericias.add(new objeto_3del5_pericia("Culinária",1,5));
        listaPericias.add(new objeto_3del5_pericia("Detectar Magia",1,5));
        listaPericias.add(new objeto_3del5_pericia("Detectar Mentiras",1,5));
        listaPericias.add(new objeto_3del5_pericia("Diplomacia",1,5));
        listaPericias.add(new objeto_3del5_pericia("Disfarce",1,5));
        listaPericias.add(new objeto_3del5_pericia("Falsificação",1,5));
        listaPericias.add(new objeto_3del5_pericia("Física",1,5));
        listaPericias.add(new objeto_3del5_pericia("História",1,5));
        listaPericias.add(new objeto_3del5_pericia("Lábia",1,5));
        listaPericias.add(new objeto_3del5_pericia("Música",1,5));
        listaPericias.add(new objeto_3del5_pericia("Navegação",1,5));
        listaPericias.add(new objeto_3del5_pericia("Ocultismo",1,5));
        listaPericias.add(new objeto_3del5_pericia("Rastrear",1,5));
        listaPericias.add(new objeto_3del5_pericia("Química",1,5));
        listaPericias.add(new objeto_3del5_pericia("Robótica",1,5));
        listaPericias.add(new objeto_3del5_pericia("Sobrevivncia",1,5));
        listaPericias.add(new objeto_3del5_pericia("Veneno",1,5));

        organizaLista(listaPericias);

        periciasPermanentes=listaPericias.size();

        return listaPericias;
    }

    private void organizaLista(ArrayList<objeto_3del5_pericia> lista){

        ordenaListaPericiasNome(lista);
        ordenaListaPericiasAtributo(lista);
        ordenaListaPericiasTipo(lista);

    }

    public static int getPericiasPermanentes() {
        return periciasPermanentes;
    }

}
