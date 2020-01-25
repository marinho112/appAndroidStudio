package com.example.a3dl_unificados.objetos;

import com.example.a3dl_unificados.dao.bdInternoDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class objeto_3del5_personagem implements Serializable{

    private String nome="";
    private String raca="";
    private String funcao="";
    private String classeDeTamanho="";
    private String imagem;

    private int codPersonagem=0;
    private int idade=0;

    private int forca=0;
    private int forcaBonus=0;
    private int vitalidade=0;
    private int vitalidadeBonus=0;
    private int destreza=0;
    private int destrezaBonus=0;
    private int agilidade=0;
    private int agilidadeBonus=0;
    private int sentidos=0;
    private int sentidosBonus=0;
    private int inteligengia=0;
    private int inteligengiaBonus=0;


    private String atributo1Nome="";
    private int atributo1=0;
    private int atributo1Bonus=0;
    private String atributo2Nome="";
    private int atributo2=0;
    private int atributo2Bonus=0;
    private int[] levPesoBonus={0,0,0,0};
    private float[] altSaltoBonus={0,0,0,0};
    private int[] velCorridaBonus={0,0,0,0};


    private int pvPerdido=0;
    private float deslocamento=0;

    private int[] pvBonus={0,0,0,0};
    private int[] acoesBonus={0,0,0,0};


    private int[] danoBonus ={0,0,0,0};
    private int[] reducaoDanoBonus ={0,0,0,0};
    private int[] acertoBonus ={0,0,0,0};
    private int[] reflexoBonus ={0,0,0,0};
    private int[] resistenciaBonus ={0,0,0,0};
    private int[] vontadeBonus ={0,0,0,0};
    private int[] percepcaoBonus ={0,0,0,0};
    private int[] multiplicadorBonus ={0,0,0,0};

    public void resetarAtributos(){
        forca=0;
        forcaBonus=0;
        vitalidade=0;
        vitalidadeBonus=0;
        destreza=0;
        destrezaBonus=0;
        agilidade=0;
        agilidadeBonus=0;
        sentidos=0;
        sentidosBonus=0;
        inteligengia=0;
        inteligengiaBonus=0;


        atributo1=0;
        atributo1Bonus=0;
        atributo2=0;
        atributo2Bonus=0;
        levPesoBonus= new int[]{0, 0, 0, 0};
        altSaltoBonus= new float[]{0, 0, 0, 0};
        velCorridaBonus= new int[]{0, 0, 0, 0};


        pvPerdido=0;
        deslocamento=0;

        pvBonus= new int[]{0, 0, 0, 0};
        acoesBonus= new int[]{0, 0, 0, 0};


        danoBonus = new int[]{0, 0, 0, 0};
        reducaoDanoBonus = new int[]{0, 0, 0, 0};
        acertoBonus = new int[]{0, 0, 0, 0};
        reflexoBonus = new int[]{0, 0, 0, 0};
        resistenciaBonus = new int[]{0, 0, 0, 0};
        vontadeBonus = new int[]{0, 0, 0, 0};
        percepcaoBonus = new int[]{0, 0, 0, 0};
        multiplicadorBonus = new int[]{0, 0, 0, 0};
    }

    public int getPericiasFisicas(){
        int retorno=((sentidos+sentidosBonus)*5);
        if(retorno<0){return 0;}
        return retorno;
    }
    public int getPericiasIntelectuais(){

        int retorno=((inteligengia+inteligengiaBonus)*10);
        if(retorno<0){return 0;}
        return retorno;
    }
    public int getPv(){
        int retorno = (3 * (forca + forcaBonus) + 20 * (vitalidade + vitalidadeBonus)) + (pvBonus[0] + pvBonus[1] + pvBonus[2] + pvBonus[3]);
        if(retorno<0){return 0;}
        return retorno;
    }
    public int getPvBase(){
        return (3*(forca+forcaBonus)+20*(vitalidade+vitalidadeBonus));
    }

    public int getAcoes(){
        int retorno = 2 + ((agilidade + agilidadeBonus) / 3) + (acoesBonus[0] + acoesBonus[1] + acoesBonus[2] + acoesBonus[3]);
        if(retorno<0){return 0;}
        return retorno;
    }
    public int getAcoesBase(){
        return 2+((agilidade+agilidadeBonus)/3);
    }

    public int getLevPeso(){

        float peso = (3 * (forca * forca)) + (levPesoBonus[0] + levPesoBonus[1] + levPesoBonus[2] + levPesoBonus[3]);
        List<objeto_3del5_pericia> pericia = bdInternoDAO.recuperaPericiasPorNome("'Levantar Peso'");
        if(pericia.size()==0){return (int)peso;}

        List<objeto_3del5_periciaPersonagem> listaPericiaPersonagems = bdInternoDAO.recuperaPericiaPersonagem(this, pericia.get(0));
        if(listaPericiaPersonagems.size()>0){
            float incremento=(1+(((float)listaPericiaPersonagems.get(0).getGraduacao())/10));
            peso*=incremento;
        }

        if(peso<0){return 0;}

        return (int)peso;
    }

    public float getAltSalto(){
        float valor=((agilidade+agilidadeBonus)+(forca+forcaBonus));
        float salto = ((deslocamento / 4) + ((valor / 10)*deslocamento)) + (altSaltoBonus[0] + altSaltoBonus[1] + altSaltoBonus[2] + altSaltoBonus[3]);

        List<objeto_3del5_pericia> pericia = bdInternoDAO.recuperaPericiasPorNome("'Saltar'");

        if(pericia.size()==0){return salto;}

        List<objeto_3del5_periciaPersonagem> listaPericiaPersonagems = bdInternoDAO.recuperaPericiaPersonagem(this, pericia.get(0));
        if(listaPericiaPersonagems.size()>0){
            float incremento=(1+(((float)listaPericiaPersonagems.get(0).getGraduacao())/10));
            salto*=incremento;
        }

        if(salto<0){return 0;}
        return salto;
    }

    public int getVelCorrida(){
        float corrida = ((int) deslocamento) * (5 + ((agilidade + agilidadeBonus) * 3) + (velCorridaBonus[0] + velCorridaBonus[1] + velCorridaBonus[2] + velCorridaBonus[3]));

        List<objeto_3del5_pericia> pericia = bdInternoDAO.recuperaPericiasPorNome("'Correr'");

        if(pericia.size()==0){return (int)corrida;}

        List<objeto_3del5_periciaPersonagem> listaPericiaPersonagems = bdInternoDAO.recuperaPericiaPersonagem(this, pericia.get(0));
        if(listaPericiaPersonagems.size()>0){
            float incremento=(1+(((float)listaPericiaPersonagems.get(0).getGraduacao())/10));
            corrida*=incremento;
        }
        if(corrida<0){return 0;}
        return (int)corrida;
    }

    public int getPtsFicha(){
        int pts=forca+vitalidade+destreza+agilidade+sentidos+inteligengia;
        List<objeto_3del5_caracteristica> caracteristicas = bdInternoDAO.recuperaTodasAsCaracteristicas(this);
        for(int i=0;i<caracteristicas.size();i++){
            pts+=caracteristicas.get(i).getCustoCaracteristica();
        }

        return pts;
    }

    public int getDano(){
        return (5*(forca+forcaBonus))+(danoBonus[0]+danoBonus[1]+danoBonus[2]+danoBonus[3]);
    }
    public int getDanoBase(){
        return (5*(forca+forcaBonus));
    }

    public int getReducaoDano(){
        return (2*(vitalidade+vitalidadeBonus))+(forca+forcaBonus)+(reducaoDanoBonus[0]+reducaoDanoBonus[1]+reducaoDanoBonus[2]+reducaoDanoBonus[3]);
    }
    public int getReducaoDanoBase(){
        return (2*(vitalidade+vitalidadeBonus))+(forca+forcaBonus);
    }

    public int getAcerto(){
        return ((destreza+destrezaBonus)+((agilidade+agilidadeBonus)/2))+(acertoBonus[0]+acertoBonus[1]+acertoBonus[2]+acertoBonus[3]);
    }
    public int getAcertoBase(){
        return ((destreza+destrezaBonus)+((agilidade+agilidadeBonus)/2));
    }

    public int getReflexo(){
        return ((sentidos+sentidosBonus)+((agilidade+agilidadeBonus)/2))+(reflexoBonus[0]+reflexoBonus[1]+reflexoBonus[2]+reflexoBonus[3]);
    }
    public int getReflexoBase(){
        return ((sentidos+sentidosBonus)+((agilidade+agilidadeBonus)/2));
    }

    public int getResistencia(){
        return ((vitalidade+vitalidadeBonus)+((forca+forcaBonus)/2))+(resistenciaBonus[0]+resistenciaBonus[1]+resistenciaBonus[2]+resistenciaBonus[3]);
    }
    public int getResistenciaBase(){
        return ((vitalidade+vitalidadeBonus)+((forca+forcaBonus)/2));
    }

    public int getPercepcao(){
        return ((sentidos+sentidosBonus)+((inteligengia+inteligengiaBonus)/2))+(percepcaoBonus[0]+percepcaoBonus[1]+percepcaoBonus[2]+percepcaoBonus[3]);
    }
    public int getPercepcaoBase(){
        return ((sentidos+sentidosBonus)+((inteligengia+inteligengiaBonus)/2));
    }

    public int getVontade(){
        return ((inteligengia+inteligengiaBonus)+((sentidos+sentidosBonus)/2))+(vontadeBonus[0]+vontadeBonus[1]+vontadeBonus[2]+vontadeBonus[3]);
    }
    public int getVontadeBase(){
        return ((inteligengia+inteligengiaBonus)+((sentidos+sentidosBonus)/2));
    }

    public int getMultiplicador(){
        return (1+(destreza+destrezaBonus))+(multiplicadorBonus[0]+multiplicadorBonus[1]+multiplicadorBonus[2]+multiplicadorBonus[3]);
    }
    public int getMultiplicadorBase(){
        return (1+(destreza+destrezaBonus));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getClasseDeTamanho() {
        return classeDeTamanho;
    }

    public void setClasseDeTamanho(String classeDeTamanho) {
        this.classeDeTamanho = classeDeTamanho;
    }

    public int getCodPersonagem() {
        return codPersonagem;
    }

    public void setCodPersonagem(int codPersonagem) {
        this.codPersonagem = codPersonagem;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getForcaBonus() {
        return forcaBonus;
    }

    public void setForcaBonus(int forcaBonus) {
        this.forcaBonus = forcaBonus;
    }

    public int getVitalidade() {
        return vitalidade;
    }

    public void setVitalidade(int vitalidade) {
        this.vitalidade = vitalidade;
    }

    public int getVitalidadeBonus() {
        return vitalidadeBonus;
    }

    public void setVitalidadeBonus(int vitalidadeBonus) {
        this.vitalidadeBonus = vitalidadeBonus;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getDestrezaBonus() {
        return destrezaBonus;
    }

    public void setDestrezaBonus(int destrezaBonus) {
        this.destrezaBonus = destrezaBonus;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(int agilidade) {
        this.agilidade = agilidade;
    }

    public int getAgilidadeBonus() {
        return agilidadeBonus;
    }

    public void setAgilidadeBonus(int agilidadeBonus) {
        this.agilidadeBonus = agilidadeBonus;
    }

    public int getSentidos() {
        return sentidos;
    }

    public void setSentidos(int sentidos) {
        this.sentidos = sentidos;
    }

    public int getSentidosBonus() {
        return sentidosBonus;
    }

    public void setSentidosBonus(int sentidosBonus) {
        this.sentidosBonus = sentidosBonus;
    }

    public int getInteligengia() {
        return inteligengia;
    }

    public void setInteligengia(int inteligengia) {
        this.inteligengia = inteligengia;
    }

    public int getInteligengiaBonus() {
        return inteligengiaBonus;
    }

    public void setInteligengiaBonus(int inteligengiaBonus) {
        this.inteligengiaBonus = inteligengiaBonus;
    }

    public int getPvPerdido() {
        return pvPerdido;
    }

    public void setPvPerdido(int pvPerdido) {
        this.pvPerdido = pvPerdido;
    }

    public int[] getDanoBonus() {
        return danoBonus;
    }

    public void setDanoBonus(int[] danoBonus) {
        this.danoBonus = danoBonus;
    }

    public int[] getReducaoDanoBonus() {
        return reducaoDanoBonus;
    }

    public void setReducaoDanoBonus(int[] reducaoDanoBonus) {
        this.reducaoDanoBonus = reducaoDanoBonus;
    }

    public int[] getAcertoBonus() {
        return acertoBonus;
    }

    public void setAcertoBonus(int[] acertoBonus) {
        this.acertoBonus = acertoBonus;
    }

    public int[] getReflexoBonus() {
        return reflexoBonus;
    }

    public void setReflexoBonus(int[] reflexoBonus) {
        this.reflexoBonus = reflexoBonus;
    }

    public int[] getResistenciaBonus() {
        return resistenciaBonus;
    }

    public void setResistenciaBonus(int[] resistenciaBonus) {
        this.resistenciaBonus = resistenciaBonus;
    }

    public int[] getVontadeBonus() {
        return vontadeBonus;
    }

    public void setVontadeBonus(int[] vontadeBonus) {
        this.vontadeBonus = vontadeBonus;
    }

    public int[] getPercepcaoBonus() {
        return percepcaoBonus;
    }

    public void setPercepcaoBonus(int[] percepcaoBonus) {
        this.percepcaoBonus = percepcaoBonus;
    }

    public int[] getMultiplicadorBonus() {
        return multiplicadorBonus;
    }

    public void setMultiplicadorBonus(int[] multiplicadorBonus) {
        this.multiplicadorBonus = multiplicadorBonus;
    }

    public String getAtributo1Nome() {
        return atributo1Nome;
    }

    public void setAtributo1Nome(String atributo1Nome) {
        this.atributo1Nome = atributo1Nome;
    }

    public int getAtributo1() {
        return atributo1;
    }

    public void setAtributo1(int atributo1) {
        this.atributo1 = atributo1;
    }

    public int getAtributo1Bonus() {
        return atributo1Bonus;
    }

    public void setAtributo1Bonus(int atributo1Bonus) {
        this.atributo1Bonus = atributo1Bonus;
    }

    public String getAtributo2Nome() {
        return atributo2Nome;
    }

    public void setAtributo2Nome(String atributo2Nome) {
        this.atributo2Nome = atributo2Nome;
    }

    public int getAtributo2() {
        return atributo2;
    }

    public void setAtributo2(int atributo2) {
        this.atributo2 = atributo2;
    }

    public int getAtributo2Bonus() {
        return atributo2Bonus;
    }

    public void setAtributo2Bonus(int atributo2Bonus) {
        this.atributo2Bonus = atributo2Bonus;
    }

    public float getDeslocamento() {
        return deslocamento;
    }

    public void setDeslocamento(float deslocamento) {
        this.deslocamento = deslocamento;
    }

    public int[] getPvBonus() {
        return pvBonus;
    }

    public void setPvBonus(int[] pvBonus) {
        this.pvBonus = pvBonus;
    }

    public int[] getAcoesBonus() {
        return acoesBonus;
    }

    public void setAcoesBonus(int[] acoesBonus) {
        this.acoesBonus = acoesBonus;
    }

    public int[] getLevPesoBonus() {
        return levPesoBonus;
    }

    public void setLevPesoBonus(int[] levPesoBonus) {
        this.levPesoBonus = levPesoBonus;
    }

    public float[] getAltSaltoBonus() {
        return altSaltoBonus;
    }

    public void setAltSaltoBonus(float[] altSaltoBonus) {
        this.altSaltoBonus = altSaltoBonus;
    }

    public int[] getVelCorridaBonus() {
        return velCorridaBonus;
    }

    public void setVelCorridaBonus(int[] velCorridaBonus) {
        this.velCorridaBonus = velCorridaBonus;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
