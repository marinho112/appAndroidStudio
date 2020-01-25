package com.example.a3dl_unificados.dao;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a3dl_unificados.ferramentas.ferramentasListaPericias;
import com.example.a3dl_unificados.objetos.objeto_3del5_caracteristica;
import com.example.a3dl_unificados.objetos.objeto_3del5_item;
import com.example.a3dl_unificados.objetos.objeto_3del5_pericia;
import com.example.a3dl_unificados.objetos.objeto_3del5_periciaPersonagem;
import com.example.a3dl_unificados.objetos.objeto_3del5_personagem;

import java.util.ArrayList;
import java.util.List;

public class bdInternoDAO {

    private static conexao_bd_interno conecxao;
    private static SQLiteDatabase banco;
    private Context context;

    public bdInternoDAO(Context context) {

        conecxao=new conexao_bd_interno(context);
        banco= getConecxao().getWritableDatabase();
        this.context=context;
    }

    //Inserts

    public static long inserirPersonagem3del5(objeto_3del5_personagem personagem){

        ContentValues values = defineValuesPersonagem3del5(personagem);
        String tabela="personagem3del5";
        String[] strg=new String[]{"idPersonagem"};
        String condicao=personagem.getCodPersonagem()+"=idPersonagem";
        Cursor querryPersonagem3del5 = banco.query(tabela, strg,condicao , null, null, null, null);
        if(querryPersonagem3del5.getCount()==0) {
            personagem.setCodPersonagem((int) banco.insert(tabela, null, values));
        }else{
            banco.update(tabela,values,condicao,null);

        }
        return personagem.getCodPersonagem();
    }

    public static long inserirItem3del5(objeto_3del5_item item){

        ContentValues values = defineValuesItem3del5(item);
        String tabela="item3del5";
        String[] strg=new String[]{"idItem"};
        String condicao=item.getIdItem()+"=idItem";
        Cursor querryItem3del5 = banco.query(tabela, strg,condicao , null, null, null, null);
        if(querryItem3del5.getCount()==0) {
            item.setIdItem((int) banco.insert(tabela, null, values));
        }else{
            banco.update(tabela,values,condicao,null);

        }
        return item.getIdItem();
    }

    public static long inserirPericia3del5(objeto_3del5_pericia pericia){

        ContentValues values = defineValuesPericia3del5(pericia);
        String tabela="pericia3del5";
        String[] strg=new String[]{"*"};
        String condicao=pericia.getIdPericia()+"=idPericia";
        Cursor querryPericia3del5 = banco.query(tabela, strg,condicao , null, null, null, null);
        if(querryPericia3del5.getCount()==0) {
            pericia.setIdPericia((int) banco.insert(tabela, null, values));
        }else{
            banco.update(tabela,values,condicao,null);

        }
        return pericia.getIdPericia();
    }

    public static Boolean inserirPericiaPersonagem3del5(objeto_3del5_periciaPersonagem periciaPersonagem){

        ContentValues values = defineValuesPericiaPersonagem3del5(periciaPersonagem);
        String tabela="periciaPersonagem3del5";
        String[] strg=new String[]{"*"};
        String condicao=periciaPersonagem.getIdPericia()+"=idPericia and idPersonagem="+periciaPersonagem.getIdPersonagem();
        Cursor querryPericia3del5 = banco.query(tabela, strg,condicao , null, null, null, null);
        if(querryPericia3del5.getCount()==0) {
            banco.insert(tabela, null, values);
        }else{
            banco.update(tabela,values,condicao,null);

        }
        return true;
    }

    public static Boolean inserirPericiaPersonagem3del5(objeto_3del5_pericia pericia,objeto_3del5_personagem personagem,int grad){

        ContentValues values = defineValuesPericiaPersonagem3del5(pericia,personagem,grad);
        String tabela="periciaPersonagem3del5";
        String[] strg=new String[]{"*"};
        String condicao=pericia.getIdPericia()+"=idPericia and idPersonagem="+personagem.getCodPersonagem();
        Cursor querryPericia3del5 = banco.query(tabela, strg,condicao , null, null, null, null);
        if(querryPericia3del5.getCount()==0) {
            banco.insert(tabela, null, values);
        }else{
            banco.update(tabela,values,condicao,null);

        }
        return true;
    }

    public static long inserirCaracteristica3del5(objeto_3del5_caracteristica caracteristica){

        ContentValues values = defineValuesCaracteristica3del5(caracteristica);
        String tabela="caracteristica3del5";
        String[] strg=new String[]{"idCaracteristica"};
        String condicao=caracteristica.getIdCaracteristica()+"=idCaracteristica";
        Cursor querryPericia3del5 = banco.query(tabela, strg,condicao , null, null, null, null);
        if(querryPericia3del5.getCount()==0) {
            caracteristica.setIdCaracteristica((int) banco.insert(tabela, null, values));
        }else{
            banco.update(tabela,values,condicao,null);

        }
        return caracteristica.getIdCaracteristica();
    }

    //Exclude

    public static void excluirItem3del5(objeto_3del5_item item){
            String tabela="item3del5";
            String condicao=item.getIdItem()+"=idItem";
            banco.delete(tabela,condicao,null);
        }

    public static boolean excluirPericia3del5(objeto_3del5_pericia pericia){
        if(pericia.getIdPericia()>conexao_bd_interno.getPericiasPermanentes()){
            String tabela="pericia3del5";
            String condicao=pericia.getIdPericia()+"=idPericia";
            banco.delete(tabela,condicao,null);

            tabela="periciaPersonagem3del5";
            banco.delete(tabela,condicao,null);

            return true;
        }
        else{
            return false;
        }

    }

    public static void excluirPericiaPersonagem3del5(objeto_3del5_pericia pericia,objeto_3del5_personagem personagem){
        String tabela="periciaPersonagem3del5";
        String condicao=pericia.getIdPericia()+"=idPericia and idPersonagem="+personagem.getCodPersonagem();
        banco.delete(tabela,condicao,null);
    }

    public static void excluirPericiaPersonagem3del5(objeto_3del5_periciaPersonagem periciaPersonagem){
        String tabela="periciaPersonagem3del5";
        String condicao=periciaPersonagem.getIdPericia()+"=idPericia and idPersonagem="+periciaPersonagem.getIdPersonagem();
        banco.delete(tabela,condicao,null);
    }

    public static void excluirCaracteristica3del5(objeto_3del5_caracteristica caracteristica){
        String tabela="caracteristica3del5";
        String condicao=caracteristica.getIdCaracteristica()+"=idCaracteristica";
        banco.delete(tabela,condicao,null);
    }

    public static void excluirPersonagem3del5(objeto_3del5_personagem personagem){
        String tabela="personagem3del5";
        String condicao=personagem.getCodPersonagem()+"=idPersonagem";
        banco.delete(tabela,condicao,null);

        tabela="item3del5";
        banco.delete(tabela,condicao,null);

        tabela="periciaPersonagem3del5";
        banco.delete(tabela,condicao,null);

        tabela="caracteristica3del5";
        banco.delete(tabela,condicao,null);

    }

    //definir Values


    private static ContentValues defineValuesPersonagem3del5(objeto_3del5_personagem personagem) {
        ContentValues values = new ContentValues();
        //Dados
        values.put("nomePersonagem",personagem.getNome());
        values.put("racaPersonagem",personagem.getRaca());
        values.put("funcaoPersonagem",personagem.getFuncao());
        values.put("idadePersonagem",personagem.getIdade());
        values.put("tamanhoPersonagem",personagem.getClasseDeTamanho());
        //Atributos
        values.put("forcaPersonagem",personagem.getForca());
        values.put("forcaBonusPersonagem",personagem.getForcaBonus());
        values.put("vitalidadePersonagem",personagem.getVitalidade());
        values.put("vitalidadeBonusPersonagem",personagem.getVitalidadeBonus());
        values.put("destrezaPersonagem",personagem.getDestreza());
        values.put("destrezaBonusPersonagem",personagem.getDestrezaBonus());
        values.put("agilidadePersonagem",personagem.getAgilidade());
        values.put("agilidadeBonusPersonagem",personagem.getAgilidadeBonus());
        values.put("sentidosPersonagem",personagem.getSentidos());
        values.put("sentidosBonusPersonagem",personagem.getSentidosBonus());
        values.put("inteligenciaPersonagem",personagem.getInteligengia());
        values.put("inteligenciaBonusPersonagem",personagem.getInteligengiaBonus());
        //Atributos compostos
        values.put("pvPerdidoPersonagem",personagem.getPvPerdido());
        values.put("deslocamentoPersonagem",personagem.getDeslocamento());
        //dano
        values.put("danoBonus0Personagem",personagem.getDanoBonus()[0]);
        values.put("danoBonus1Personagem",personagem.getDanoBonus()[1]);
        values.put("danoBonus2Personagem",personagem.getDanoBonus()[2]);
        values.put("danoBonus3Personagem",personagem.getDanoBonus()[3]);
        //Redução de Dano
        values.put("reducaoDanoBonus0Personagem",personagem.getReducaoDanoBonus()[0]);
        values.put("reducaoDanoBonus1Personagem",personagem.getReducaoDanoBonus()[1]);
        values.put("reducaoDanoBonus2Personagem",personagem.getReducaoDanoBonus()[2]);
        values.put("reducaoDanoBonus3Personagem",personagem.getReducaoDanoBonus()[3]);
        //Acerto
        values.put("acertoBonus0Personagem",personagem.getAcertoBonus()[0]);
        values.put("acertoBonus1Personagem",personagem.getAcertoBonus()[1]);
        values.put("acertoBonus2Personagem",personagem.getAcertoBonus()[2]);
        values.put("acertoBonus3Personagem",personagem.getAcertoBonus()[3]);
        //Reflexo
        values.put("reflexoBonus0Personagem",personagem.getReflexoBonus()[0]);
        values.put("reflexoBonus1Personagem",personagem.getReflexoBonus()[1]);
        values.put("reflexoBonus2Personagem",personagem.getReflexoBonus()[2]);
        values.put("reflexoBonus3Personagem",personagem.getReflexoBonus()[3]);
        //Resistencia
        values.put("resistenciaBonus0Personagem",personagem.getResistenciaBonus()[0]);
        values.put("resistenciaBonus1Personagem",personagem.getResistenciaBonus()[1]);
        values.put("resistenciaBonus2Personagem",personagem.getResistenciaBonus()[2]);
        values.put("resistenciaBonus3Personagem",personagem.getResistenciaBonus()[3]);
        //Vontade
        values.put("vontadeBonus0Personagem",personagem.getVontadeBonus()[0]);
        values.put("vontadeBonus1Personagem",personagem.getVontadeBonus()[1]);
        values.put("vontadeBonus2Personagem",personagem.getVontadeBonus()[2]);
        values.put("vontadeBonus3Personagem",personagem.getVontadeBonus()[3]);
        //Percepção
        values.put("percepcaoBonus0Personagem",personagem.getPercepcaoBonus()[0]);
        values.put("percepcaoBonus1Personagem",personagem.getPercepcaoBonus()[1]);
        values.put("percepcaoBonus2Personagem",personagem.getPercepcaoBonus()[2]);
        values.put("percepcaoBonus3Personagem",personagem.getPercepcaoBonus()[3]);
        //Multiplicador
        values.put("multiplicadorBonus0Personagem",personagem.getMultiplicadorBonus()[0]);
        values.put("multiplicadorBonus1Personagem",personagem.getMultiplicadorBonus()[1]);
        values.put("multiplicadorBonus2Personagem",personagem.getMultiplicadorBonus()[2]);
        values.put("multiplicadorBonus3Personagem",personagem.getMultiplicadorBonus()[3]);
        //Ações
        values.put("acoesBonus0Personagem",personagem.getAcoesBonus()[0]);
        values.put("acoesBonus1Personagem",personagem.getAcoesBonus()[1]);
        values.put("acoesBonus2Personagem",personagem.getAcoesBonus()[2]);
        values.put("acoesBonus3Personagem",personagem.getAcoesBonus()[3]);
        //Pontos de Vida
        values.put("pvBonus0Personagem",personagem.getPvBonus()[0]);
        values.put("pvBonus1Personagem",personagem.getPvBonus()[1]);
        values.put("pvBonus2Personagem",personagem.getPvBonus()[2]);
        values.put("pvBonus3Personagem",personagem.getPvBonus()[3]);
        //atributo Um
        values.put("atributoUmPersonagem",personagem.getAtributo1());
        values.put("atributoUmBonusPersonagem",personagem.getAtributo1Bonus());
        values.put("atributoUmNomePersonagem",personagem.getAtributo1Nome());
        //atributo 2
        values.put("atributoDoisPersonagem",personagem.getAtributo2());
        values.put("atributoDoisBonusPersonagem",personagem.getAtributo2Bonus());
        values.put("atributoDoisNomePersonagem",personagem.getAtributo2Nome());
        //imagem
        values.put("fotoPersonagem",personagem.getImagem());
        //Levantamento de Peso
        values.put("levPesoBonus0Personagem",personagem.getLevPesoBonus()[0]);
        values.put("levPesoBonus1Personagem",personagem.getLevPesoBonus()[1]);
        values.put("levPesoBonus2Personagem",personagem.getLevPesoBonus()[2]);
        values.put("levPesoBonus3Personagem",personagem.getLevPesoBonus()[3]);
        //altura Salto
        values.put("altSaltoBonus0Personagem",personagem.getAltSaltoBonus()[0]);
        values.put("altSaltoBonus1Personagem",personagem.getAltSaltoBonus()[1]);
        values.put("altSaltoBonus2Personagem",personagem.getAltSaltoBonus()[2]);
        values.put("altSaltoBonus3Personagem",personagem.getAltSaltoBonus()[3]);
        //Velocidade Corrida
        values.put("velCorridaBonus0Personagem",personagem.getVelCorridaBonus()[0]);
        values.put("velCorridaBonus1Personagem",personagem.getVelCorridaBonus()[1]);
        values.put("velCorridaBonus2Personagem",personagem.getVelCorridaBonus()[2]);
        values.put("velCorridaBonus3Personagem",personagem.getVelCorridaBonus()[3]);

        return values;
    }

    private static ContentValues defineValuesItem3del5(objeto_3del5_item item) {
        ContentValues values = new ContentValues();
        values.put("nomeItem",item.getNome());
        values.put("descricaoItem",item.getDescricao());
        values.put("pesoItem",item.getPeso());
        values.put("quantidadeItem",item.getQuantidade());
        values.put("tipoItem",item.getTipo());
        int equipado=0;
        if(item.isEquipado()==true){equipado=1;}
        values.put("equipadoItem",equipado);
        values.put("idPersonagem",item.getIdPersonagem());

        return values;
    }

    private static ContentValues defineValuesPericia3del5(objeto_3del5_pericia pericia) {
        ContentValues values = new ContentValues();
        values.put("nomePericia",pericia.getNome());
        values.put("tipoPericia",pericia.getTipo());
        values.put("AtributoPrincipal",pericia.getAtributoPrincipal());

        return values;
    }

    private static ContentValues defineValuesPericiaPersonagem3del5(objeto_3del5_periciaPersonagem periciaPersonagem) {
        ContentValues values = new ContentValues();
        values.put("idPericia",periciaPersonagem.getIdPericia());
        values.put("idPersonagem",periciaPersonagem.getIdPersonagem());
        values.put("graduacao",periciaPersonagem.getGraduacao());

        return values;
    }

    private static ContentValues defineValuesPericiaPersonagem3del5(objeto_3del5_pericia pericia,objeto_3del5_personagem personagem,int grad) {
        ContentValues values = new ContentValues();
        values.put("idPericia",pericia.getIdPericia());
        values.put("idPersonagem",personagem.getCodPersonagem());
        values.put("graduacao",grad);

        return values;
    }

    private static ContentValues defineValuesCaracteristica3del5(objeto_3del5_caracteristica caracteristica) {
        ContentValues values = new ContentValues();
        values.put("nomeCaracteristica",caracteristica.getNomeCaracteristica());
        values.put("descricaoCaracteristica",caracteristica.getDescricaoCaracteristica());
        values.put("custoCaracteristica",caracteristica.getCustoCaracteristica());
        values.put("idPersonagem",caracteristica.getIdPersonagem());

        return values;
    }

    //recuperar Listas

    public static List<objeto_3del5_personagem> recuperaTodosOsPersonagens(){
        String tabela="personagem3del5";
        String[] strg=new String[]{"*"};
        Cursor querryPersonagem3del5 = banco.query(tabela, strg,null , null, null, null, null);
        List<objeto_3del5_personagem> retorno= cursorToArrayPersonagem(querryPersonagem3del5);
        return retorno;
    }

    public static List<objeto_3del5_item> recuperaTodosOsItens(objeto_3del5_personagem personagem){
        String tabela="item3del5";
        String[] strg=new String[]{"*"};
        String where="idPersonagem ="+personagem.getCodPersonagem();
        Cursor querryItem3del5 = banco.query(tabela, strg,where, null, null, null, null);
        List<objeto_3del5_item> retorno= cursorToArrayItem(querryItem3del5);
        return retorno;
    }

    public static List<objeto_3del5_pericia> recuperaTodasAsPericias(){
        String tabela="pericia3del5";
        String[] strg=new String[]{"*"};
        String where="";
        Cursor querryPericia3del5 = banco.query(tabela, strg,where, null, null, null, null);
        List<objeto_3del5_pericia> retorno= cursorToArrayPericia(querryPericia3del5);
        return retorno;
    }

    public static List<objeto_3del5_pericia> recuperaTodasAsPericiasTipo(int tipo){
        String tabela="pericia3del5";
        String[] strg=new String[]{"*"};
        String where="tipoPericia="+tipo;
        Cursor querryPericia3del5 = banco.query(tabela, strg,where, null, null, null, null);
        List<objeto_3del5_pericia> retorno= cursorToArrayPericia(querryPericia3del5);
        return retorno;
    }

    public static List<objeto_3del5_pericia> recuperaPericiasPorNome(String nome){
        String tabela="pericia3del5";
        String[] strg=new String[]{"*"};
        String where="nomePericia="+nome;
        Cursor querryPericia3del5 = banco.query(tabela, strg,where, null, null, null, null);
        List<objeto_3del5_pericia> retorno= cursorToArrayPericia(querryPericia3del5);
        return retorno;
    }

    public static List<objeto_3del5_periciaPersonagem> recuperaPericiaPersonagemporPericia(objeto_3del5_pericia pericia){
        String tabela="periciaPersonagem3del5";
        String[] strg=new String[]{"*"};
        String where="idPericia ="+pericia.getIdPericia();
        Cursor querryPericiaPersonagem3del5 = banco.query(tabela, strg,where, null, null, null, null);
        List<objeto_3del5_periciaPersonagem> retorno= cursorToArrayPericiaPersonagem(querryPericiaPersonagem3del5);
        return retorno;
    }

    public static List<objeto_3del5_periciaPersonagem> recuperaPericiaPersonagemporPersonagem(objeto_3del5_personagem personagem){
        String tabela="periciaPersonagem3del5";
        String[] strg=new String[]{"*"};
        String where="idPersonagem ="+personagem.getCodPersonagem();
        Cursor querryPericiaPersonagem3del5 = banco.query(tabela, strg,where, null, null, null, null);
        List<objeto_3del5_periciaPersonagem> retorno=cursorToArrayPericiaPersonagem(querryPericiaPersonagem3del5);
        return retorno;
    }

    public static List<objeto_3del5_periciaPersonagem> recuperaPericiaPersonagem(objeto_3del5_personagem personagem,objeto_3del5_pericia pericia){
        String tabela="periciaPersonagem3del5";
        String[] strg=new String[]{"*"};
        String where="idPersonagem ="+personagem.getCodPersonagem()+" and idPericia="+pericia.getIdPericia();
        Cursor querryPericiaPersonagem3del5 = banco.query(tabela, strg,where, null, null, null, null);
        List<objeto_3del5_periciaPersonagem> retorno=cursorToArrayPericiaPersonagem(querryPericiaPersonagem3del5);
        return retorno;
    }


    public static List<objeto_3del5_caracteristica> recuperaTodasAsCaracteristicas(objeto_3del5_personagem personagem){
        String tabela="caracteristica3del5";
        String[] strg=new String[]{"*"};
        String where="idPersonagem ="+personagem.getCodPersonagem();
        Cursor querryCaracteristica3del5 = banco.query(tabela, strg,where, null, null, null, null);
        List<objeto_3del5_caracteristica> retorno= cursorToArrayCaracteristica(querryCaracteristica3del5);
        return retorno;
    }

    //Conversores de cursor pra List

    private static List<objeto_3del5_personagem> cursorToArrayPersonagem(Cursor querryPersonagem3del5) {
        ArrayList<objeto_3del5_personagem> retornar = new ArrayList<>();
        querryPersonagem3del5.moveToNext();
        for (int i=0;i<querryPersonagem3del5.getCount();i++){
            objeto_3del5_personagem personagem=new objeto_3del5_personagem();
            personagem.setCodPersonagem(querryPersonagem3del5.getInt(0));
            personagem.setNome(querryPersonagem3del5.getString(1));
            personagem.setRaca(querryPersonagem3del5.getString(2));
            personagem.setFuncao(querryPersonagem3del5.getString(3));
            personagem.setClasseDeTamanho(querryPersonagem3del5.getString(4));
            personagem.setIdade(querryPersonagem3del5.getInt(5));
            //atributos
            personagem.setForca(querryPersonagem3del5.getInt(6));
            personagem.setForcaBonus(querryPersonagem3del5.getInt(7));
            personagem.setVitalidade(querryPersonagem3del5.getInt(8));
            personagem.setVitalidadeBonus(querryPersonagem3del5.getInt(9));
            personagem.setDestreza(querryPersonagem3del5.getInt(10));
            personagem.setDestrezaBonus(querryPersonagem3del5.getInt(11));
            personagem.setAgilidade(querryPersonagem3del5.getInt(12));
            personagem.setAgilidadeBonus(querryPersonagem3del5.getInt(13));
            personagem.setSentidos(querryPersonagem3del5.getInt(14));
            personagem.setSentidosBonus(querryPersonagem3del5.getInt(15));
            personagem.setInteligengia(querryPersonagem3del5.getInt(16));
            personagem.setInteligengiaBonus(querryPersonagem3del5.getInt(17));
            //atributos calculados
            personagem.setPvPerdido(querryPersonagem3del5.getInt(18));
            personagem.setDeslocamento(querryPersonagem3del5.getFloat(19));
            int[] valores=new int[4];
            float[] valores2=new float[4];
            //dano
            valores[0]=querryPersonagem3del5.getInt(20);
            valores[1]=querryPersonagem3del5.getInt(21);
            valores[2]=querryPersonagem3del5.getInt(22);
            valores[3]=querryPersonagem3del5.getInt(23);
            personagem.setDanoBonus(valores.clone());
            //reducao de dano
            valores[0]=querryPersonagem3del5.getInt(24);
            valores[1]=querryPersonagem3del5.getInt(25);
            valores[2]=querryPersonagem3del5.getInt(26);
            valores[3]=querryPersonagem3del5.getInt(27);
            personagem.setReducaoDanoBonus(valores.clone());
            //acerto
            valores[0]=querryPersonagem3del5.getInt(28);
            valores[1]=querryPersonagem3del5.getInt(29);
            valores[2]=querryPersonagem3del5.getInt(30);
            valores[3]=querryPersonagem3del5.getInt(31);
            personagem.setAcertoBonus(valores.clone());
            //reflexo
            valores[0]=querryPersonagem3del5.getInt(32);
            valores[1]=querryPersonagem3del5.getInt(33);
            valores[2]=querryPersonagem3del5.getInt(34);
            valores[3]=querryPersonagem3del5.getInt(35);
            personagem.setReflexoBonus(valores.clone());
            //resistencia
            valores[0]=querryPersonagem3del5.getInt(36);
            valores[1]=querryPersonagem3del5.getInt(37);
            valores[2]=querryPersonagem3del5.getInt(38);
            valores[3]=querryPersonagem3del5.getInt(39);
            personagem.setResistenciaBonus(valores.clone());
            //vontade
            valores[0]=querryPersonagem3del5.getInt(40);
            valores[1]=querryPersonagem3del5.getInt(41);
            valores[2]=querryPersonagem3del5.getInt(42);
            valores[3]=querryPersonagem3del5.getInt(43);
            personagem.setVontadeBonus(valores.clone());
            //percepcao
            valores[0]=querryPersonagem3del5.getInt(44);
            valores[1]=querryPersonagem3del5.getInt(45);
            valores[2]=querryPersonagem3del5.getInt(46);
            valores[3]=querryPersonagem3del5.getInt(47);
            personagem.setPercepcaoBonus(valores.clone());
            //multiplicador
            valores[0]=querryPersonagem3del5.getInt(48);
            valores[1]=querryPersonagem3del5.getInt(49);
            valores[2]=querryPersonagem3del5.getInt(50);
            valores[3]=querryPersonagem3del5.getInt(51);
            personagem.setMultiplicadorBonus(valores.clone());
            //acoes
            valores[0]=querryPersonagem3del5.getInt(52);
            valores[1]=querryPersonagem3del5.getInt(53);
            valores[2]=querryPersonagem3del5.getInt(54);
            valores[3]=querryPersonagem3del5.getInt(55);
            personagem.setAcoesBonus(valores.clone());
            //pv
            valores[0]=querryPersonagem3del5.getInt(56);
            valores[1]=querryPersonagem3del5.getInt(57);
            valores[2]=querryPersonagem3del5.getInt(58);
            valores[3]=querryPersonagem3del5.getInt(59);
            personagem.setPvBonus(valores.clone());
            //atributo Um
            personagem.setAtributo1(querryPersonagem3del5.getInt(60));
            personagem.setAtributo1Bonus(querryPersonagem3del5.getInt(61));
            personagem.setAtributo1Nome(querryPersonagem3del5.getString(62));
            //atributo 2
            personagem.setAtributo2(querryPersonagem3del5.getInt(63));
            personagem.setAtributo2Bonus(querryPersonagem3del5.getInt(64));
            personagem.setAtributo2Nome(querryPersonagem3del5.getString(65));
            //imagem
            personagem.setImagem(querryPersonagem3del5.getString(66));
            //Levantamento de Peso
            valores[0]=querryPersonagem3del5.getInt(67);
            valores[1]=querryPersonagem3del5.getInt(68);
            valores[2]=querryPersonagem3del5.getInt(69);
            valores[3]=querryPersonagem3del5.getInt(70);
            personagem.setLevPesoBonus(valores.clone());
            //altura Salto
            valores2[0]=querryPersonagem3del5.getFloat(71);
            valores2[1]=querryPersonagem3del5.getFloat(72);
            valores2[2]=querryPersonagem3del5.getFloat(73);
            valores2[3]=querryPersonagem3del5.getFloat(74);
            personagem.setAltSaltoBonus(valores2.clone());
            //Velocidade Corrida
            valores[0]=querryPersonagem3del5.getInt(75);
            valores[1]=querryPersonagem3del5.getInt(76);
            valores[2]=querryPersonagem3del5.getInt(77);
            valores[3]=querryPersonagem3del5.getInt(78);
            personagem.setVelCorridaBonus(valores.clone());

            //atribuir
            retornar.add(personagem);
            //proximo
            querryPersonagem3del5.moveToNext();
        }

        return retornar;
    }

    private static List<objeto_3del5_item> cursorToArrayItem(Cursor querryItem3del5) {
        ArrayList<objeto_3del5_item> retornar = new ArrayList<>();
        querryItem3del5.moveToNext();
        for (int i=0;i<querryItem3del5.getCount();i++){
            objeto_3del5_item item=new objeto_3del5_item(0);
            item.setIdItem(querryItem3del5.getInt(0));
            item.setNome(querryItem3del5.getString(1));
            item.setDescricao((querryItem3del5.getString(2)));
            item.setPeso((querryItem3del5.getFloat(3)));
            item.setQuantidade((querryItem3del5.getInt(4)));
            item.setTipo((querryItem3del5.getInt(5)));
            boolean equipado=true;
            if((querryItem3del5.getInt(6))<1){equipado=false;}
            item.setEquipado(equipado);
            item.setIdPersonagem((querryItem3del5.getInt(7)));
            //atribuir
            retornar.add(item);
            //proximo
            querryItem3del5.moveToNext();
        }

        return retornar;
    }

    private static List<objeto_3del5_pericia> cursorToArrayPericia(Cursor querryPericia3del5) {
        ArrayList<objeto_3del5_pericia> retornar = new ArrayList<>();
        querryPericia3del5.moveToNext();
        for (int i=0;i<querryPericia3del5.getCount();i++){
            objeto_3del5_pericia pericia=new objeto_3del5_pericia();
            pericia.setIdPericia(querryPericia3del5.getInt(0));
            pericia.setNome(querryPericia3del5.getString(1));
            pericia.setTipo((querryPericia3del5.getInt(2)));
            pericia.setAtributoPrincipal((querryPericia3del5.getInt(3)));
            //atribuir
            retornar.add(pericia);
            //proximo
            querryPericia3del5.moveToNext();
        }
        ferramentasListaPericias.ordenaListaPericiasNome(retornar);
        ferramentasListaPericias.ordenaListaPericiasAtributo(retornar);
        ferramentasListaPericias.ordenaListaPericiasTipo(retornar);

        return retornar;
    }

    private static List<objeto_3del5_periciaPersonagem> cursorToArrayPericiaPersonagem(Cursor querryPericiaPersonagem3del5) {
        ArrayList<objeto_3del5_periciaPersonagem> retornar = new ArrayList<>();
        querryPericiaPersonagem3del5.moveToNext();
        for (int i=0;i<querryPericiaPersonagem3del5.getCount();i++){
            objeto_3del5_periciaPersonagem periciaPersonagem=new objeto_3del5_periciaPersonagem(0,0);
            periciaPersonagem.setPericiaPorID(querryPericiaPersonagem3del5.getInt(0));
            periciaPersonagem.setPersonagemPorID(querryPericiaPersonagem3del5.getInt(1));
            periciaPersonagem.setGraduacao((querryPericiaPersonagem3del5.getInt(2)));
            //atribuir
            retornar.add(periciaPersonagem);
            //proximo
            querryPericiaPersonagem3del5.moveToNext();
        }

        return retornar;
    }

    private static List<objeto_3del5_caracteristica> cursorToArrayCaracteristica(Cursor querryCaracteristica3del5) {
        ArrayList<objeto_3del5_caracteristica> retornar = new ArrayList<>();
        querryCaracteristica3del5.moveToNext();
        for (int i=0;i<querryCaracteristica3del5.getCount();i++){
            objeto_3del5_caracteristica caracteristica=new objeto_3del5_caracteristica(0);
            caracteristica.setIdCaracteristica(querryCaracteristica3del5.getInt(0));
            caracteristica.setNomeCaracteristica(querryCaracteristica3del5.getString(1));
            caracteristica.setDescricaoCaracteristica(querryCaracteristica3del5.getString(2));
            caracteristica.setCustoCaracteristica((querryCaracteristica3del5.getInt(3)));
            caracteristica.setIdPersonagem((querryCaracteristica3del5.getInt(4)));
            //atribuir
            retornar.add(caracteristica);
            //proximo
            querryCaracteristica3del5.moveToNext();
        }

        return retornar;
    }





    //Recuperar Pontual

    public static objeto_3del5_personagem recuperaPersonagem(int idPersonagem){
        String tabela="personagem3del5";
        String[] strg=new String[]{"*"};
        String where="idPersonagem ="+idPersonagem;
        Cursor querryPersonagem3del5 = banco.query(tabela, strg,where , null, null, null, null);
        List<objeto_3del5_personagem> retorno= cursorToArrayPersonagem(querryPersonagem3del5);
        if(retorno.size()>0){
            return retorno.get(0);
        }
        return null;
    }

    public static objeto_3del5_pericia recuperaPericia(int idPericia){
        String tabela="pericia3del5";
        String[] strg=new String[]{"*"};
        String where="idPericia ="+idPericia;
        Cursor querryPericia3del5 = banco.query(tabela, strg,where , null, null, null, null);
        List<objeto_3del5_pericia> retorno= cursorToArrayPericia(querryPericia3del5);
        if(retorno.size()>0){
            return retorno.get(0);
        }
            return null;
    }


    public static conexao_bd_interno getConecxao() {
        return conecxao;
    }
}
