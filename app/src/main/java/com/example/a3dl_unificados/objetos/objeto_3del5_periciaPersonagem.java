package com.example.a3dl_unificados.objetos;

import com.example.a3dl_unificados.dao.bdInternoDAO;

public class objeto_3del5_periciaPersonagem {

    private objeto_3del5_pericia pericia;
    private objeto_3del5_personagem personagem;
    private int graduacao;

    public objeto_3del5_periciaPersonagem(int idPericia,int idPersonagem){
        setPersonagemPorID(idPersonagem);
        setPericiaPorID(idPericia);
    }

    public int getIdPericia() {
        return getPericia().getIdPericia();
    }

    public void setPericiaPorID(int idPericia) {
        if(idPericia==0){
            this.setPericia(null);
        }else {
            this.setPericia(bdInternoDAO.recuperaPericia(idPericia));
        }
    }

    public int getIdPersonagem() {
        return getPersonagem().getCodPersonagem();
    }

    public void setPersonagemPorID(int idPersonagem) {
        if(idPersonagem==0){
            this.setPersonagem(null);
        }else {
            this.setPersonagem(bdInternoDAO.recuperaPersonagem(idPersonagem));
        }
    }

    public int getGraduacao() {
        return graduacao;
    }

    public void setGraduacao(int graduacao) {
        this.graduacao = graduacao;
    }

    public objeto_3del5_pericia getPericia() {
        return pericia;
    }

    public void setPericia(objeto_3del5_pericia pericia) {
        this.pericia = pericia;
    }

    public objeto_3del5_personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(objeto_3del5_personagem personagem) {
        this.personagem = personagem;
    }
}
