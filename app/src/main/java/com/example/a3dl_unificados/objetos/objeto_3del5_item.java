package com.example.a3dl_unificados.objetos;

public class objeto_3del5_item {

    private boolean equipado=false;
    private String nome="";
    private String descricao="";
    private String script="";
    private int quantidade=1;
    private int tipo=0;
    private int idItem=0;
    private int idPersonagem=0;
    private float peso=0;


    public objeto_3del5_item(objeto_3del5_personagem Personagem) {
        this.idPersonagem = Personagem.getCodPersonagem();
    }

    public objeto_3del5_item(int id) {
        this.idPersonagem = id;
    }

    public int onUse(){

        return 0;
    }

    public int onEquipe(){

        return 0;
    }


    @Override
    public String toString(){
        return nome+": "+quantidade;
    }




    public void setEquipado(boolean equipado) {
        this.equipado = equipado;
    }

    public boolean isEquipado() {
        return equipado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getScript() {
        return script;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public float getPeso() {
        return peso;
    }

    public int getTipo() {
        return tipo;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getIdPersonagem() {
        return idPersonagem;
    }

    public void setIdPersonagem(int idPersonagem) {
        this.idPersonagem = idPersonagem;
    }
}
