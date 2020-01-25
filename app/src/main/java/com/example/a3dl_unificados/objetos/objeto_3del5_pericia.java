package com.example.a3dl_unificados.objetos;

public class objeto_3del5_pericia {

    private int idPericia;
    private String nome;
    private int tipo;
    private int atributoPrincipal;


    public objeto_3del5_pericia() {
    }

    public objeto_3del5_pericia(String nome,int tipo,int atributoPrincipal) {
        this.nome=nome;
        this.tipo=tipo;
        this.atributoPrincipal=atributoPrincipal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getAtributoPrincipal() {
        return atributoPrincipal;
    }

    public void setAtributoPrincipal(int atributoPrincipal) {
        this.atributoPrincipal = atributoPrincipal;
    }

    public int getIdPericia() {
        return idPericia;
    }

    public void setIdPericia(int idPericia) {
        this.idPericia = idPericia;
    }
}
