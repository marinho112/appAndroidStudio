package com.example.a3dl_unificados.objetos;

public class objeto_3del5_caracteristica {


    private int idCaracteristica;
    private String nomeCaracteristica;
    private String descricaoCaracteristica;
    private int custoCaracteristica;
    private int idPersonagem;


    public objeto_3del5_caracteristica(objeto_3del5_personagem Personagem) {
        this.idPersonagem = Personagem.getCodPersonagem();
    }

    public objeto_3del5_caracteristica(int id) {
        this.idPersonagem = id;
    }


    public int getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(int idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public String getNomeCaracteristica() {
        return nomeCaracteristica;
    }

    public void setNomeCaracteristica(String nomeCaracteristica) {
        this.nomeCaracteristica = nomeCaracteristica;
    }

    public String getDescricaoCaracteristica() {
        return descricaoCaracteristica;
    }

    public void setDescricaoCaracteristica(String descricaoCaracteristica) {
        this.descricaoCaracteristica = descricaoCaracteristica;
    }

    public int getCustoCaracteristica() {
        return custoCaracteristica;
    }

    public void setCustoCaracteristica(int custoCaracteristica) {
        this.custoCaracteristica = custoCaracteristica;
    }

    public int getIdPersonagem() {
        return idPersonagem;
    }

    public void setIdPersonagem(int idPersonagem) {
        this.idPersonagem = idPersonagem;
    }
}
